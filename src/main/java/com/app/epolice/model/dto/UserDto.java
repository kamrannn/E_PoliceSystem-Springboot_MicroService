package com.app.epolice.model.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * The type User dto.
 */
@Data
public class UserDto {
    /**
     * The Users data.
     */
    Set<Object> usersData = new HashSet<>();
}
