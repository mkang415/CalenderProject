package board.dao.face;

import java.util.List;

import dto.Board;
import dto.Schedule;
import util.Paging;

public interface BoardDao {

	//테이블 전체 조회
	public List selectAll(Paging paging);
	
	//게시글 수 조회
	public int selectCntAll();

	//게시글 조회수 +1
	public void updateHit(Board viewBoard);

	//게시글 상세보기
	public Board selectBoardByBoardno(Board viewBoard);
		
	//다음 게시글 번호 반환
	public int selectBoardno();
	
	//게시글 입력
	public void insert(Board board);

	//게시글 수정
	public void update(Board board);

	//게시글 삭제
	public void delete(Board board);

//	public List getList(String event, String team, String region);

	
	// 내가 쓴 글 보기
	public void myBoard(Board board);
	
	//scheduleno로 insertdate?
	public int scheduleno(String team, String gamedate);
}
