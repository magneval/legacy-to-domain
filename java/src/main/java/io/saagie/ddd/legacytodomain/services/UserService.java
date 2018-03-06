package io.saagie.ddd.legacytodomain.services;

import io.saagie.ddd.legacytodomain.controllers.PlatformRoleDto;
import io.saagie.ddd.legacytodomain.dao.PlatformDao;
import io.saagie.ddd.legacytodomain.dao.UserDao;
import io.saagie.ddd.legacytodomain.model.Platform;
import io.saagie.ddd.legacytodomain.model.User;
import io.saagie.ddd.legacytodomain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void definePlatformRole(Integer id, PlatformRoleDto platformRole) throws TooManyPlatformWritersException, UserNotFoundException, AuthorizationLimitReachedException {
        User user = this.userDao.get(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }

        Platform platform = this.platformDao.get(platformRole.getPlatformId());

        long writerCount = platform
                .getRoles()
                .entrySet()
                .stream()
                .filter(keyValue -> keyValue.getValue().equals(WRITER)).count();
        if (Role.fromLabel(platformRole.getRole()) == WRITER && writerCount == 3) {
            throw new TooManyPlatformWritersException();
        }

        if (user.getRoles().entrySet().stream().filter(entry -> entry.getValue() == ADMIN).count() == 3 ||
                user.getRoles().entrySet().stream().filter(entry -> entry.getValue() == WRITER).count() == 5) {
            throw new AuthorizationLimitReachedException();
        }

        if (platform.getRoles().entrySet().stream().anyMatch(keyValue -> keyValue.getValue().equals(ADMIN))
                && Role.fromLabel(platformRole.getRole()) == WRITER) {
            this.messageService.notifyPlatformAdminOfNewWriter(platform, user);
        }
        user.addRole(platformRole.getPlatformId(), Role.fromLabel(platformRole.getRole()));

        this.userDao.save(user);
        this.messageService.sendPlatformRoleConfirmation(user, platformRole.getPlatformId(), Role.fromLabel(platformRole.getRole()));
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
