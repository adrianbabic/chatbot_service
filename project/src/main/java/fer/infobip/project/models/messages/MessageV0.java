package fer.infobip.project.models.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fer.infobip.project.models.Conversation;
import fer.infobip.project.utils.Base64Converter;
import fer.infobip.project.utils.LargeTextSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "prompt_messages")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageV0 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    long messageId;

    @Column(name = "from_attribute", nullable = false)
    String fromAttribute;

    @Column(name = "to_attribute")
    String toAttribute;

//    @Lob
//    @JsonSerialize(using = LargeTextSerializer.class)
//    @Column(name = "text", length = Integer.MAX_VALUE)
    @Column(columnDefinition = "TEXT")
    @Convert(converter = Base64Converter.class)
    String text;

    @Column(name = "display_name")
    String displayName;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    @JsonIgnore
    Conversation conversation;
    //id, foreign key

    public MessageV0() {

    }
    public MessageV0(String from, String to, String text, String displayName) {
        this.fromAttribute = from;
        this.toAttribute = to;
        this.text = text;
        this.displayName = displayName;
    }
}