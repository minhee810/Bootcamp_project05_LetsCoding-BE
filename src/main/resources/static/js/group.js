function createStudyGroup() {
    var topic = $("#topic").val();
    var groupName = $("#groupName").val();
    var introduction = $("#introduction").val();
    var skills = $("#skills").val();
    var capacity = $("#capacity").val();
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();

    var data = {
        topic: topic,
        groupName: groupName,
        introduction: introduction,
        skills: skills,
        capacity: capacity,
        startDate: startDate,
        endDate: endDate
    };

    event.preventDefault();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/study-group/create",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (response) {
            if (response.code === 1) {
                alert("스터디 그룹이 생성되었습니다.");
                location.href = "/group/list";
            } else {
                alert("스터디 그룹 생성에 실패하였습니다.");
            }
        },
        error: function () {
            alert("서버 오류가 발생하였습니다.");
        }
    });
}