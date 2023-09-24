let page = 0;

function loadMyGroups() {
    $.ajax({
        url: `/api/study-group/list?page=${page}`,
        dataType: "json"
    }).done(res => {
        res.data.content.forEach((group) => {
            let groupItem = getGroupItem(group);
            $("#groupList").append(groupItem);
        })
    }).fail(error => {
        console.log("오류", error);
    });
}

loadMyGroups();

function getGroupItem(group) {
    let item = `
    <div class="container">
        <div class="d-flex justify-content-center">
            <div class="card m-2" style="width: 60%;">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h4 class="card-title">${group.topic}</h4>
                        <p class="card-text">
                            <strong>Skills:</strong> ${group.skills}<br>
                            <strong>리더:</strong> ${group.leader.name}<br>
                            <strong>그룹 이름:</strong> ${group.groupName}
                        </p>
                    </div>
                    <div class="text-right">
                        <i class="fa-solid fa-laptop-code" style="color: #244989; font-size: 70px; margin-right: 20px;"></i>
                        <br>
                        <a href="/group/${group.id}" class="btn btn-primary mt-2">스터디룸 입장</a>
                    </div>
                </div>
            </div>
        </div>
    </div>`;
    return item;
}

$(window).scroll(() => {
    // console.log("윈도우 scrollTop", $(window).scrollTop());
    // console.log("문서의 높이", $(document).height());
    // console.log("윈도우 높이", $(window).height());

    let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());
    if (checkNum < 1 && checkNum > -1) {
        page++;
        loadMyGroups();
    }
});

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

function addMember() {
    let username = $("#inputMember").val();
    let groupId = $("#groupId").val();

    $.ajax({
        url: `/api/auth/checkUserExist`,
        method: "POST",
        data: username,
        contentType: "text/plain",
        dataType: "json"
    }).done(function (response) {
        if (response.code === 1) {
            if (confirm("해당 멤버를 추가하시겠습니까?")) {
                let userId = response.data;
                $.ajax({
                    url: `/api/study-group/add-member`,
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({ userId: userId, groupId: groupId }),
                    dataType: "json"
                }).done(function (res) {
                    if (res.code === 1) {
                        alert("멤버가 추가되었습니다.");
                        location.href = "/group/list";
                        location.reload();
                    } else {
                        alert("멤버 추가에 실패하였습니다.");
                    }
                }).fail(function (err) {
                    alert("서버 오류가 발생하였습니다.");
                });
            }
        } else {
            alert("존재하지 않는 사용자입니다.");
        }
    }).fail(function (error) {
        alert("서버 오류가 발생하였습니다.");
    });
}