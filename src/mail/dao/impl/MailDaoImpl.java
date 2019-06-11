package mail.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBConn;
import mail.dao.face.MailDao;

public class MailDaoImpl implements MailDao{

	Connection conn = DBConn.getConnection();
	
	PreparedStatement ps = null;
	
	ResultSet rs = null;
	
	@Override
	public boolean confirm(String email) {

		boolean confirm = false;
		
		String sql = "";
		sql+="SELECT userid FROM member";
		sql+=" WHERE userid=?";		
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, email);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				if(rs.getString("userid").equals(email)) {
					confirm=true;
					break;
				} else {
					confirm=false;
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return confirm;
	}

}
