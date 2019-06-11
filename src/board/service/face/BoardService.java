package board.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Board;
import util.Paging;
import dto.Reply;

public interface BoardService {
	
	/**
	 * 게시글 리스트 조회
	 * 
	 * @param paging - 조회대상 페이징 객체
	 * @return 게시글을 조회한 결과
	 */
	public List getList(Paging paging);
	
	/**
	 * 게시글 리스트 조회
	 * 
	 * @param paging - 조회대상 페이징 객체
	 * @return 게시글을 조회한 결과
	 */
	public Paging getCurPage(HttpServletRequest request);
	
	/**
	 * 요청파라미터에서 boardno를 파싱한다
	 * 
	 * @param req - 요청정보객체
	 * @return Board - boardno를 입력한 객체
	 */
	public Board getBoardno(HttpServletRequest request);
	
	/**
	 * 상세보기 게시글 조회
	 * 
	 * @param viewBoard - 상세보기할 boardno를 가진 객체
	 * @return Board - 상세보기할 게시글 조회 결과
	 */
	public Board view(Board viewBoard);
	
	/**
	 * 댓글 전달파라미터 꺼내기
	 */
	public Reply getReply(HttpServletRequest req);
	
	/**
	 * 댓글 입력
	 * 
	 * @param comment - 삽입될 댓글
	 */
	public void insertReply(Reply reply);
	
	/**
	 * 댓글 리스트
	 * 
	 * @param board - 댓글이 조회될 게시글
	 * @return List - 댓글 리스트
	 */
	public List getReplyList(Board board);
	
	/**
	 * 댓글 삭제
	 *  
	 * @param comment - 삭제할 댓글
	 * @return boolean - 삭제 성공 여부
	 */
	public boolean deleteReply(Reply reply);

	//게시글 작성
	public void write(HttpServletRequest req);

	//글 작성자인지 판단하기
	public boolean checkWriter(HttpServletRequest req);

	//게시글 수정
	public void update(HttpServletRequest req);

	//게시글 삭제
	public void delete(Board board);

}
