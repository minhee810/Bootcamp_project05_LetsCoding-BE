package com.group.letscoding.domain.studypostcomment;

import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.dto.post.CommentSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyPostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="studyPostId")
    private StudyPost studyPost;

    @CreationTimestamp
    private LocalDateTime createDate;
}
