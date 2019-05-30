package dto;

import java.util.Date;

public class Member {
	private String userid;
	private String password;
	private String nickname;
	private int iconno;
	private int age;
	private String gender;
	private String teamname;
	private String introduce;
	private Date joindate;
	private int grade;
	
	@Override
	public String toString() {
		return "member [userid=" + userid + ", password=" + password + ", nickname=" + nickname + ", iconno=" + iconno
				+ ", age=" + age + ", gender=" + gender + ", teamname=" + teamname + ", introduce=" + introduce
				+ ", joindate=" + joindate + ", grade=" + grade + "]";
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getIconno() {
		return iconno;
	}
	public void setIconno(int iconno) {
		this.iconno = iconno;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
