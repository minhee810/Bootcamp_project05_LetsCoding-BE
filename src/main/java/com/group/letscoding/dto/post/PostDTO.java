package com.group.letscoding.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//게시글 관련

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO{

    //id(상세보기를 위해 필요함)
    private int post_id;

    //제목
    private String title;

    //기술 스택 - 스터디 그룹 엔티티,테이블과 매팡 팔요
    private String skill;

    public int getPost_id() {
        return post_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSkill() {
        return skill;
    }

    //Getter,Setters...
    //Constructors,toStrings...
}
