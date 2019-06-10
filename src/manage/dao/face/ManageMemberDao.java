package manage.dao.face;

import java.util.List;

import dto.Member;
import util.Paging;

public interface ManageMemberDao {

	public List selectAll(Paging paging);

	public int selectCntAll();

	public Member selectMemberByTeamname(Member viewMember);

}
