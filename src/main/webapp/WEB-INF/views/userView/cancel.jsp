<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/user/orderSearch.js"></script>
<script type="text/javascript" src="js/user/sideCategory.js"></script>
<link href="css/user/sideCategory.css" type="text/css" rel="stylesheet"/>
<link href="css/user/orderSearch.css" type="text/css" rel="stylesheet"/>
<!-- <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script> -->
<script type="text/javascript">
	$(function(){
		$("#array").change(function(){
			var url="orderSearch.do?list_id="+$(this).val();
			$(location).attr('href', url);
		})
		
		var list_id=$('input[type="hidden"]').val();
		if(list_id=="") list_id="0";
		$("#array option:selected").removeAttr("selected"); 
 		$("#array").val(list_id).attr("selected", "selected");
 		
 		$("#change_exchange").click(function(){
			alert("이미 환불 처리 요청이된 주문입니다.");
				
		});
	 		
 		$("#change_cancel").click(function(){
 			alert("배송을 처음부터 다시 시작하겠습니다.");
 			var order_number=$(this).parents().find("#order_number").text();
			var url="statusChange.do?order_number="+order_number+"&status=1&pageStatus=4";
			$(location).attr('href', url);
			
 		});
	});
		
</script>
</head>

<body>
	<div class="widthline">
		<div class="path_hy">홈 > 주문관리 > 취소/반품/교환</div>
		<!-- 사이드메뉴 -->
		<div class="side_mh">
		<div class="category_mh">
			<div>
			<input type="hidden" name="listId" value="${list_id }"/>
				<!-- 주문관리 -->
				<div class="orderManager_mh">
					<div class="title_mh">
						<h3>주문관리</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<ul>
							<li><a href="orderSearch.do">주문/배송 조회</a></li>
							<li><a href="cancel.do">취소/반품/교환 내역</a></li>
							<li><a href="buyList.do">구매내역</a></li>
						</ul>
					</div>
				</div>
	
				<!-- 관심리스트 -->
				<div class="wishList_mh">
					<div class="title_mh">
						<h3>관심리스트</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<ul>
							<li><a href="nearestList.do">최근본 상품</a></li>
							<li><a href="wishList.do">위시리스트</a></li>
							<li><a href="buyList.do">장바구니</a></li>
						</ul>
					</div>
				</div>
	
				<!-- 고객센터 -->
				<div class="client_mh">
					<div class="title_mh">
						<h3>고객센터</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
						<p class="faq_sc">FAQ</p> 
						<ul>
							<li><a href="CustomerService_faq.do">회원</a></li>
							<li><a href="CustomerService_faq.do">상품</a></li>
							<li><a href="CustomerService_faq.do">입금/결제</a></li>
							<li><a href="CustomerService_faq.do">취소/교환/환불</a></li>
							<li><a href="CustomerService_faq.do">주문</a></li>
							<li><a href="CustomerService_faq.do">배송</a></li>
							<li><a href="CustomerService_faq.do">적립</a></li>
						</ul>
	
						<p class="consulting_sc">1:1 상담</p>
						<ul>
							<li><a href="CustomerService_consulting.do">1:1 상담하기</a></li>
							<li><a href="CustomerService_consultingList.do">1:1 상담내역</a></li>
						</ul>
					</div>
				</div>
	
				<!-- 영업점 안내 -->
				<div class="map_mh">
					<div class="title_mh">
						<h3>영업점 안내</h3>
						<img src="images/down.png">
						<img src="images/up.png">
					</div>
					<div class="sub_mh">
	
						<ul>
							<li><a href="Introduction.do">회사 소개</a></li>
							<li><a href="Map.do">매장 소개</a></li>
						</ul>
	
					</div>
				</div>
			</div>
		</div>

		<div class="category_time_mh">
			<div style="text-align: center;">
				<h3>고객센터</h3>
				<h2>0000-0000</h2>
			</div>
			<div style="text-align: center;">월~금 09:00 ~ 18:00</div>
			<div style="text-align: center;">(토요일,일요일,공휴일 휴무)</div>
		</div>
		</div>
		<div class="con_hy">
			<div class="con_info_hy">
				<div><h2 class="h2_hy">아이디님의 정보</h2></div>
				<div class="con_info1_hy">
					<div class="info_head_hy">
						<div>진행중 주문 건</div>
						<div class="info_box_hy"><span><a href="ordering.do">${orderingCount }</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>배송중</div>
						<div class="info_box_hy"><span><a href="delivery.do">${deliveryCount}</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>환불/취소</div>
						<div class="info_box_hy"><span><a href="cancel.do">${cancelCount }</a></span></div>
					</div>
					<div class="info_head_hy">
						<div>포인트</div>
						<div class="info_box_hy"><span><a href="">${point }</a></span></div>
					</div>
				</div>
			</div>
			<div>
				<h2 class="h2_hy">취소/반품/교환</h2>	 
				<div class="search_box_hy">
					<div><span>기간별 조회</span></div>
					<div>
						<span class="block_hy">
							<button class="block_btn_hy">15일이내</button>
							<button class="block_btn_hy">1개월</button>
							<button class="block_btn_hy">3개월</button>
							<button class="block_btn_hy">6개월</button>
						</span>
					</div>
					<div>
						<span class="block_day_hy">
							<span id="fromYear"><select id="fromYear" name="fromYear" ><option value="2008">2008</option><option value="2009">2009</option><option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option><option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option><option value="2018" selected="selected">2018</option></select></span>
							<span id="fromMonth"><select id="fromMonth" name="fromMonth"><option value="1" selected="selected">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option></select></span>
							<span id="fromDay"><select id="fromDay" name="fromDay"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20" selected="selected">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option></select></span> 
							<span>-</span>
							<span id="toYear"><select id="toYear" name="toYear"><option value="2008">2008</option><option value="2009">2009</option><option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option><option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option><option value="2018" selected="selected">2018</option></select></span>
							<span id="toMonth"><select id="toMonth" name="toMonth"><option value="1" selected="selected">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option></select></span>
							<span id="toDay"><select id="toDay" name="toDay"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23" selected="selected">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option></select></span>
							<span><button class="block_btn_hy">조회</button></span>
						</span>
					</div>
					<p>조회기간은 최대  6개월 단위로 설정하실 수 있으며, 주문번호를 클릭하시면 주문에 대한 상세정보를 보실 수 있습니다.</p>
				</div>
				
				<div class="downList_hy">
					<select class="downList_sel_hy" id="array">
						<option value="0" selected="selected">최근주문 순</option>
						<option value="1" >상품이름 순</option>
						<option value="2" >주문가격 순</option>
					</select>
				</div>
				<div class="search_faqlist_header_ej table_jm">
					<div>주문번호</div>
					<div>상품명</div>
					<div>수량</div>
					<div class="search_list_size_hy">주문일자</div>
					<div class="search_list_size_hy">수령예상일</div>
					<div class="search_list_size_hy">현재상태</div>
					<div class="search_list_size_hy">주문금액</div>
					<div class="search_list_size_hy">교환/환불</div>
				</div>
				
				<c:if test="${cancelCount==0}">
					<h3 style="text-align: center;">고객님의 주문내역이 존재하지 않습니다</h3>
				</c:if>
				<c:if test="${cancelCount>0 }">
					<div class="recentOrder_hy">
						<div class="list_hy">
							<c:forEach var="cancelList" items="${cancelList}">
								<div class="search_list_con_hy table_jm">
									<div id="order_number"><a href="detailOrder.do?order_number=${cancelList.order_number}">${cancelList.order_number }</a></div>
									<div><a href="detailOrder.do?order_number=${cancelList.order_number}">${cancelList.title }</a></div>
									<div>${cancelList.goods_account }권</div><!-- search_list_size_hy -->
									<div class=""><fmt:formatDate value="${cancelList.order_date}" pattern="yyyy-MM-dd"/></div>
									<div class=""><fmt:formatDate value="${cancelList.maybe_date}" pattern="yyyy-MM-dd"/></div>
									<div class="">${cancelList.status }</div>
									<div class=""><strong>${cancelList.total_price }원</strong></div>
									<div class=""><button class="block_btn_hy" id="change_exchange">환불</button><button class="block_btn_hy" id="change_cancel">취소</button></div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
				
			</div>
			<div class="page_count_hy">
				<c:if test="${cancelCount>0 }">
					<fmt:parseNumber var="pageCount" value="${cancelCount/pageSize+(cancelCount%pageSize==0 ? 0:1)}" integerOnly="true"/>
					<c:set var="pageBlock" value="${5 }"/>
				
					<fmt:parseNumber var="rs" value="${(pageNumber-1)/pageBlock }" integerOnly="true"/>
					<c:set var="startPage" value="${rs*pageBlock +1 }"/>
					
					<c:set var="endPage" value="${startPage+pageBlock -1 }"/>
					<c:if test="${endPage >pageCount }">
						<c:set var="endPage" value="${pageCount }"/>
					</c:if>
			
					<c:if test="${startPage> pageBlock }">
						<a href="cancel.do?cancel_pageNumber=${startPage-pageBlock }&list_id=${list_id}">[이전]</a>
					</c:if>
				
					<c:forEach var="i" begin="${startPage}" end="${endPage }">
						<a href="cancel.do?cancel_pageNumber=${i }&list_id=${list_id}">${i }</a>
					</c:forEach>
				
					<c:if test="${endPage< pageCount }">
						<a href="cancel.do?cancel_pageNumber=${startPage + pageBlock }&list_id=${list_id}">[다음]</a>
					</c:if>
				
				</c:if>
			</div>
			<div class="underimg_hy"><img src="images/info2.png" style="width:85%;"/></div>
		</div>
	</div>
</body>
</html>