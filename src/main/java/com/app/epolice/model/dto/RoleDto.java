package com.app.epolice.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Role dto.
 */
public class RoleDto {
    private String name;

    private List<PermissionDto> permissionDtoList = new ArrayList<>();

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets permission dto list.
     *
     * @return the permission dto list
     */
    public List<PermissionDto> getPermissionDtoList() {
        return permissionDtoList;
    }

    /**
     * Sets permission dto list.
     *
     * @param permissionDtoList the permission dto list
     */
    public void setPermissionDtoList(List<PermissionDto> permissionDtoList) {
        this.permissionDtoList = permissionDtoList;
    }
}
