package fer.infobip.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fer.infobip.project.utils.Base64Converter;
import fer.infobip.project.utils.LargeTextSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "chatbot_responses")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatbotResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "response_id")
    int responseId;

    //    @Lob
//    @Basic(fetch = FetchType.EAGER)
//    @JsonSerialize(using = LargeTextSerializer.class)
//    @Column(name = "response_text", length = Integer.MAX_VALUE)
    @Column(columnDefinition = "TEXT")
    @Convert(converter = Base64Converter.class)
    String text;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    @JsonIgnore
    Conversation conversation;

    public ChatbotResponse() {

    }

    public ChatbotResponse(String text) {
        this.text = text;
    }
}
