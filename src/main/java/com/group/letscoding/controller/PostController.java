package com.group.letscoding.controller;

import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.service.post.PostServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller                     // * 페이지 이동 컨트롤러
public class PostController {

    private final PostServiceImpl postService;

    private static final ModelAndView mav = new ModelAndView();

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);


    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

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
    public String recruitment(){
        return "post/study-recruitment";
    }

    // 3. 모집 글 수정 화면으로 이동 -> updatePost.html
    @GetMapping("/study/update-post")
    public String updatePost () {
        return "update-post";
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


//    //convert StudyPost -> PostDTO
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
