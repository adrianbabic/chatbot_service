package fer.infobip.project.service;

import fer.infobip.project.dao.ChatbotResponseDAO;
import fer.infobip.project.models.ChatbotResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChatbotResponseService {

    private final ChatbotResponseDAO chatbotResponseDAO;

    public void saveChatbotReponse(ChatbotResponse chatbotResponse) {
        log.info("Saving ChatbotResponse object");
        chatbotResponseDAO.save(chatbotResponse);
    }

    public void deleteAllChatbotResponses(){
        log.info("Deleting all ChatbotResponse objects");
        chatbotResponseDAO.deleteAll();
    }

    public ChatbotResponse getChatbotResponseById(int chatbotResponseId) {
        log.info("Getting ChatbotResponse object by id {}", chatbotResponseId);
        return chatbotResponseDAO.findById(chatbotResponseId).orElse(null);
    }

}
