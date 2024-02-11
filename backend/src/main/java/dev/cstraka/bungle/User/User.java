package dev.cstraka.bungle.user;

import java.util.Collection;
import java.util.HashSet;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private HashSet<String> familiarWords;

    // https://stackoverflow.com/questions/2808747
    public User() {
    }

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.familiarWords = new HashSet<String>();
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

    public HashSet<String> getFamiliarWords() {
        return this.familiarWords;
    }

    public void setFamiliarWords(HashSet<String> familiarWords) {
        this.familiarWords = familiarWords;
    }

    public void addFamiliarWord(String word) {
        familiarWords.add(word);
    }

    public void addFamiliarWords(String[] words) {
        for (String word : words) {
            familiarWords.add(word);
        }
    }

    public void removeFamiliarWord(String word) {
        familiarWords.remove(word);
    }

    public void removeFamiliarWords(String[] words) {
        for (String word : words) {
            familiarWords.remove(word);
        }
    }

    public void clearFamiliarWords() {
        familiarWords.clear();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
}
