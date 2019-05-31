package board.dao.face;

import java.util.List;

import dto.Board;

public interface BoardDao {

	//테이블 전체 조회
	public List selectAll();

	//게시글 조회수 +1
	public void updateHit(Board viewBoard);

	//게시글 상세보기
	public Board selectBoardByBoardno(Board viewBoard);
	
	//게시글 입력
	public void insert(Board board);

	//다음 게시글 번호 반환
	public int selectBoardno();
	
	
	
	

}
