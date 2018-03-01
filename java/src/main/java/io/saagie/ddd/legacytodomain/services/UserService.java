package io.saagie.ddd.legacytodomain.services;

import io.saagie.ddd.legacytodomain.controllers.UserRegistrationDto;
import io.saagie.ddd.legacytodomain.dao.PlatformDao;
import io.saagie.ddd.legacytodomain.dao.UserAlreadyExistsException;
import io.saagie.ddd.legacytodomain.dao.UserDao;
import io.saagie.ddd.legacytodomain.model.Platform;
import io.saagie.ddd.legacytodomain.model.User;
import io.saagie.ddd.legacytodomain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.saagie.ddd.legacytodomain.model.Role.ADMIN;
import static io.saagie.ddd.legacytodomain.model.Role.WRITER;

@Service
public class UserService {
    private UserDao userDao;
    private PlatformDao platformDao;
    private MessageService messageService;

    @Autowired
    public UserService(UserDao userDao, PlatformDao platformDao, MessageService messageService) {
        this.userDao = userDao;
        this.platformDao = platformDao;
        this.messageService = messageService;
    }

    public void register(UserRegistrationDto registration) throws TooManyPlatformWritersException, UserAlreadyExistsException {
        Platform defaultPlatform = this.platformDao.get(registration.getDefaultPlatform());
        User user = new User(registration.getFirstname(), registration.getLastname(), registration.getEmail(), defaultPlatform);

        for (Map.Entry<Integer, String> entry : registration.getPlatforms().entrySet()) {
            Integer platformId = entry.getKey();
            String role = entry.getValue();

            Platform platform = this.platformDao.get(platformId);

            long writerCount = platform
                    .getRoles()
                    .entrySet()
                    .stream()
                    .filter(keyValue -> keyValue.getValue().equals(WRITER)).count();
            if (Role.fromLabel(role) == WRITER && writerCount == 3) {
                throw new TooManyPlatformWritersException();
            }

            if (platform.getRoles().entrySet().stream().anyMatch(keyValue -> keyValue.getValue().equals(ADMIN))
                    && Role.fromLabel(role) == WRITER) {
                this.messageService.notifyPlatformAdminOfNewWriter(platform, user);
            }
            user.addRole(platformId, Role.fromLabel(role));
        }

        this.userDao.create(user);
        this.messageService.sendRegistrationConfirmation(user);
    }

    public List<User> listAllUsers() {
        return this.userDao.getAll();
    }

    public List<User> listPlatformUsers(Integer platformId) {
        Platform platform = this.platformDao.get(platformId);
        return platform
                .getRoles()
                .keySet()
                .stream()
                .map(userId -> this.userDao.get(userId))
                .collect(Collectors.toList());
    }
}
