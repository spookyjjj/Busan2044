package chapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.green.BusanUtil;



public class ChapterDaoImpl implements ChapterDao {
	private List<ChapterInfo> list;
	
	public ChapterInfo resultMapping(ResultSet rs) throws SQLException {
		int StoryId = rs.getInt("StoryId");
		String storyNum  = rs.getString("storyNum");
		String storyMain = rs.getString("storyMain");
		String storyTime  = rs.getString("storyTime");
		String check = rs.getString("check");
		
		return new ChapterInfo(StoryId, storyNum, storyMain, storyTime, check);
	}
	

	@Override
	public int create(String storyNum, String storyMain, String storyTime, String check) throws SQLException {
		String query = "INSERT INTO chapter1 (storyNum , storyMain, storyTime, check) VALUES (?, ?, ?, ?)";
		

		Connection conn = null;
		PreparedStatement pstmt = null; // statement 의 자식 클래스
										// 쿼리문에 넣을 값을 ? 로 만들어놓고 이후에 내가 원하는  값으로 세팅해준다
		
		 
		try {
			conn = BusanUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			 // 첫번째 인덱스에 값을 넣는다
			pstmt.setString(1, storyNum);
			pstmt.setString(2, storyMain);
			pstmt.setString(3, storyTime);
			pstmt.setString(4, check);
			
			
			return pstmt.executeUpdate();  // 위에서 쿼리문을 다 설정을 해놓은 상태라 파라미터를 비워놓는다.
			
		} finally {
			BusanUtil.closeStmt(pstmt);
			BusanUtil.closeConn(conn);
		}
	}
	

	@Override
	public List<ChapterInfo> read() throws SQLException {
		String query = "SELECT * FROM chapter1";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		list = new ArrayList<>();
		try {
			conn = BusanUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(resultMapping(rs));
			}
			
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
			BusanUtil.closeConn(conn);
		}
		return list;
	}
	

	@Override
	public ChapterInfo read(int storyNum) throws SQLException {
		String query = "SELECT * FROM chapter1 WHERE storyNum = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = BusanUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storyNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return resultMapping(rs);
			}
			
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
			BusanUtil.closeConn(conn);
		}
		return null;
	}
	

	@Override
	public int update(int storyNum, String storyMain, String storyTime, String check) throws SQLException {
String query = "UPDATE chapter1 SET storyMain = ?, storyTime = ?, check = ? WHERE storyNum = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = BusanUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, storyMain);
			pstmt.setString(2, storyTime);
			pstmt.setString(3, check);
			pstmt.setInt(4, storyNum);
			
			
			return pstmt.executeUpdate();
		} finally {
			BusanUtil.closeStmt(pstmt);
			BusanUtil.closeConn(conn);
		}
	}
	

	@Override
	public int delete(int storyNum) throws SQLException {
		String query = "DELETE FROM chapter1 WHERE storyNum = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = BusanUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, storyNum);
			
			return pstmt.executeUpdate();
		} finally {
			BusanUtil.closeStmt(pstmt);
			BusanUtil.closeConn(conn);
		}
	}

}
