package com.app.epolice.util;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UuidGenerator {
    public static String getUuid(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }
}
