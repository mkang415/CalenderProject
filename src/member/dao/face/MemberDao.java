package member.dao.face;

import java.util.List;

import dto.Member;
import util.Paging;

public interface MemberDao {

	public Member selectMemberByUserid(Member member); // userid로 회원정보 조회
	
	public int selectCntMemberByUserid(Member member); // login시 해당 id의 회원이 존재하는지 검사
	
	public void insert(Member member); // 회원가입
	
	public void update(Member member); // 회원정보 수정
	
	public int selectCntAll(String userid);

	public List selectAll(Paging paging, String userid);
	
	public boolean pwCheck(Member member);
	
	public void pwUpdate(Member member);
	
	public boolean nicknameCheck(String nickname);
	
	public String isMyNickname(String nickname);
}
