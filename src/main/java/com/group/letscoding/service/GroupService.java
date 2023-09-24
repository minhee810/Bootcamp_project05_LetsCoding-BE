package com.group.letscoding.service;

import com.group.letscoding.domain.StudyGroupMember.GroupMember;
import com.group.letscoding.domain.StudyGroupMember.GroupMemberRepository;
import com.group.letscoding.domain.group.Group;
import com.group.letscoding.domain.group.GroupRepository;
import com.group.letscoding.domain.user.User;

import com.group.letscoding.domain.user.UserRepository;
import com.group.letscoding.handler.ex.CustomApiException;
import com.group.letscoding.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Transactional(readOnly = true)
    public Page<Group> getGroupList(Long principalId, Pageable pageable) {
        Page<Group> groups = groupRepository.mGroupList(principalId, pageable);
        return groups;
    }

    @Transactional
    public Group createGroup(Group groupEntity, User user) {
        Group group = groupRepository.save(groupEntity);
        GroupMember groupMember = new GroupMember();
        groupMember.setUser(user);
        groupMember.setGroup(group);
        groupMemberRepository.save(groupMember);

        return group;
    }

    @Transactional
    public void addMember(int groupId, Long userId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> {
            return new CustomApiException("그룹을 찾을 수 없습니다.");
        });
        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new CustomApiException("사용자를 찾을 수 없습니다.");
        });

        if (group.getMembers().size() >= group.getCapacity()) {
            throw new CustomApiException("그룹 정원을 초과했습니다.");
        }

        GroupMember groupMember = new GroupMember();
        groupMember.setGroup(group);
        groupMember.setUser(user);
        groupMemberRepository.save(groupMember);
    }

    public Group getGroupById(int id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> {
            return new CustomException("그룹을 찾을 수 없습니다.");
        });
        return group;
    }

    public List<User> findUsersInGroup(int groupId) {
        List<User> userList = userRepository.findUsersInGroup(groupId);
        return userList;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}