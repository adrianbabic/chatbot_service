package fer.infobip.project.dao;

import fer.infobip.project.models.messages.MessageV0;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDAO extends JpaRepository<MessageV0, Integer> {
}
