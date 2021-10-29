package com.app.epolice.service;

import com.app.epolice.repository.PermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
}
