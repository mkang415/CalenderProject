package dto;

import java.util.Date;

public class Schedule {

	private int scheduleno;
	private String hometeam;
	private String awayteam;
	private Date gamedate;
	
	@Override
	public String toString() {
		return "schedule [scheduleno=" + scheduleno + ", hometeam=" + hometeam + ", awayteam=" + awayteam
				+ ", gamedate=" + gamedate + "]";
	}
	
	public int getScheduleno() {
		return scheduleno;
	}
	public void setScheduleno(int scheduleno) {
		this.scheduleno = scheduleno;
	}
	public String getHometeam() {
		return hometeam;
	}
	public void setHometeam(String hometeam) {
		this.hometeam = hometeam;
	}
	public String getAwayteam() {
		return awayteam;
	}
	public void setAwayteam(String awayteam) {
		this.awayteam = awayteam;
	}
	public Date getGamedate() {
		return gamedate;
	}
	public void setGamedate(Date gamedate) {
		this.gamedate = gamedate;
	}
	
}
