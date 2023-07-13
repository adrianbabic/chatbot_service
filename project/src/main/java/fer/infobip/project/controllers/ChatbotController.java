package fer.infobip.project.controllers;

import fer.infobip.project.models.ChatbotResponse;
import fer.infobip.project.models.Conversation;
import fer.infobip.project.models.User;
import fer.infobip.project.models.messages.MessageV0;
import fer.infobip.project.models.messages.MessageV1;
import fer.infobip.project.models.messages.MessageV2;
import fer.infobip.project.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
@Slf4j
@RequiredArgsConstructor
public class ChatbotController {

    private final UserService userService;
    private final ConversationService conversationService;
    private final MessageService messageService;
    private final ChatbotResponseService chatbotResponseService;
    private final ChatbotService chatbotService;

    @PostMapping("/conversation/v1")
    public ResponseEntity<String> sendMessageV1(@RequestBody MessageV1 messageV1) {

        //jedna linija, sve u chatbotService

        User user = userService.getUserByPhoneNumber(messageV1.getFrom());
        if (user == null) {
            user = new User(messageV1.getFrom(), null);
        }

        Conversation conv = userService.getUsersConversation(user, messageV1.getTo());
        if (conv == null) {
            conv = userService.createNewConversation(user, messageV1.getTo());
        }

        if(!conv.getIsActive()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("This conversation has been closed. Try opening new conversation.");
        }

        ChatbotResponse chatbotResp = chatbotService.askChatbot(messageV1.getText());
        chatbotResp.setConversation(conv);

        conv.addResponse(chatbotResp);
        MessageV0 msg = new MessageV0(messageV1.getFrom(), messageV1.getTo(), messageV1.getText(), null);
        msg.setConversation(conv);
        conv.addMessage(msg);

        userService.saveUser(user);

        return ResponseEntity.ok(chatbotResp.getText());
    }

    @PostMapping("/conversation/v2")
    public ResponseEntity<String> sendMessageV2(@RequestBody MessageV2 messageV2) {

        User user = userService.getUserByPhoneNumber(messageV2.getFrom());
        if (user == null) {
            user = new User(messageV2.getFrom(), messageV2.getDisplayName().orElse(null));
        }

        Conversation conv = userService.getUsersConversation(user, messageV2.getTo());
        if (conv == null) {
            conv = userService.createNewConversation(user, messageV2.getTo());
        }

        if(!conv.getIsActive()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("This conversation has been closed. Try opening new conversation.");
        }

        ChatbotResponse chatbotResp = chatbotService.askChatbot(messageV2.getContent().getText());
        chatbotResp.setConversation(conv);

        conv.addResponse(chatbotResp);
        MessageV0 msg = new MessageV0(messageV2.getFrom(), messageV2.getTo(), messageV2.getContent().getText(), messageV2.getDisplayName().orElse(null));
        msg.setConversation(conv);
        conv.addMessage(msg);

        userService.saveUser(user);

        return ResponseEntity.ok(chatbotResp.getText());
    }

    @GetMapping(value = "/conversation/transcript")
    public ResponseEntity<Conversation> getConversationById(@RequestParam("conversationId") String conversationId) {
        Conversation conv;
        try {
            conv = conversationService.getConversationById(Integer.parseInt(conversationId));
            return new ResponseEntity<>(conv, HttpStatus.OK);
        } catch (NumberFormatException e) {
            log.error("[getConversationById] NumberFormatException for given id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/conversation/chatbotResponse")
    public ResponseEntity<ChatbotResponse> getChatbotResponseById(@RequestParam("chatbotResponseId") String chatbotResponseId) {
        ChatbotResponse resp;
        try {
            resp = chatbotResponseService.getChatbotResponseById(Integer.parseInt(chatbotResponseId));
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NumberFormatException e) {
            log.error("[getChatbotResponseById] NumberFormatException for given id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        messageService.deleteAllMessages();
        chatbotResponseService.deleteAllChatbotResponses();
        conversationService.deleteAllConversations();
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
