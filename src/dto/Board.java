package dto;

import java.util.Date;

public class Board {

	private int boardno;
	private String userid;
	private String title;
	private String content;
	private int scheduleno;
	private Date insertdate;
	private int hit;
	
	@Override
	public String toString() {
		return "board [boardno=" + boardno + ", userid=" + userid + ", title=" + title + ", content=" + content
				+ ", scheduleno=" + scheduleno + ", insertdate=" + insertdate + ", hit=" + hit + "]";
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
