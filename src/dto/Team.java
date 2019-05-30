package dto;

public class Team {

	private String teamname;
	private int iconno;
	private String region;
	private String stadium;
	private int event;
	
	@Override
	public String toString() {
		return "team [teamname=" + teamname + ", iconno=" + iconno + ", region=" + region + ", stadium=" + stadium
				+ ", event=" + event + "]";
	}
	
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public int getIconno() {
		return iconno;
	}
	public void setIconno(int iconno) {
		this.iconno = iconno;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getStadium() {
		return stadium;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	public int getEvent() {
		return event;
	}
	public void setEvent(int event) {
		this.event = event;
	}
}
