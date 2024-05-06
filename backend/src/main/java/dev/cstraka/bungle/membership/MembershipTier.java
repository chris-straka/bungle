package dev.cstraka.bungle.membership;

import jakarta.persistence.*;

@Entity
public class MembershipTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g., "Standard", "Premium", "VIP"
    @Column(nullable = false)
    private String tier_name;

    @Column(nullable = false)
    private Integer maxFamiliarWords;
}
