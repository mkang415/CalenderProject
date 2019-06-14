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
	
	public Paging getCurPage(HttpServletRequest req, String userid); // 페이징 객체 반환 메소드

	public List getList(Paging paging, String userid); // 회원 작성 게시글 리스트 반환
	
	public boolean pwUpdate(HttpServletRequest req); // 비밀번호 변경
	
	public boolean pwCheck(HttpServletRequest req); // 비밀번호 중복검사
	
	public boolean nicknameCheck(String nickname); // 닉네임 중복검사
	
	public boolean isMyNickname(String nickname, String userid); // 회원정보 변경 - 닉네임 중복 시 내 닉네임인지 검사
	
	public void memberResign(HttpServletRequest req);
}
