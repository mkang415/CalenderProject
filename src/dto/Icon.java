package dto;

public class Icon {

	private int iconno;
	private String iconname;
	private String filename;
	private String storename;
	
	@Override
	public String toString() {
		return "icon [iconno=" + iconno + ", iconname=" + iconname + ", filename=" + filename + ", storename="
				+ storename + "]";
	}
	
	public int getIconno() {
		return iconno;
	}
	public void setIconno(int iconno) {
		this.iconno = iconno;
	}
	public String getIconname() {
		return iconname;
	}
	public void setIconname(String iconname) {
		this.iconname = iconname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	
	
}
