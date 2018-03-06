package io.saagie.ddd.legacytodomain.services;

import io.saagie.ddd.legacytodomain.controllers.PlatformRoleDto;
import io.saagie.ddd.legacytodomain.model.Platform;
import io.saagie.ddd.legacytodomain.model.Role;
import io.saagie.ddd.legacytodomain.model.User;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public void sendPlatformRoleConfirmation(User user, Integer platformId, Role role) {

    }

    public void notifyPlatformAdminOfNewWriter(Platform platform, User user) {

    }
}
