package dto;

import java.util.Date;

public class Board {

	private int boardno; //게시판번호
	private String userid; //유저아이디
	private String nickname; //닉네임
	private String title; //글제목
	private String content; //글본문
	private int scheduleno; //스케줄넘버?
	private Date insertdate; //글쓴날짜
	private int hit; //조회수
	
	@Override
	public String toString() {
		return "Board [boardno=" + boardno + ", userid=" + userid + ", nickname=" + nickname + ", title=" + title
				+ ", content=" + content + ", scheduleno=" + scheduleno + ", insertdate=" + insertdate + ", hit=" + hit
				+ "]";
	}

	public int getBoardno() {
		return boardno;
	}
	
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getScheduleno() {
		return scheduleno;
	}
	
	public void setScheduleno(int scheduleno) {
		this.scheduleno = scheduleno;
	}
	
	public Date getInsertdate() {
		return insertdate;
	}
	
	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}
	
	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit = hit;
	}
}
