let studyPost=
    {
        init:function(){
            $("#btn-studyPostComment-save").on("click", ()=>{
                this.studyPostCommentSave();
            });
        },

        studyPostCommentSave:function(){
            let data ={

                userId:$("#userId").val(),
                studyPostId:$("#studyPostId").val(),
                content:$("#studyPost-content").val()
            };

            let studyPostId = $("#studyPostId").val();

            console.log(data)

            $.ajax({
                type:"POST",
                url:`/api/studyPost/${data.studyPostId}/studyPostComment`,
                data:JSON.stringify(data),
                contentType : "application/json;charset = utf-8",
                dataType:"json"
            }).done(function(resp){
                alert("글쓰기가 완료되었습니다. ");
                location.href=`/study/study-recruitment/${data.studyPostId}`;
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        },


    }
