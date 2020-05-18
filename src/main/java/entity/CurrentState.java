package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CurrentState {
    public User currentUser;
    public List<User> users;
    public List<User> likedUsers;
    public List<User> tempUnlikedUsers;

    public CurrentState() {
        users = new ArrayList<>();
        likedUsers = new ArrayList<>();
        tempUnlikedUsers = new ArrayList<>();
    }
}
