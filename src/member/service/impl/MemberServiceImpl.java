package member.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.Exit;
import dto.Member;
import member.dao.face.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.service.face.MemberService;
import util.Paging;

public class MemberServiceImpl implements MemberService{

	MemberDao memberdao = new MemberDaoImpl();
	
	@Override
	public void join(HttpServletRequest req) { // 회원가입 서비스 메소드
		
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
	public Member select(HttpServletRequest req) { // mypage - myboardlist 조회 서비스
		
		HttpSession session = req.getSession();
		
		Member member = new Member();
		
		member.setUserid((String)session.getAttribute("userid"));
		
		return memberdao.selectMemberByUserid(member);
	}

	@Override
	public void update(HttpServletRequest req) { // 개인정보 수정 서비스
		
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
	public Member getLoginMember(HttpServletRequest req) { // 로그인을 위한 회원 아이디, 비밀번호 일치 여부 확인 서비스
		Member member = new Member();
		
		member.setUserid(req.getParameter("userid"));
		member.setPassword(req.getParameter("password"));		

		return member;
	}

	@Override
	public boolean login(Member member) { // 로그인을 위해 DB에 회원 정보가 있는지 조회하는 서비스
		
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
	public Member getMemberByUserid(Member member) { // 로그인 시 유저의 정보를 반환하기 위한 서비스

		Member loginmember = new Member();
		
		loginmember = memberdao.selectMemberByUserid(member);
		
		return loginmember;
	}

	@Override
	public Paging getCurPage(HttpServletRequest req, String nickname) { // 페이징

		// 전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");

		int curPage = 0;

		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		// 전체 게시글 수
		int totalCount = memberdao.selectCntAll(nickname);

		// 페이징 객체 생성
		Paging paging = new Paging(totalCount, curPage);
		System.out.println(paging); // test

		return paging;
	}

	@Override
	public List getList(Paging paging, String nickname) { // 페이징

		List boardList = memberdao.selectAll(paging, nickname);
		
		return boardList;
	}

	@Override
	public boolean pwUpdate(HttpServletRequest req) { // 비밀번호 변경 서비스

		boolean res = false;
		
		Member member = new Member();
		
		HttpSession session = req.getSession();
		
		member.setUserid((String)session.getAttribute("userid"));
		member.setPassword(req.getParameter("pwConfirm"));
		
		if(memberdao.pwCheck(member)==true) {

			member.setPassword(req.getParameter("newPw"));
			memberdao.pwUpdate(member);
			res=true;
		} else {
			res=false;
		}
		
		return res;
	}
	
	@Override
	public boolean pwCheck(HttpServletRequest req) { // 회원탈퇴시 비밀번호 확인 메소드

		Member member = new Member();
		
		boolean res = false;
		
		try {
			req.setCharacterEncoding("utf-8");

			HttpSession session = req.getSession();

			member.setUserid((String)session.getAttribute("userid"));
			member.setPassword(req.getParameter("password"));
			System.out.println(member.toString());
			res = memberdao.pwCheck(member);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public boolean nicknameCheck(String nickname) { // 회원가입, 회원정보 수정을 위해 닉네임 중복 여부를 판단하는 서비스
		return memberdao.nicknameCheck(nickname);
	}

	@Override
	public boolean isMyNickname(String nickname, String userid) { // 회원정보 수정 시 중복검사 후 자신의 아이디인지 확인하기 위한 서비스

		String id = memberdao.isMyNickname(nickname);
		
		boolean res = false;
		
		if(id!=null&&userid.equals(id)) {
			res=true;
		}
		
		return res;
	}

	@Override
	public void memberResign(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		

		
		Exit exitReason = new Exit();

		String userid=(String)session.getAttribute("userid");
		String reason = req.getParameter("reason");
		
		exitReason.setExitid(userid);
		exitReason.setExitreason(reason);
		
		memberdao.exitReason(exitReason);
		
		memberdao.deleteMember(userid);
	}


	
}
