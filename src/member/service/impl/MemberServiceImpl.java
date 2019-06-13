package member.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.Member;
import member.dao.face.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.service.face.MemberService;
import util.Paging;

public class MemberServiceImpl implements MemberService{

	MemberDao memberdao = new MemberDaoImpl();
	
	@Override
	public void join(HttpServletRequest req) {
		
		Member member = new Member();
		
		try {
			
			req.setCharacterEncoding("utf-8");
			member.setUserid(req.getParameter("email"));
			member.setPassword(req.getParameter("password"));
			member.setNickname(req.getParameter("nickname"));
			member.setAge(Integer.parseInt(req.getParameter("age")));
			member.setGender(req.getParameter("gender"));
			
			memberdao.insert(member);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Member select(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		Member member = new Member();
		
		member.setUserid((String)session.getAttribute("userid"));
		
		return memberdao.selectMemberByUserid(member);
	}

	@Override
	public void update(HttpServletRequest req) {
		
		Member member = new Member();
		
		HttpSession session = req.getSession();
		
		try {
			req.setCharacterEncoding("utf-8");
		
			String uid = (String)session.getAttribute("userid");
			
			member.setUserid(uid);
			member.setAge(Integer.parseInt(req.getParameter("age")));
			member.setGender(req.getParameter("gender"));
			member.setNickname(req.getParameter("nickname"));
			member.setTeamname(req.getParameter("teamname"));
			member.setIntroduce(req.getParameter("introduce"));
			
			memberdao.update(member);
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Member getLoginMember(HttpServletRequest req) {
		Member member = new Member();
		
		member.setUserid(req.getParameter("userid"));
		member.setPassword(req.getParameter("password"));		

		return member;
	}

	@Override
	public boolean login(Member member) {
		
		boolean res = false;
		int cnt = memberdao.selectCntMemberByUserid(member);
		
		if(cnt==1) {
			res = true;
		} else {
			res = false;
		}
		
		return res;
		
	}
	
	@Override
	public Member getMemberByUserid(Member member) {

		Member loginmember = new Member();
		
		loginmember = memberdao.selectMemberByUserid(member);
		
		return loginmember;
	}

	@Override
	public Paging getCurPage(HttpServletRequest req, String userid) {

		// 전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");

		int curPage = 0;

		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		// 전체 게시글 수
		int totalCount = memberdao.selectCntAll(userid);

		// 페이징 객체 생성
		Paging paging = new Paging(totalCount, curPage);
		System.out.println(paging); // test

		return paging;
	}

	@Override
	public List getList(Paging paging, String userid) {

		List boardList = memberdao.selectAll(paging, userid);
		
		return boardList;
	}

	@Override
	public boolean pwUpdate(HttpServletRequest req) {

		boolean res = false;
		
		Member member = new Member();
		
		HttpSession session = req.getSession();
		
		member.setUserid((String)session.getAttribute("userid"));
		member.setPassword(req.getParameter("pwConfirm"));
		
		if(memberdao.pwCheck(member)==true) {
			memberdao.pwUpdate(member);
			res=true;
		} else {
			res=false;
		}
		
		return res;
	}

	@Override
	public boolean nicknameCheck(String nickname) {
		return memberdao.nicknameCheck(nickname);
	}

	@Override
	public boolean isMyNickname(String nickname, String userid) {

		String id = memberdao.isMyNickname(nickname);
		
		boolean res = false;
		
		if(id!=null&&userid.equals(id)) {
			res=true;
		}
		
		return res;
	}
	
		


}
