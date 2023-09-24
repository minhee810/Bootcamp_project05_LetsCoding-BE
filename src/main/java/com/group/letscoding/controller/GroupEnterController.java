package com.group.letscoding.controller;

import com.group.letscoding.domain.group.GroupRepository;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.domain.user.UserRepository;
import com.group.letscoding.dto.user.UserDto;
import com.group.letscoding.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class GroupEnterController {

    private final GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    public GroupEnterController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/group/{id}")
    public String groupForm(@PathVariable Integer id, Model model){
        model.addAttribute("id", id);
        return "/group/group-form";
    }

    @GetMapping("/group/{id}/inviteForm")
    public String groupInviteForm(@PathVariable Integer id, String username, Model model){
        List<User> usersInGroup = groupRepository.findUsersInGroup(id);

        model.addAttribute("usersInGroup", usersInGroup);
        model.addAttribute("id", id);
        model.addAttribute("username", username);

        List<User> userList = groupService.getUsersInGroup(id);
        model.addAttribute("users", userList);

        return "group/invite";
    }
}
