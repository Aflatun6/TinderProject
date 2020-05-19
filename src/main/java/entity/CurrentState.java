package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class CurrentState {
    private final int i;
    public static User currentUser;
    public static List<User> users;
    public static List<User> likedUsers;

    public CurrentState(int i) {
        this.i = i;
        users = new ArrayList<>();
        likedUsers = new ArrayList<>();
        currentUser = new User(0, null, null, null);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<User> likedUsers) {
        this.likedUsers = likedUsers;
    }
}
