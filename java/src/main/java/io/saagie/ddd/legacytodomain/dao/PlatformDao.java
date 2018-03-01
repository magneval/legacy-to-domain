package io.saagie.ddd.legacytodomain.dao;

import io.saagie.ddd.legacytodomain.model.Platform;
import io.saagie.ddd.legacytodomain.model.Role;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PlatformDao {

    private Map<Integer, Platform> platforms = new HashMap<>();

    public PlatformDao() {
        init();
    }

    public Platform get(Integer id) {
        return this.platforms.get(id);
    }

    private void init() {
        Platform one = new Platform(1, "one", new HashMap<Integer, Role>() {{
            put(123, Role.ADMIN);
            put(456, Role.WRITER);
            put(457, Role.WRITER);
            put(458, Role.WRITER);
            put(789, Role.READER);
        }});
        platforms.put(1, one);

        Platform two = new Platform(2, "two", new HashMap<Integer, Role>() {{
            put(123, Role.ADMIN);
            put(456, Role.WRITER);
            put(789, Role.READER);
        }});
        platforms.put(2, two);

        Platform three = new Platform(3, "three", new HashMap<Integer, Role>() {{
            put(123, Role.ADMIN);
        }});
        platforms.put(3, three);
    }
}
