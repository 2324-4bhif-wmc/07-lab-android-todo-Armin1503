package at.htl.posts.model.entity;

public class User {
    public Long id;
    public String name = "";
    public String username = "";
    public String email = "";
    public String phone = "";

    public User() {
    }

    public User(Long id, String name, String username, String email, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
}
