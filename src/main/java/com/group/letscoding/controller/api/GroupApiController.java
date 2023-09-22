package com.group.letscoding.controller.api;

import com.group.letscoding.config.auth.PrincipalDetails;
import com.group.letscoding.domain.group.Group;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.dto.CMRespDto;
import com.group.letscoding.dto.group.GroupCreateDto;
import com.group.letscoding.handler.ex.CustomValidationApiException;
import com.group.letscoding.handler.ex.CustomValidationException;
import com.group.letscoding.service.GroupService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GroupApiController {

    private final GroupService groupService;

    @GetMapping("/api/study-group/list")
    public ResponseEntity<?> getGroupList(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                          @PageableDefault(size = 3) Pageable pageable) {
        Page<Group> groups = groupService.getGroupList(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", groups), HttpStatus.OK);
    }

    @ApiOperation(value = "스터디 그룹 생성", notes = "새로운 스터디 그룹을 생성합니다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "스터디 그룹 생성 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청 형식")
    })
    @PostMapping("/api/study-group/create")
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupCreateDto groupCreateDto, BindingResult bindingResult,
                                         @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패", errorMap);
        } else {
            Group group = groupService.createGroup(fromDto(groupCreateDto, principalDetails.getUser()), principalDetails.getUser());
            return new ResponseEntity<>(new CMRespDto<>(1, "스터디 그룹 생성 성공", group), HttpStatus.CREATED);
        }
    }

    private Group fromDto(GroupCreateDto groupCreateDto, User leader) {
        Group groupEntity = new Group();
        groupEntity.setTopic(groupCreateDto.getTopic());
        groupEntity.setGroupName(groupCreateDto.getGroupName());
        groupEntity.setIntroduction(groupCreateDto.getIntroduction());
        groupEntity.setSkills(groupCreateDto.getSkills());
        groupEntity.setCapacity(groupCreateDto.getCapacity());
        groupEntity.setStartDate(groupCreateDto.getStartDate());
        groupEntity.setEndDate(groupCreateDto.getEndDate());
        groupEntity.setLeader(leader);
        return groupEntity;
    }
}
