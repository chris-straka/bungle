package dev.cstraka.bungle.User;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
@Table
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private List<String> familiarWords;

    // https://stackoverflow.com/questions/2808747
    public UserEntity() {
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.familiarWords = new ArrayList<String>();
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

    public List<String> getFamiliarWords() {
        return this.familiarWords;
    }

    public void setFamiliarWords(List<String> familiarWords) {
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
}
