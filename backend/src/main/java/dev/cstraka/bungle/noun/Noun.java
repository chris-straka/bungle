package dev.cstraka.bungle.noun;

import dev.cstraka.bungle.user.User;
import jakarta.persistence.*;

public class Noun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Most chinese words are 1-4 characters
    @Column(nullable = false, length = 5)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Link back to the User entity
}
