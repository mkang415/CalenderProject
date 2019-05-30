package dto;

import java.util.Date;

public class Report {

	private int reportno;
	private String userid;
	private String reason;
	private String explain;
	private Date reportdate;
	
	@Override
	public String toString() {
		return "report [reportno=" + reportno + ", userid=" + userid + ", reason=" + reason + ", explain=" + explain
				+ ", reportdate=" + reportdate + "]";
	}
	
	public int getReportno() {
		return reportno;
	}
	public void setReportno(int reportno) {
		this.reportno = reportno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public Date getReportdate() {
		return reportdate;
	}
	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
	
	
}
