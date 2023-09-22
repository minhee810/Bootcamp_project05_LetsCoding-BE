package com.group.letscoding.service;

import com.group.letscoding.domain.group.Group;
import com.group.letscoding.domain.group.GroupRepository;
import com.group.letscoding.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    @Transactional
    public Group createGroup(Group group) {
        try {
            return groupRepository.save(group);
        } catch (Exception e) {
            throw new CustomApiException(e.getMessage());
        }
    }
}