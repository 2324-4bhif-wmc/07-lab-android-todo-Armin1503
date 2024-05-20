package at.htl.posts.model.entity.dto;

public class Company {
    public String name;
    public String catchPhrase;
    public String bs;

    public Company() {
    }

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }
}
