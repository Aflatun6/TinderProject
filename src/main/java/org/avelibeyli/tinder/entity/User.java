package org.avelibeyli.tinder.entity;

import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private final int id;
    private final String name;
    private final String imageURL;
    private final String password;


    public User(int id, String name, String imageURL, String password) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.password = password;
    }
}
