package manage.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Member;
import util.Paging;

public interface ManageMemberService {

	public Paging getCurPage(HttpServletRequest req);

	public List getList(Paging paging);
	
	public Member getUserid(HttpServletRequest req);

	public int getReply(HttpServletRequest req);

	public int getcntBoard(HttpServletRequest req);

	
}
