package fer.infobip.project.service;

import fer.infobip.project.dao.MessageDAO;
import fer.infobip.project.models.messages.MessageV0;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageDAO messageDAO;

    public void saveMessage(MessageV0 message) {
        log.info("Saving MessageV0 object");
        messageDAO.save(message);
    }

    public void deleteAllMessages(){
        log.info("Deleting all MessageV0 objects");
        messageDAO.deleteAll();
    }

}
