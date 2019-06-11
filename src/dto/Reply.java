package dto;

import java.util.Date;

public class Reply {

	private int replyno;
	private String userid;
	private int boardno;
	private String replyContent;
	private Date insertdate;
	
	@Override
	public String toString() {
		return "Comment [replyno = " + replyno
				+ ", boardno = " + boardno + ", userid = " + userid
				+ ", replyContent = " + replyContent + ", insertdate = " + insertdate 
				+ ", getClass() = " + getClass() + ", hashCode() = " + hashCode()
				+ ", toString() = " + super.toString() + "]";
	}
	
	public int getReplyno() {
		return replyno;
	}

	public void setReplyno(int replyNo) {
		this.replyno = replyNo;
	}

	public int getBoardno() {
		return replyno;
	}

	public void setBoardno(int boardNo) {
		this.boardno = boardNo;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String content) {
		this.replyContent = content;
	}

	public Date getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(Date insertdate) {
		this.insertdate = insertdate;
	}
}
