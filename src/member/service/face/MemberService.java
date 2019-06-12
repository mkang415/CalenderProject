package member.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Member;
import util.Paging;

public interface MemberService {

	public Member getLoginMember(HttpServletRequest req); // 로그인 입력 폼에서 전달받은 파라미터를 멤버객체로 반환하는 메소드
	
	public Member getMemberByUserid(Member member); // getLoginMember의 Member객체 데이터와 일치하는 회원의 정보를 반환할 메소드
	
	public boolean login(Member member); // 로그인 정보를 전달할 메소드

	public void join(HttpServletRequest req); // 회원가입 진행할 메소드

	public Member select(HttpServletRequest req); // 회원정보 조회할 메소드

	public void update(HttpServletRequest req); // 회원정보 수정 메소드
	
	public Paging getCurPage(HttpServletRequest req, String userid);

	public List getList(Paging paging, String userid);
	
	public boolean pwUpdate(HttpServletRequest req);
	
	public boolean nicknameCheck(String nickname);
}
