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

    public void create(User user) throws UserAlreadyExistsException {
        if (users.values().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()))) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        Integer nextId = this.users.size();
        user.setId(nextId);
        this.users.put(nextId, user);
    }

    public List<User> getAll() {
        return new ArrayList<>(this.users.values());
    }

    public User get(Integer userId) {
        return this.users.get(userId);
    }

    private void init() {
        Platform defaultPlatform = new Platform(1, "one");

        User george = new User("George", "Abitbol", "george@gd.ca", defaultPlatform);
        george.setId(123);
        this.users.put(george.getId(), george);

        User peter = new User("Peter", "Hoffman", "peter@gd.ca", defaultPlatform);
        peter.setId(456);
        this.users.put(peter.getId(), peter);

        User steven = new User("Steven", "Redford", "steven@gd.ca", defaultPlatform);
        steven.setId(457);
        this.users.put(steven.getId(), steven);

        User dave = new User("Dave", "Newman", "dave@gd.ca", defaultPlatform);
        dave.setId(458);
        this.users.put(dave.getId(), dave);

        User hugues = new User("Hugues", "Fonda", "hugues@gd.ca", defaultPlatform);
        hugues.setId(789);
        this.users.put(hugues.getId(), hugues);
    }
}
