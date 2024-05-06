package com.cloud.study.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

/**
 * @author user
 */
@RestController
@RequestMapping("/annotation")
public class AnnotationController {
    @PermitAll
    @GetMapping("/have")
    public String have() {
        return "have";
    }

    @RolesAllowed({"admin"})
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/roleAdmin")
    public String roleAdmin() {
        return "roleAdmin";
    }
//    @RolesAllowed({"Role_admin"})
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/PreAuthorize")
    public String PreAuthorize() {
        return "PreAuthorize";
    }

    @RolesAllowed({"Role_user"})
    @GetMapping("/roleUser")
    public String roleUser() {
        return "roleUser";
    }

    @DenyAll
    @GetMapping("/denyAll")
    public String denyAll() {
        return "denyAll";
    }

}
