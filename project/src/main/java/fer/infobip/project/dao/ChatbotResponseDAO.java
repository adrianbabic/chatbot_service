package fer.infobip.project.dao;

import fer.infobip.project.models.ChatbotResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotResponseDAO extends JpaRepository<ChatbotResponse, Integer> {
}

