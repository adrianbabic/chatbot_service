package fer.infobip.project.service;

import fer.infobip.project.CRUDtesting.model.Beer;
import fer.infobip.project.dao.ConversationDAO;
import fer.infobip.project.models.Conversation;
import fer.infobip.project.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ConversationService {

    private final ConversationDAO conversationDAO;

    public void saveConversation(Conversation Conversation) {
        log.info("Saving Conversation object");
        conversationDAO.save(Conversation);
    }

    public void deleteAllConversations(){
        log.info("Deleting all Conversation objects");
        conversationDAO.deleteAll();
    }

    @Transactional
    public Conversation getConversationById(int conversationId) {
        log.info("Getting Conversation object by id {}", conversationId);
        return conversationDAO.findById(conversationId).orElse(null);
    }

}