/**
 * read.jsp 스크립트
 */

$(function(){
	
	// operForm 가져오기
	let operForm = $("#operForm");
	
	// list 버튼 클릭 시 /board/list로 이동
	// $("[type = "reset"]")
	// $(":reset")
//	$(".btn-info").click(function(){
//		location.href = "/board/list";
//	})

	$(".btn-info").click(function(){
		
		// operForm 안의 bno 태그 제거
		operForm.find("input[name='bno']").remove();
		
		// operForm action 수정
		operForm.attr("action", "/board/list")
		
		// operForm 보내기
		operForm.submit();		  
		
	})
	
	// modify 버튼 클릭 시 operForm 이동
	$(".btn-default").click(function(){
		operForm.submit();
	})
	
	// 첨부파일------------------------------------------------------------------------------
	
	// 첨부파일 가져오기 - 무조건 실행
	$.getJSON({
		url:'getAttachList',
		data:{
			bno:bno
		},
		success:function(data){
			console.log(data);
			showUploadFile(data);
		}
	})
	
	function showUploadFile(result){
		// 업로드 결과 영역 가져오기
		let uploadResult = $(".uploadResult ul");
		
		let str = "";
		
		$(result).each(function(idx,obj){
			
			if(obj.fileType){ // 이미지 파일
				// 썸네일 이미지 보여주기
				// 썸네일 이미지 경로
				let fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
				
				// 원본 파일 이미지 경로
				let oriPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
				oriPath = oriPath.replace(new RegExp(/\\/g),"/");
			
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str += "<a href = \"javascript:showImage(\'"+oriPath+"\')\">"; // 역슬래시로 여러 쌍따옴표 나올 때 이것도 쌍따옴표로 해달라고 표시해주는 것 \", \' 홑따옴표도 마찬가지로
				str += "<img src='/display?fileName="+fileCallPath+"'></a>";
				str += "<div>"+obj.fileName;
				str += "</div></li>";	
				
			}else{ // txt 파일
				
				// 다운로드 경로
				let fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
			
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str += "<a href='/download?fileName="+fileCallPath+"'>";
				str += "<img src='/resources/img/attach.png'></a>";
				str += "<div>"+obj.fileName;
				str += "</div></li>";	
			}
		});
		
		console.log("업로드 파일 경로");
		console.log(str);
		
		uploadResult.append(str);
	} // showUploadFile 종료
	
})