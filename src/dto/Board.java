package dto;

import java.util.Date;

public class Board {

	private int boardno; //게시판번호
	private String nickname; //유저닉네임
	private String title; //글제목
	private String content; //글본문
	private int scheduleno;	//스케줄 번호
	private String team;	//직관 경기 팀
	private Date insertdate; //글쓴날짜
	private int hit; //조회수
	
	
	@Override
	public String toString() {

		return "Board [boardno=" + boardno + ", nickname=" + nickname + ", title=" + title + ", content=" + content
				+ ", scheduleno=" + scheduleno + ", team=" + team + ", insertdate=" + insertdate + ", hit=" + hit + "]";

	}

	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
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
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
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
