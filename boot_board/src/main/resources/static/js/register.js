/**
 * register.jsp 스크립트
 */
$(function(){
	$(":submit").click(function(e){
		e.preventDefault();
		
		let str = "";
		
		// li 태그 정보 수집하기
		$(".uploadResult ul li").each(function(idx,obj){
			var job = $(obj);
			
			str += "<input type = 'hidden' name = 'attachList["+idx+"].uuid' value='"+job.data("uuid")+"'>";
			str += "<input type = 'hidden' name = 'attachList["+idx+"].uploadPath' value='"+job.data("path")+"'>";
			str += "<input type = 'hidden' name = 'attachList["+idx+"].fileName' value='"+job.data("filename")+"'>";
			str += "<input type = 'hidden' name = 'attachList["+idx+"].fileType' value='"+job.data("type")+"'>";
		})
		
		console.log("form 태그 삽입 전");
		console.log(str);
		
		// 폼 보내기
		$("form").append(str).submit();
	})
	
	// X 버튼 클릭 시 첨부 파일 삭제
	// 이벤트 위임
	$(".uploadResult").on("click","button",function(){
		// button 태그의 data- 속성 가져오기 (뒤에거를 data() 해서 괄호 안에 넣기)
		let targetFile = $(this).data("file");
		let type = $(this).data("type");
		
		// span의 태그가 속해있는 li 태그 가져오기
		let targetLi = $(this).closest("li");
		
		$.ajax({
			url:'/deleteFile',
			data:{
				fileName:targetFile,
				type:type
			},
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			type:'post',
			success:function(result){
				console.log(result);
				$(":file").val(""); // 남는 파일명 제거
				// li 태그 제거
				targetLi.remove();
			}
		})
	})
	
})