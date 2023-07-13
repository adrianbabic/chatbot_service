package fer.infobip.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fer.infobip.project.models.messages.MessageV0;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversation")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "conversation_id")
    int conversationId;

    @Column(name = "title")
    String title;

    @Column(name = "is_active")
    Boolean isActive = true;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    List<MessageV0> messages = new ArrayList<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ChatbotResponse> responses = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public void addMessage(MessageV0 message) {
        this.messages.add(message);
    }

    public void addResponse(ChatbotResponse response) {
        this.responses.add(response);
    }

    public MessageV0 getLatestMessage() {

        if (this.messages == null || this.messages.isEmpty())
            return null;
        return this.messages.get(this.messages.size() - 1);
    }


}