package com.group.letscoding.controller;

import com.group.letscoding.config.auth.PrincipalDetails;
import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.dto.post.PostDto;
import com.group.letscoding.service.post.PostServiceImpl;
import com.group.letscoding.dto.post.PostResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller                     // * 페이지 이동 컨트롤러
public class PostController {

    private final PostServiceImpl postService;

    private static final ModelAndView mav = new ModelAndView();

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);


    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    //recruitmentId : 글 번호

    //0.게시판에 글 나열(페이지 이동에 따른 이동 없음)
    @GetMapping(value ={"/post/list","/"})
    public String redirectToPage(@RequestParam(name = "page", defaultValue = "0") int pageNumber, Model model) {
        // Define the page size.
        int size = 5;

        Page<StudyPost> recruitBoardPage = postService.getRecruitBoardPage(pageNumber, size);

        model.addAttribute("recruitBoardList", recruitBoardPage.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", recruitBoardPage.getTotalPages());

        return "post/post-list";
    }

    // 1. 모집 글 작성 페이지로 이동
    @GetMapping("/study/create-post")
    public String createPostView () {
        return "post/create-post";
    }

    // 2. 모집 글 상세보기 페이지로 이동
    @GetMapping("/study/study-recruitment/{recruitmentId}")
    public String recruitment(@PathVariable int recruitmentId, Model model){
        PostResponseDto postResponseDto = postService.getPostById(recruitmentId);
        model.addAttribute("post", postResponseDto);
        model.addAttribute("recruitmentId", recruitmentId);
        return "post/study-recruitment";
    }

    // 3. 모집 글 수정 화면으로 이동,작성 글 데이터 같이 전송 -> updatePost.html
    @GetMapping("/study/update-post/{recruitmentId}")
    public String updatePostViewSwitch (@PathVariable int recruitmentId,
                                            @ModelAttribute PostDto postForm, Model model) {
        postForm.getStart_date();
        postForm.getEnd_date();

        model.addAttribute("post", postForm);
        // PostDto 내부의 recruitmentId를 사용
        model.addAttribute("recruitmentId", recruitmentId);
        System.out.println("recruitmentId = " + recruitmentId);

        return "post/update-post";
    }

    // 3-1. 수정 버튼 누르면 StudyPost update
    @PostMapping("/study/study-recruitment-update/{recruitmentId}")
    public String updatePost(
            @PathVariable int recruitmentId,
            @RequestParam String title,
            @RequestParam String topic,
            @RequestParam String skills,
            @RequestParam String start_date,
            @RequestParam String end_date,
            @RequestParam int max_num,
            @RequestParam String content,
            @AuthenticationPrincipal PrincipalDetails principalDetails){

        Date Real_start_date = convertStringToDate(start_date);
        Date Real_end_date = convertStringToDate(end_date);

        postService.updatePost(title,topic, skills, Real_start_date,
                Real_end_date, max_num, content,principalDetails.getUser().getId());

        return "post/post-list";
    }

    // 4. 스터디 그룹 생성 화면 -> createGroup.html
    @GetMapping("/study/create-group")
    public String createGroup () {
        return "member/create-group";
    }

    // 5. 스터디 구인 글 검색(title)
    @GetMapping(value = "/post/title-search?keyword={keyword}")
    public String StudyRecruitTitleSearch(Model model, @RequestParam String keyword
            , @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        logger.info("StudyRecruitTitleSearch   START");
        //1. 검색어를 사용하여 제목 기반 게시글 검색
        Page<StudyPost> searchResults = postService.findByTitleContaining(keyword,pageable);

        logger.info("StudyRecruitTitleSearch   RETURNs");
        //2.Model 객체에 리스트 담기
        //2.Model 객체에 리스트 담기
        // 리다이렉션 시 데이터를 추가.
        /*
        model.addAttribute("searchResults",searchResults);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", searchResults.hasNext());
        model.addAttribute("hasPrev", searchResults.hasPrevious());
        */

        System.out.println(keyword+""+searchResults);
        return "/post/post-list";
    }

    // 6.스터디 구인 글 검색(skill)
    @GetMapping(value = "/post/skill-search?keyword={keyword}")
    public String StudyRecruitSkillSearch(Model model, @RequestParam String keyword
            ,@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        //1. 검색어를 사용하여 skill 기반 게시글 검색
        Page<StudyPost> searchResults = postService.findBySkillContaining(keyword,pageable);

        //2.Model 객체에 리스트 담기
        // 리다이렉션 시 데이터를 추가.
        model.addAttribute("searchResults",searchResults);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", searchResults.hasNext());
        model.addAttribute("hasPrev", searchResults.hasPrevious());

        return "/post/post-list";
    }

    //7. 스터디 구인 글 작성
    @PostMapping("/study/create-post")
    public String createPost(@RequestParam String title,
                             @RequestParam String topic,
                             @RequestParam String skills,
                             @RequestParam String String_start_date,
                             @RequestParam String String_end_date,
                             @RequestParam int max_num,
                             @RequestParam String content,
                             @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Date start_date = convertStringToDate(String_start_date);
        Date end_date = convertStringToDate(String_end_date);

        postService.savePost(title,topic,start_date,skills,
                end_date,max_num,content,principalDetails.getUser().getId());

        return "redirect:/post/list"; // 처리 후 리다이렉트
    }
    public Date convertStringToDate(String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //convert StudyPost -> PostDTO
//    private Page<PostDTO> getPageDTOs(Page<PostDTO> postDTOList, Page<StudyPost> postList) {
//        for(StudyPost post : postList){
//            PostDTO postDTO = new PostDTO();
//            postDTO.setPost_id(post.getPost_id());
//            postDTO.setTitle(post.getTitle());
//            postDTO.setSkill(post.getSkill());
//
//            postDTOList.add(postDTO);
//        }
//
//        return postDTOList;
//    }

}
