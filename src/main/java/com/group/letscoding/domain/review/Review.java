package com.group.letscoding.domain.review;

import com.group.letscoding.domain.group.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer review_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private Group group;

    /*@ManyToOne
    @JoinColumn(name = "studyGroup_id")
    @ToString.Exclude
    private StudyGroup studyGroup;*/

    private LocalDateTime createdDate;
}
