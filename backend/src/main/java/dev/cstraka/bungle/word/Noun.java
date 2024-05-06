package dev.cstraka.bungle.word;

import dev.cstraka.bungle.user.jpa.User;
import jakarta.persistence.*;

@Entity
public class Noun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Most chinese words are 1-4 characters
    @Column(nullable = false, length = 5)
    private String text;

    // Link back to the User entity
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // https://stackoverflow.com/questions/2808747
    public Noun() {
    }

    public Noun(String text) {
        this.text = text;
    }
}
