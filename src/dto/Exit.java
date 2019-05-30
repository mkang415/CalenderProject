package dto;

public class Exit {

	private String exitid;
	private String exitreason;
	
	@Override
	public String toString() {
		return "exit [exitid=" + exitid + ", exitreason=" + exitreason + "]";
	}
	
	public String getExitid() {
		return exitid;
	}
	public void setExitid(String exitid) {
		this.exitid = exitid;
	}
	public String getExitreason() {
		return exitreason;
	}
	public void setExitreason(String exitreason) {
		this.exitreason = exitreason;
	}
	
	
}
