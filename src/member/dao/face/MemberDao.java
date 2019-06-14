package member.dao.face;

import java.util.List;

import dto.Exit;
import dto.Member;
import util.Paging;

public interface MemberDao {

	public Member selectMemberByUserid(Member member); // userid로 회원정보 조회
	
	public int selectCntMemberByUserid(Member member); // login시 해당 id의 회원이 존재하는지 검사
	
	public void insert(Member member); // 회원가입
	
	public void update(Member member); // 회원정보 수정
	
	public int selectCntAll(String nickname); // 페이징을 위한 글 수 반환

	public List selectAll(Paging paging, String nickname); // 회원 작성 게시글 목록 반환
	
	public boolean pwCheck(Member member); // 비밀번호 중복검사
	
	public void pwUpdate(Member member); // 비밀번호 변경
	
	public boolean nicknameCheck(String nickname); // 닉네임 중복검사
	
	public String isMyNickname(String nickname); // 닉네임 중복시 내 닉네임인지 확인
	
	public void deleteMember(String userid);
	
	public void exitReason(Exit exit);
}
