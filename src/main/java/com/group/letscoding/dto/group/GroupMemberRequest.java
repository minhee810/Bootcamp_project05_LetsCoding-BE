package com.group.letscoding.dto.group;

import lombok.Data;

@Data
public class GroupMemberRequest {

    private int groupId;
    private Long userId;
}
