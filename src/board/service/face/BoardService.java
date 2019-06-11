package board.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Board;
import util.Paging;

public interface BoardService {

	//게시글 리스트 조회
	public List getList(Paging paging);
	
	//요청파라미터 curPage 파싱
	public Paging getCurPage(HttpServletRequest req);

	//요청파라미터에서 boardno 파싱
	public Board getBoardno(HttpServletRequest req);
	
	//상세보기 게시글 조회
	public Board view(Board viewBoard);

	//게시글 작성
	public void write(HttpServletRequest req);

	//글 작성자인지 판단하기
	public boolean checkWriter(HttpServletRequest req);

	//게시글 수정
	public void update(HttpServletRequest req);

	//게시글 삭제
	public void delete(Board board);

	
	

	

}
