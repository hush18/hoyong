/**
 * 
 */

$(function(){
	$("#advice").click(function(){
		$("#sub_advice").children().not(":first").remove();
		if($("#advice option:selected").val()=="회원"){
			$("#sub_advice").append("<option value='회원가입'>회원가입</option>");
			$("#sub_advice").append("<option value='회원정보확인/수정'>회원정보확인/수정</option>");
			$("#sub_advice").append("<option value='회원탈퇴'>회원탈퇴</option>");
			$("#sub_advice").append("<option value='휴먼계정'>휴먼계정</option>");
		}else if($("#advice option:selected").val()=="상품"){
			$("#sub_advice").append("<option value='상품불량'>상품불량</option>");
			$("#sub_advice").append("<option value='입고/품절/절판'>입고/품절/절판</option>");
			$("#sub_advice").append("<option value='상품정보/가격'>상품정보/가격</option>");
		}else if($("#advice option:selected").val()=="입금/결제"){
			$("#sub_advice").append("<option value='신용카드'>신용카드</option>");
			$("#sub_advice").append("<option value='핸드폰 결제'>핸드폰 결제</option>");
			$("#sub_advice").append("<option value='실시간 계좌이체'>실시간 계좌이체</option>");
			$("#sub_advice").append("<option value='직접 입금'>직접 입금</option>");
		}else if($("#advice option:selected").val()=="취소/교환/환불"){
			$("#sub_advice").append("<option value='취소/교환/환불 문의'>취소/교환/환불 문의</option>");
			$("#sub_advice").append("<option value='취소/교환/환불 신청'>취소/교환/환불 신청</option>");
			$("#sub_advice").append("<option value='취소/교환/환불 취소'>취소/교환/환불 취소</option>");
		}else if($("#advice option:selected").val()=="주문"){
			$("#sub_advice").append("<option value='주문조회'>주문조회</option>");
			$("#sub_advice").append("<option value='주문변경'>주문변경</option>");
			$("#sub_advice").append("<option value='주문취소'>주문취소</option>");
		}else if($("#advice option:selected").val()=="배송"){
			$("#sub_advice").append("<option value='배송문의'>배송문의</option>");
			$("#sub_advice").append("<option value='배송/출고예정일'>배송/출고예정일</option>");
		}else if($("#advice option:selected").val()=="적립"){
			$("#sub_advice").append("<option value='포인트문의'>포인트문의</option>");
			$("#sub_advice").append("<option value='포인트적립'>포인트적립</option>");
			$("#sub_advice").append("<option value='포인트사용'>포인트사용</option>");
			$("#sub_advice").append("<option value='포인트소멸'>포인트소멸</option>");
		}
	});
});

