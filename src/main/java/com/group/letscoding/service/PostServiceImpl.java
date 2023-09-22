package com.group.letscoding.service;

import com.group.letscoding.domain.post.Post;
import com.group.letscoding.domain.post.PostRepository;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.domain.user.UserRepository;
import com.group.letscoding.dto.post.PostDto;
import com.group.letscoding.dto.post.PostResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Post savaPost(String title, String topic, Date startDate, String skills, Date endDate, int max_num, String content, Long userId) {

        User userEntity = userRepository.findById(userId).orElseThrow(()->{
            return new RuntimeException("유저 아이디를 찾을 수 없습니다.");
        });

        Post post = new Post();
        post.setTitle(title);
        post.setTopic(topic);
        post.setSkills(skills);
        post.setContent(content);
        post.setStart_date(startDate);
        post.setEnd_date(endDate);
        post.setMax_num(max_num);
        post.setUser(userEntity);

        System.out.println("savePost() : " + post);
        return postRepository.save(post);
    }

    @Override
    public PostResponseDto updatePost(int id, PostDto postDto) {
        return null;
    }

    @Override
    public PostResponseDto deletePost(int id) throws Exception {
        return null;
    }



}



