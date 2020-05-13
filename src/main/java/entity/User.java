package entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private final String name;
    private final String imageURL;
}
