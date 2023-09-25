function submitComment() {
    var studyPostId = $("#studyPostId").val();
    var content = $("#studyPost-content").val();

    // 댓글 데이터를 JSON 형식으로 만듭니다.
    var commentData = {
        studyPostId: studyPostId,
        content: content
    };

    // AJAX를 사용하여 댓글을 서버로 전송
    $.ajax({
        type: "POST",
        url: "/api/post/comment",
        contentType: "application/json", // 요청 데이터 형식을 JSON으로 지정
        data: JSON.stringify(commentData), // JSON 문자열로 변환하여 전송
        dataType: "json", // 서버 응답 데이터 형식을 JSON으로 지정
        success: function(response) {
            // 성공적으로 댓글을 등록한 경우 서버의 응답을 처리
            alert("댓글 등록 성공");
            window.location.href = window.location.href;
            // 추가적인 처리 또는 페이지 갱신 등을 수행할 수 있음
        },
        error: function(error) {
            // 댓글 등록 중 오류 발생한 경우 처리
            console.error("댓글 등록 오류", error);
            // 오류 처리 또는 사용자에게 오류 메시지 표시 등을 수행할 수 있음
        }
    });
}
