package com.app.epolice.model.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDto {
    private String name;

    private List<PermissionDto> permissionDtoList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionDto> getPermissionDtoList() {
        return permissionDtoList;
    }

    public void setPermissionDtoList(List<PermissionDto> permissionDtoList) {
        this.permissionDtoList = permissionDtoList;
    }
}
