package com.app.epolice.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * The type Uuid generator.
 */
@Service
public class UuidGenerator {
    /**
     * Get uuid string.
     *
     * @return the string
     */
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }
}
