package fer.infobip.project.models.messages;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageV1 {
    String from;
    String to;
    String text;
}