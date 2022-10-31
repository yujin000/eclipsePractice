package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MessagesDTO;

public class MessagesDAO {

	private static MessagesDAO instance;
	
	synchronized public static MessagesDAO getinstance() throws Exception {
		
		if (instance == null) {
			instance = new MessagesDAO();
		}
		return instance;
	}
	
	public MessagesDAO(){
		
	}
	
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
//	private Connection getConnection() throws Exception {
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String id = "kh";
//		String pw = "kh";
//		return DriverManager.getConnection(url,id,pw);
//	}

	public int insert(String writer, String message) throws Exception {

		String sql = "insert into messages values(messages_seq.nextval,?,?)";

		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, writer);
			pstat.setString(2, message);
			int result = pstat.executeUpdate();
			con.commit();
			con.close();		
			return result;
		}
	}
	
	public ArrayList<MessagesDTO> selectAll() throws SQLException, Exception {
		String sql = "select * from messages";
		
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try (ResultSet rs = pstat.executeQuery();) {
				ArrayList<MessagesDTO> result = new ArrayList<>();
				
				while (rs.next()) {
					
					MessagesDTO dto = new MessagesDTO();
					dto.setSeq(rs.getInt("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setMessage(rs.getString("messages"));
					result.add(dto);					
				}
				return result;				
			} 
		} 
	}
	
	public int delete(int num) throws SQLException, Exception {
		String sql = "delete messages where num = ?";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, num);
			int result=pstat.executeUpdate();
			con.commit();
			con.close();
			
			return result;
		}				
	}
	
	public int update(int num, String writer, String message) throws SQLException, Exception {
		String sql="update messages set writer = ?, messages = ? where num = ?";
		
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, writer);
			pstat.setString(2, message);
			pstat.setInt(3, num);
			
			int result = pstat.executeUpdate();
			con.commit();
			con.close();
			
			return result;
		}			
	}	
}
