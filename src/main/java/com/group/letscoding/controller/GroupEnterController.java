package com.group.letscoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupEnterController {

    @GetMapping("/group/groupForm")
    public String groupForm(){
        return "/group/group-form";
    }

    @GetMapping("/group/inviteForm")
    public String groupInviteForm(){
        return "/group/invite";
    }

    @GetMapping("/group/reviewWriteForm")
    public String reviewWriteForm(){
        return "/group/review-form";
    }
}
