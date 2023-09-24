package com.group.letscoding.domain.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group.letscoding.domain.StudyGroupMember.GroupMember;
import com.group.letscoding.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "study_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String groupName;
    private String topic;
    private String introduction;
    private String skills;
    private int capacity;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User leader;

    @OneToMany(mappedBy = "group")
    @JsonIgnoreProperties({"group"})
    private List<GroupMember> members;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
