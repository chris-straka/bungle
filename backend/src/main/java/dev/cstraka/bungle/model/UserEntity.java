// package dev.cstraka.bungle.model;

// import java.util.List;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import java.util.Objects;

// @Entity
// public class UserEntity {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String username;
//     private String password;
//     private List<String> familiarWords;

//     public boolean isAccountVerified() {
//         return this.accountVerified;
//     }

//     public boolean getAccountVerified() {
//         return this.accountVerified;
//     }

//     public void setAccountVerified(boolean accountVerified) {
//         this.accountVerified = accountVerified;
//     }

//     private boolean accountVerified;

//     // https://stackoverflow.com/questions/2808747
//     public UserEntity() {
//     }

//     public UserEntity(String username, String password, List<String> familiarWords) {
//         this.username = username;
//         this.password = password;
//         this.familiarWords = familiarWords;
//     }

//     public Long getId() {
//         return this.id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getUsername() {
//         return this.username;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     public void updateUsername(String newUsername) {
//         this.username = newUsername;
//     }

//     public String getPassword() {
//         return this.password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public List<String> getFamiliarWords() {
//         return this.familiarWords;
//     }

//     public void setFamiliarWords(List<String> familiarWords) {
//         this.familiarWords = familiarWords;
//     }

//     @Override
//     public boolean equals(Object o) {
//         if (o == this)
//             return true;
//         if (!(o instanceof UserEntity)) {
//             return false;
//         }
//         UserEntity user = (UserEntity) o;
//         return Objects.equals(id, user.id) && Objects.equals(username, user.username)
//                 && Objects.equals(password, user.password) && Objects.equals(familiarWords, user.familiarWords);
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hash(id, username, password, familiarWords);
//     }

//     @Override
//     public String toString() {
//         return "{" +
//                 " id='" + getId() + "'" +
//                 ", username='" + getUsername() + "'" +
//                 ", password='" + getPassword() + "'" +
//                 ", familiarWords='" + getFamiliarWords() + "'" +
//                 "}";
//     }

//     public void addFamiliarWord(String word) {
//         familiarWords.add(word);
//     }

//     public void addFamiliarWord(String[] words) {
//         for (String word : words) {
//             familiarWords.add(word);
//         }
//     }

//     public void removeFamiliarWord(String[] words) {
//         for (String word : words) {
//             familiarWords.remove(word);
//         }
//     }

//     public void clearFamiliarWords() {
//         familiarWords.clear();
//     }

// }
