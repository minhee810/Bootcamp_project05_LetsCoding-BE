package com.group.letscoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {

    @GetMapping("/group/list")
    public String groupList() {
        return "group/group-list";
    }

    @GetMapping("/group/createForm")
    public String groupCreateForm() {
        return "group/group-create";
    }
}
