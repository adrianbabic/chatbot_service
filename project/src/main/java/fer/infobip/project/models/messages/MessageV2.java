package fer.infobip.project.models.messages;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageV2 {
    String from;
    String to;
    Content content;

    @Getter
    Optional<String> displayName;

    //optional
    @Data
    public static class Content {
        String text;
    }
}