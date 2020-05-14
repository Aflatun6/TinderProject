package entity;

import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private final String name;
    private final String imageURL;
    private final int id;
    private static int ids = 0;

    public User(String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
        this.id = ++ids;
    }
}
