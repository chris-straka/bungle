package dev.cstraka.bungle.user.jpa;

import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;
import dev.cstraka.bungle.word.Noun;

@Entity
@Table(name = "BungleUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HashSet<Noun> learnedNouns;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "user_groups", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<UserGroup> userGroups = new HashSet<>();

    public void addUserGroup(UserGroup group) {
        userGroups.add(group);
        group.getUsers().add(this);
    }

    private String secret;

    // https://stackoverflow.com/questions/2808747
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.learnedNouns = new HashSet<Noun>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public HashSet<Noun> getLearnedNouns() {
        return this.learnedNouns;
    }

    public void setLearnedNouns(HashSet<Noun> familiarNouns) {
        this.learnedNouns = familiarNouns;
    }

    public void addNewLearnedNoun(Noun noun) {
        learnedNouns.add(noun);
    }

    public void addNewLearnedNouns(Noun[] nouns) {
        for (Noun noun : nouns) {
            learnedNouns.add(noun);
        }
    }

    public void removeLearnedNoun(Noun noun) {
        learnedNouns.remove(noun);
    }

    public void removeLearnedNouns(Noun[] nouns) {
        for (Noun noun : nouns) {
            learnedNouns.remove(noun);
        }
    }

    public void clearLearnedNouns() {
        learnedNouns.clear();
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }
}
