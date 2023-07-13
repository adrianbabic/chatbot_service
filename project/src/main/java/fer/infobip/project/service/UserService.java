package fer.infobip.project.service;


import fer.infobip.project.dao.UserDAO;
import fer.infobip.project.models.Conversation;
import fer.infobip.project.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserDAO userDAO;

    public User getUserByPhoneNumber(String phoneNumber){
        return userDAO.findByPhoneNumber(phoneNumber);
    }
    public Conversation createNewConversation(User user, String title) {
        Conversation conv = new Conversation();
        conv.setTitle(title);
        conv.setUser(user);
        user.addConversation(conv);
        return conv;
    }

    public void saveUser(User user) {
        log.info("Saving User object");
        userDAO.save(user);
    }

    public void deleteAllUsers(){
        log.info("Deleting all User objects");
        userDAO.deleteAll();
    }

    public Conversation getUsersConversation(User user, String to_attribute) {
        List<Conversation> convs = user.getConversations();
        for(Conversation conv: convs) {
            if(conv.getTitle().equals(to_attribute)){
                return conv;
            }
        }
        return null;
    }
}
