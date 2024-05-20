package at.htl.posts.model.entity.dto;

public class UserDto {
    public Long id;
    public String name;
    public String username;
    public String email;
    public Address address;
    public String website;
    public Company company;

    public String phone;

    public UserDto() {
    }

    public UserDto(Long id, String name, String username, String email,
                   String phone, Address address, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.website = website;
        this.company = company;
    }
}
