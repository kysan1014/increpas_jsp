<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글게시판</title>
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls/css/cls.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/cls/js/reBoardList.js"></script>
</head>
<body>
	<form method="POST" id="frm1" name="frm1">
		<input type="hidden" name="bno" id="dbno">
		<input type="hidden" name="nowPage" id="nowPage" value="${PAGE.nowPage}">
	</form>

	<div class="w3-content w3-center mw650">
		<h1 class="w3-pink w3-padding">댓글게시판</h1>
		<div class="w3-row w3-border-bottom pdb10">
			<div class="w3-col m2 w3-left pdh1">
					<span class="w3-col w3-button w3-small w3-green w3-hover-lime w3-left mt0 btnBox" id="hbtn">Home</span>
			</div>
			<c:if test="${empty SID}">
				<div class="w3-col m2 w3-right pdh1">
					<span class="w3-col w3-button w3-small w3-orange w3-hover-deep-orange mt0 btnBox" id="lbtn">로그인</span>
				</div>
				<div class="w3-col m2 w3-right pdh1">
					<span class="w3-col w3-button w3-small w3-blue w3-hover-aqua w3-right mt0 btnBox" id="jbtn">회원가입</span>
				</div>
			</c:if>
			<c:if test="${not empty SID}">
				<div class="w3-col m2 w3-right pdh1">
					<span class="w3-col w3-button w3-small w3-red w3-hover-orange w3-right mt0 btnBox" id="obtn">로그아웃</span>
				</div>
				<div class="w3-col m2 w3-right pdh1">
					<span class="w3-col w3-button w3-small w3-pink w3-hover-orange w3-right mt0 btnBox" id="wbtn">글쓰기</span>
				</div>
			</c:if>
		</div>
		
		<c:if test="${not empty SID}">
		 	<div id="wmodal" class="w3-modal">
			    <div class="w3-modal-content">
			      <div class="w3-container">
			        <span class="w3-button w3-display-topright" id="mcbtn">&times;</span>
					<div class="w3-col w3-margin-top pb10" >
						<div class="w3-col pd10">
							<div class="w3-col w3-border-bottom w3-left-align w3-text-grey mb10">글 작 성</div>
							<div class="w3-col">
								<div class="w3-col inblock avtbox100 pdr10">
									<img src="/cls/img/avatar/${AVTIMG}" class="avtimg100 w3-border">
								</div>
								<form class="w3-rest" id="wfrm" name="wfrm">
									<input type="hidden" name="avatar" value="${AVTIMG}">
									<input type="hidden" name="id" value="${SID}">
									<input type="hidden" name="bno" id="tno">
									<input type="hidden" name="nowPage" id="nowPage" value="${PAGE.nowPage}">
									<textarea class="w3-input w3-border h72" style="resize: none;" placeholder="인삿말을 작성하세요!" id="body" name="body"></textarea>
									<div class="w3-col pdh1 mt5">
										<span class="w3-col m2 w3-left w3-button w3-small w3-lime w3-hover-light-green btn" id="rbtn">reset</span>
										<span class="w3-col m2 w3-right w3-button w3-small w3-pink w3-hover-purple btn" id="wrbtn">글 등 록</span>
									</div>
								</form>
							</div>
						</div>
					</div>
			      </div>
			    </div>
		    </div>
		</c:if>
		
		<!-- 댓글 리스트 -->
		<div class="w3-row w3-padding w3-card-4" style="padding-bottom: 20px!important;">
			<c:forEach var="data" items="${LIST}">
					<div class="w3-col w3-border-bottom pdb10">
						<div class="w3-col m${12 - data.step * 2} w3-right pd10 w3-margin-top">
						
						<!-- 댓글 표시 영역 -->
							<div class="w3-col">
								<div class="w3-col inblock avtbox100 pdr10">
									<img src="/cls/img/avatar/${data.avatar}" class="avtimg100 w3-border">
								</div>
								<div class="w3-rest">
									<div class="w3-col w3-border-bottom w3-border-blue w3-left w3-text-grey mb5 pb3">
										<div class="w3-cell w3-left">작성자 : ${data.id}</div>
										<div class="w3-cell w3-right"><small>${data.sdate}</small></div>
									</div>
									<div class="w3-col w3-padding w3-left-align w3-display-container">
										<div class="w3-col ft12">${data.body}</div>
										<div class="w3-col mt10 mb5">
											<c:if test="${SID ne data.id}">
												<div class="w3-card-4 w3-button w3-small w3-blue w3-hover-aqua w3-right w3-round rebtn" id="${data.bno}" style="padding: 0 10px;">답글달기</div>
											</c:if>
											<c:if test="${SID eq data.id}">
												<div class="w3-card-4 w3-button w3-small w3-red w3-hover-amber w3-left w3-round dbtn" id="d${data.bno}" style="padding: 0 10px;">글삭제</div>
												<div class="w3-card-4 w3-button w3-small w3-purple w3-hover-pink w3-right w3-round ebtn" id="e${data.bno}" style="padding: 0 10px;">글수정</div>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
			</c:forEach>
		</div>
		
		<!-- 페이징 버튼 -->
		<div class="w3-col w3-margin-top">
			<form method="POST" action="/cls/reBoard/reBoardList.cls" name="pfrm" id="pfrm" class="w3-bar w3-border w3-round">
				<input type="hidden" id="nowPage" name="nowPage">
				<!-- 이전 버튼 처리 -->
			  	<c:if test="${PAGE.startPage != 1}">
			  		<span class="w3-bar-item w3-button pagebtn" id="${PAGE.startPage - 1}">&laquo;</span>
			  	</c:if>
			  
			  	<c:forEach var="page" begin="${PAGE.startPage}" end="${PAGE.endPage}">
			  		<span class="w3-bar-item w3-button pagebtn" id="${page}">${page}</span>
			  	</c:forEach>
			  
				<!-- 다음 버튼 처리 -->
			  	<c:if test="${PAGE.endPage != PAGE.totalPage}">
			  		<span class="w3-bar-item w3-button pagebtn" id="${PAGE.endPage + 1}">&raquo;</span>
			  	</c:if>
			</form>
		</div>
	</div>
</body>
</html>