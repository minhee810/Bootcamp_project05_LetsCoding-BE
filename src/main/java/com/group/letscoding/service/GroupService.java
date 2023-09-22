package com.group.letscoding.service;

import com.group.letscoding.domain.StudyGroupMember.GroupMember;
import com.group.letscoding.domain.StudyGroupMember.GroupMemberRepository;
import com.group.letscoding.domain.group.Group;
import com.group.letscoding.domain.group.GroupRepository;
import com.group.letscoding.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Transactional
    public Group createGroup(Group groupEntity, User user) {
        Group group = groupRepository.save(groupEntity);
        GroupMember groupMember = new GroupMember();
        groupMember.setUser(user);
        groupMember.setGroup(group);
        groupMemberRepository.save(groupMember);

        return group;
    }
}
