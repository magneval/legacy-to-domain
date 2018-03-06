package io.saagie.ddd.legacytodomain.dao;

import io.saagie.ddd.legacytodomain.model.Platform;
import io.saagie.ddd.legacytodomain.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    Map<Integer, User> users = new HashMap<>();

    UserDao() {
        init();
    }

    public void save(User user) {
        this.users.put(user.getId(), user);
    }

    public List<User> getAll() {
        return new ArrayList<>(this.users.values());
    }

    public User get(Integer userId) {
        return this.users.get(userId);
    }

    private void init() {
        User george = new User(123, "George", "Abitbol", "george@gd.ca");
        this.users.put(george.getId(), george);

        User peter = new User(456, "Peter", "Hoffman", "peter@gd.ca");
        this.users.put(peter.getId(), peter);

        User steven = new User(457, "Steven", "Redford", "steven@gd.ca");
        this.users.put(steven.getId(), steven);

        User dave = new User(458, "Dave", "Newman", "dave@gd.ca");
        this.users.put(dave.getId(), dave);

        User hugues = new User(789,"Hugues", "Fonda", "hugues@gd.ca");
        this.users.put(hugues.getId(), hugues);
    }
}
