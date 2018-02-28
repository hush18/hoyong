package com.team3.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.team3.admin.cst.dto.AdminCstDto;
import com.team3.admin.faq.dto.AdminFaqDto;
import com.team3.admin.nct.dto.AdminNctDto;
import com.team3.aop.LogAspect;
import com.team3.admin.map.dto.MapDto;
import com.team3.service.ServiceInterface;
import com.team3.user.book.dao.BookDao;
import com.team3.user.cst.dto.CstDto;
import com.team3.admin.map.dto.MapDto;
import com.team3.user.book.dto.BookDto;
import com.team3.user.book.dto.WriterDto;

import com.team3.user.member.dto.MemberDto;

@Controller
public class ProController {
	
	@Autowired
	private ServiceInterface service;

	// 여기부터 사용자
	// 스크롤배너 최근본상품 출력!! 후에 본인 컨트롤러도 밑의 위시리스트 출력 처럼 리턴값을 바꿔주세요~~
	public ModelAndView scroll(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();		//세션받기 ID
		
		if(session.getAttribute("mbId")!=null) {
			service.scrollBanner(mav);
		}
		return mav;
	}

	@RequestMapping(value = "/userMain.do", method = RequestMethod.GET)
	public ModelAndView userMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.getMainList(mav);
		
		return scroll(mav);
	}

	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.myPage(mav);

		return scroll(mav);
	}

	@RequestMapping(value = "/userPoint.do", method = RequestMethod.GET)
	public ModelAndView userPoint(HttpServletRequest request, HttpServletResponse response) {

		return scroll(new ModelAndView("userPointView.users"));
	}

	@RequestMapping(value = "/updateAccount.do", method = RequestMethod.GET)
	public ModelAndView updateAccount(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.updateAccount(mav);

		return scroll(mav);
	}

	@RequestMapping(value = "/updateAccount.do", method = RequestMethod.POST)
	public ModelAndView updateAccount(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDto", memberDto);
		service.updateAccountOk(mav);

		return scroll(mav);
	}

	@RequestMapping(value = "/deleteAccount.do", method = RequestMethod.GET)
	public ModelAndView deleteAccount(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("deleteAccount.empty");
	}

	@RequestMapping(value = "/deleteAccount.do", method = RequestMethod.POST)
	public ModelAndView deleteAccount(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberDto", memberDto);
		mav.addObject("request", request);
		service.deleteAccount(mav);

		return mav;
	}

	// 위시리스트 출력
	@RequestMapping(value = "/wishList.do", method = RequestMethod.GET)
	public ModelAndView wishList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.wishList(mav);

		return scroll(mav);
	}

	// 위시리스트에서 장바구니 이동
	@RequestMapping(value = "/wishListUp.do", method = RequestMethod.GET)
	public ModelAndView wishListUp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response", response);
		service.wishListUp(mav);
		return scroll(mav);
	}

	// 위시리스트에서 리스트 삭제
	@RequestMapping(value = "/wishListDel.do", method = RequestMethod.GET)
	public ModelAndView wishListDel(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.wishListDel(mav);
		return scroll(mav);
	}

	// 위시리스트로 Insert
	@RequestMapping(value = "/wishListInsert.do", method = RequestMethod.GET)
	public ModelAndView wishListInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		// String id=request.getSession("id");
		mav.addObject("request", request);

		service.wishListInsert(mav);

		return scroll(mav);
	}

	// 최근본상품 리스트 출력
	@RequestMapping(value = "/nearestList.do", method = RequestMethod.GET)
	public ModelAndView nearestList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.nearestList(mav);

		return scroll(mav);
		// return new ModelAndView("nearestList.users");
	}

	// 최근본상품에서 장바구니로 이동
	@RequestMapping(value = "/nearestUp.do", method = RequestMethod.GET)
	public ModelAndView nearestUp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.nearestUp(mav);
		// service.nearestInsert(mav);
		return scroll(mav);
	}

	// 최근본상품에서 리스트 삭제
	@RequestMapping(value = "/nearestDel.do", method = RequestMethod.GET)
	public ModelAndView nearestDel(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.nearestDel(mav);

		return scroll(mav);
	}

	@RequestMapping(value = "/loginMember.do", method = RequestMethod.GET)
	public ModelAndView loginMember(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("session", session);
		service.loginMember(mav);

		return mav;
	}

	@RequestMapping(value = "/naverCreateAccount.do", method = RequestMethod.GET)
	public ModelAndView naverCreateAccount(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Throwable {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("session", session);
		service.naverCreateAccount(mav);

		return mav;
	}

	@RequestMapping(value = "/facebookCreateAccount.do", method = RequestMethod.GET)
	public ModelAndView facebookCreateAccount(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Throwable {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("session", session);
		service.facebookCreateAccount(mav);

		return mav;
	}

	@RequestMapping(value = "/createAccount.do", method = RequestMethod.GET)
	public ModelAndView createAccount(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("createAccount.users");
	}

	@RequestMapping(value = "/createAccount.do", method = RequestMethod.POST)
	public ModelAndView createAccount(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("memberDto", memberDto);
		service.createAccountOk(mav);

		// LogAspect.logger.info(LogAspect.logMsg + request.getParameter("id"));
		// LogAspect.logger.info(LogAspect.logMsg + "멤버 디티오 : " + memberDto);
		return mav;
	}

	@RequestMapping(value = "/diap.do", method = RequestMethod.GET)
	public ModelAndView diap(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("diap.users");
	}

	@RequestMapping(value = "/findId.do", method = RequestMethod.GET)
	public ModelAndView findId(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("findId.empty");
	}

	@RequestMapping(value = "/findPwd.do", method = RequestMethod.GET)
	public ModelAndView findPwd(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("findPwd.empty");
	}
	
	@RequestMapping(value="/cart.do", method=RequestMethod.GET)
	public ModelAndView cart(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.cart(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/cartListDelete.do", method=RequestMethod.GET)
	public ModelAndView cartListDelete(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.cartListDelete(mav);
		
		return mav;
	}
		
	@RequestMapping(value="/orderSearch.do", method=RequestMethod.GET)
	public ModelAndView orderSearch(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.orderSearch(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/statusChange.do", method=RequestMethod.GET)
	public ModelAndView statusChange(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.statusChange(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/orderDelete.do", method=RequestMethod.GET)
	public ModelAndView orderDelete(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.orderDelete(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/cancel.do", method=RequestMethod.GET)
	public ModelAndView cancel(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.cancel(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/ordering.do", method=RequestMethod.GET)
	public ModelAndView ordering(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.ordering(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/delivery.do", method=RequestMethod.GET)
	public ModelAndView delivery(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.delivery(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/buyList.do", method=RequestMethod.GET)
	public ModelAndView buyList(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.buyList(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/CustomerService_main.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		service.getTopTen(mav);
		return mav;
	}

	@RequestMapping(value = "/CustomerService_consulting.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_consulting(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_consulting.users");
	}

	@RequestMapping(value = "/CustomerService_cstOk.do", method = RequestMethod.POST)
	public ModelAndView CustomerService_cstOk(HttpServletRequest request, HttpServletResponse response, CstDto cstDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		mav.addObject("cstDto", cstDto);
		service.cstOk(mav);

		return mav;
	}

	@RequestMapping(value = "/CustomerService_consultingList.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_consultingList(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_consultingList.users");
	}

	@RequestMapping(value = "/CustomerService_faq.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_faq(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		service.getFaq(mav);
		return mav;
	}

	@RequestMapping(value = "/CustomerService_order_search.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_order_search(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_order_search.empty");
	}

	@RequestMapping(value = "/CustomerService_question_search.do", method = RequestMethod.GET)
	public ModelAndView CustomerService_question_search(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("CustomerService_question_search.empty");
	}

	@RequestMapping(value = "/Map.do", method = RequestMethod.GET)
	public ModelAndView Map(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		service.userMapRead(mav);
		return mav;
	}

	@RequestMapping(value = "/Introduction.do", method = RequestMethod.GET)
	public ModelAndView Introduction(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("Introduction.users");
	}

	@RequestMapping(value = "/bookList.do", method = RequestMethod.GET)
	public ModelAndView bookList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);

		service.bookList(mav);

		mav.setViewName("bookList.users");
		return scroll(mav);
	}

	@RequestMapping(value = "/bookInfo.do", method = RequestMethod.GET)
	public ModelAndView bookInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);

		service.bookInfo(mav);

		mav.setViewName("bookInfo.users");
		return scroll(mav);
	}
		
	@RequestMapping(value="/detailOrder.do", method=RequestMethod.GET)
	public ModelAndView detailOrder(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.detailOrder(mav);
		
		return mav;
	}
	@RequestMapping(value = "/eventPopup.do", method = RequestMethod.GET)
	public ModelAndView popup(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("event_popup.empty");
	}

	@RequestMapping(value = "/newsfeed.do", method = RequestMethod.GET)
	public ModelAndView newsfeed(HttpServletRequest request, HttpServletResponse response) {
		service.newsfeedParsing(request, response);

		return null;
	}

	@RequestMapping(value = "/payment.do", method = RequestMethod.GET)
	public ModelAndView payment(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("payment.users");
	}

	@RequestMapping(value = "/addressList.do", method = RequestMethod.GET)
	public ModelAndView addressList(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("addressList.empty");
	}

	@RequestMapping(value = "/searchPwd.do", method = RequestMethod.GET)
	public ModelAndView searchPwd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("req", request);
		service.searchPwd(mav);

		return mav;
	}

	@RequestMapping(value = "/searchPwdOK.do", method = RequestMethod.GET)
	public ModelAndView pwd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.searchPwdOK(mav);
		return mav;
	}

	@RequestMapping(value = "/memberLoginOK.do", method = RequestMethod.POST)
	public ModelAndView memberLoginOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.memberLoginOK(mav);

		return mav;
	}

	@RequestMapping(value = "/zipcode.do", method = RequestMethod.GET)
	public ModelAndView zipcode(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.zipcode(mav);

		return mav;
	}

	@RequestMapping(value = "/findIdOK.do", method = RequestMethod.POST)
	public ModelAndView findIdOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.findIdOK(mav);

		return mav;
	}

	@RequestMapping(value = "logoutMember.do", method = RequestMethod.GET)
	public ModelAndView logoutMember(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("logoutMember.empty");
	}
	
	@RequestMapping(value = "diapOK.do", method = RequestMethod.POST)
	public ModelAndView diapOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.diapOK(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "searchHeader.do", method = RequestMethod.POST)
	public ModelAndView searchHeader(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("response", response);
		service.searchHeader(mav);
		return null;
	}

	// 여기부터 관리자
	// ================================================================================================================================================
	@RequestMapping(value = "adminBookSearch.do", method = RequestMethod.GET)
	public ModelAndView adminBookSearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminBookSearch(mav);
		mav.setViewName("adminBookSearch.admin");
		return mav;
	}

	@RequestMapping(value = "adminBookInsert.do", method = RequestMethod.GET)
	public ModelAndView adminBookInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminBookInsert.admin");
	}

	@RequestMapping(value = "adminBookInfo.do", method = RequestMethod.GET)
	public ModelAndView adminBookInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminBookInfo(mav);
		mav.setViewName("adminBookInfo.admin");
		return mav;
	}

	@RequestMapping(value = "adminBookUpdate.do", method = RequestMethod.POST)
	public ModelAndView adminBookUpdate(HttpServletRequest request, HttpServletResponse response, BookDto bookDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("bookDto", bookDto);
		service.adminBookUpdate(mav);
		mav.setViewName("adminBookUpdateOk.admin");
		return mav;
	}

	@RequestMapping(value = "adminWriterSearch.do", method = RequestMethod.GET)
	public ModelAndView adminWriterSearch(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminWriterSearch(mav);
		mav.setViewName("adminWriterSearch.adminEmpty");
		return mav;
	}

	@RequestMapping(value = "adminWriterInsert.do", method = RequestMethod.GET)
	public ModelAndView adminWriterInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminWriterInsert.adminEmpty");
	}

	@RequestMapping(value = "adminMemberManage.do", method = RequestMethod.GET)
	public ModelAndView adminMemberManage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		service.memberManage(mav);
		return mav;
	}

	@RequestMapping(value = "adminMap.do", method = RequestMethod.GET)
	public ModelAndView adminMap(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		service.readMap(mav);
		return mav;
	}

	@RequestMapping(value = "adminChange.do", method = RequestMethod.GET)
	public ModelAndView adminChange(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminChange.admin");
	}

	@RequestMapping(value = "adminDelivery.do", method = RequestMethod.GET)
	public ModelAndView adminDelivery(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminDelivery.admin");
	}

	@RequestMapping(value = "adminOrderSearch.do", method = RequestMethod.GET)
	public ModelAndView adminOrderSearch(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminOrderSearch.admin");
	}

	@RequestMapping(value = "adminSales.do", method = RequestMethod.GET)
	public ModelAndView adminSales(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		service.adminSales(mav);
		return mav;
		
	}

	@RequestMapping(value = "adminCstMain.do", method = RequestMethod.GET)
	public ModelAndView adminCstMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminCstMain(mav);
		return mav;
	}

	@RequestMapping(value = "adminCstInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminCstInsertOk(HttpServletRequest request, HttpServletResponse response,
			AdminCstDto adminCstDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		mav.addObject("adminCstDto", adminCstDto);

		service.adminCstInsertOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminCstUpdateOk.do", method = RequestMethod.POST)
	public ModelAndView adminCstUpdateOk(HttpServletRequest request, HttpServletResponse response,
			AdminCstDto adminCstDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("adminCstDto", adminCstDto);

		service.adminCstUpdateOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminCstDeleteOk.do", method = RequestMethod.GET)
	public ModelAndView adminCstDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminCstDeleteOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminFaqInsertOk(HttpServletRequest request, HttpServletResponse response,
			AdminFaqDto adminFaqDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		mav.addObject("adminFaqDto", adminFaqDto);

		service.adminFaqInsertOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqMain.do", method = RequestMethod.GET)
	public ModelAndView adminFaqMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminFaqMain(mav);
		return mav;
	}

	@RequestMapping(value = "adminFaqUpdate.do", method = RequestMethod.GET)
	public ModelAndView adminFaqUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqUpdate(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqUpdateOk.do", method = RequestMethod.POST)
	public ModelAndView adminFaqUpdateOk(HttpServletRequest request, HttpServletResponse response,
			AdminFaqDto adminFaqDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("adminFaqDto", adminFaqDto);

		service.adminFaqUpdateOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqTopInsert.do", method = RequestMethod.GET)
	public ModelAndView adminFaqTopInsert(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqTopInsert(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqTopDelete.do", method = RequestMethod.GET)
	public ModelAndView adminFaqTopDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminFaqTopDelete(mav);

		return mav;
	}

	@RequestMapping(value = "adminFaqDeleteOk.do", method = RequestMethod.GET)
	public ModelAndView adminFaqDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		service.adminFaqDeleteOk(mav);
		return mav;
	}

	@RequestMapping(value = "adminFaqInsert.do", method = RequestMethod.GET)
	public ModelAndView adminFaqInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminFaqInsert.admin");
	}

	@RequestMapping(value = "adminNctInsert.do", method = RequestMethod.GET)
	public ModelAndView adminNctInsert(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("adminNctInsert.admin");
	}

	@RequestMapping(value = "adminNctInsertOk.do", method = RequestMethod.POST)
	public ModelAndView adminNctInsertOk(HttpServletRequest request, HttpServletResponse response,
			AdminNctDto adminNctDto) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("request", request);
		mav.addObject("adminNctDto", adminNctDto);

		service.adminNctInsertOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminNctMain.do", method = RequestMethod.GET)
	public ModelAndView adminNctMain(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminNctMain(mav);

		return mav;
	}

	@RequestMapping(value = "adminNctDeleteOk.do", method = RequestMethod.GET)
	public ModelAndView adminNctDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminNctDeleteOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminNctUpdate.do", method = RequestMethod.GET)
	public ModelAndView adminNctUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.adminNctUpdate(mav);

		return mav;
	}

	@RequestMapping(value = "adminNctUpdateOk.do", method = RequestMethod.POST)
	public ModelAndView adminNctUpdateOk(HttpServletRequest request, HttpServletResponse response,
			AdminNctDto adminNctDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("adminNctDto", adminNctDto);

		service.adminNctUpdateOk(mav);

		return mav;
	}

	@RequestMapping(value = "adminMapOk.do", method = RequestMethod.POST)
	public ModelAndView adminMapInsert(HttpServletRequest request, HttpServletResponse response, MapDto mapDto)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("mapDto", mapDto);

		service.createMap(mav);
		return mav;
	}

	@RequestMapping(value = "adminMapUpdate.do", method = RequestMethod.POST)
	public ModelAndView adminMapUpdate(HttpServletRequest request, HttpServletResponse response, MapDto mapDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("mapDto", mapDto);

		service.updateMap(mav);
		return mav;
	}

	@RequestMapping(value = "adminMapDelete.do", method = RequestMethod.POST)
	public ModelAndView adminMapDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		service.deleteMap(mav);

		return mav;
	}
	
	@RequestMapping(value = "adminMemberDelete.do", method = RequestMethod.GET)
	public ModelAndView adminMemberDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("member_number", request.getParameter("member_number"));
		service.adminMemberDelete(mav);
		return mav;
	}
	
	@RequestMapping(value = "adminMemberDeleteOK.do", method = RequestMethod.GET)
	public ModelAndView adminMemberDeleteOK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		service.adminMemberDeleteOK(mav);
		return mav;
	}
	

}
