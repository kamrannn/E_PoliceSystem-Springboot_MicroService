package com.app.epolice.controller;

import com.app.epolice.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
}
