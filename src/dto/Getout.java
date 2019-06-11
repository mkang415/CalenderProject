package dto;

public class Getout {
	
	private String getoutid;
	private String reason;
	private String explain;
	
	@Override
	public String toString() {
		return "getout [getoutid=" + getoutid + ", reason=" + reason + ", explain=" + explain + "]";
	}
	
	public String getGetoutid() {
		return getoutid;
	}
	
	public void setGetoutid(String getoutid) {
		this.getoutid = getoutid;
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
	
	
}
