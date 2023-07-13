package fer.infobip.project.dao;

import fer.infobip.project.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationDAO extends JpaRepository<Conversation, Integer> {
}
