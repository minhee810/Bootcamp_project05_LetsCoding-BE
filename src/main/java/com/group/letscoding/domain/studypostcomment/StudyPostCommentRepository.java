package com.group.letscoding.domain.studypostcomment;

import com.group.letscoding.dto.post.CommentSaveRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudyPostCommentRepository extends JpaRepository<StudyPostComment, Integer>{
   /* @Query(value = "insert into studypostcomment(userId, studyPostId, content, createDate) values (?,?,?,now())", nativeQuery = true )
    void mSave(CommentSaveRequestDto commentSaveRequestDto);*/

}

