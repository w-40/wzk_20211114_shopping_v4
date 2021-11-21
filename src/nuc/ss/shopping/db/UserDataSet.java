package nuc.ss.shopping.db;

import nuc.ss.shopping.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataSet {
//    public static Set<User> users = new HashSet<User>();
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        UserDataSet.users = users;
//    }

    public static List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        UserDataSet.users = users;
    }

    public boolean addUser(User user) {
        users.add(user);
        return true;
    }
}
