package fer.infobip.project.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    long userId;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "display_name", nullable = true)
    String displayName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Conversation> conversations = new ArrayList<>();

    public User() {
    }

    public User(String phoneNumber, String displayName) {
        this.displayName = displayName;
        this.phoneNumber = phoneNumber;
    }

    public Conversation getActiveConversation() {
        if(this.conversations == null || this.conversations.isEmpty())
            return null;
        return this.conversations.get(this.conversations.size() - 1);
    }

    public void addConversation(Conversation conversation) {
        this.conversations.add(conversation);
    }

}
