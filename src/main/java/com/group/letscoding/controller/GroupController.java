package com.group.letscoding.controller;

import com.group.letscoding.config.auth.PrincipalDetails;
import com.group.letscoding.domain.group.Group;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/group/list")
    public String groupList() {
        return "group/group-list";
    }

    @GetMapping("/group/createForm")
    public String groupCreateForm() {
        return "group/group-create";
    }

    @GetMapping("/group/{id}")
    public String groupForm(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return "/group/group-form";
    }

    @GetMapping("/group/{id}/inviteForm")
    public String groupInviteForm(@PathVariable int id, Model model,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<User> users = groupService.findUsersInGroup(id);
        model.addAttribute("users", users);
        model.addAttribute("id", id);

        Group group = groupService.getGroupById(id);
        boolean isLeader = (group.getLeader().getId() == principalDetails.getUser().getId());
        model.addAttribute("isLeader", isLeader);

        return "group/invite";
    }
}
