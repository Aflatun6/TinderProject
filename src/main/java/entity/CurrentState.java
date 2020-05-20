package entity;

import lombok.EqualsAndHashCode;
import java.util.List;

@EqualsAndHashCode
public class CurrentState {
    private static User currentUser;
    private static List<User> users;
    private static List<User> likedUsers;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentState.currentUser = currentUser;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        CurrentState.users = users;
    }

    public static List<User> getLikedUsers() {
        return likedUsers;
    }

    public static void setLikedUsers(List<User> likedUsers) {
        CurrentState.likedUsers = likedUsers;
    }

}
