package bulletin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.green.BusanUtil;

public class BulletinDao {
	private BulletinInfo info;
	private List<BulletinInfo> list = new ArrayList<>();
	
	
	
	public String BulletInputId(int userNum) {
		String a = null;
		try (Connection conn = BusanUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Busan.login_info");
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				if(userNum == rs.getInt("userNum")) {
					a = rs.getString("id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}
	
	
	public int BulletStar(int star) {
		int a = 0;
		try (Connection conn = BusanUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * From bulletinbord");
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				if(star == rs.getInt("star")) {
					a++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return a;
	}

	
	public List<BulletinInfo> read() throws SQLException {
		String query = "SELECT * From bulletinbord";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		

		try {
			conn = BusanUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				int num = rs.getInt("num");
				String userId = rs.getString("userId");
				String achv = rs.getString("achv");
				String text = rs.getString("text");
				int star = rs.getInt("star");
				
				info = new BulletinInfo(num, userId, achv, text, star);
				list.add(info);
			}

		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(stmt);
			BusanUtil.closeConn(conn);
		}
		return list;
	}
	
	
	public String StarAvg() {
		String query = "Select round(avg(star),1) AS star from bulletinBord";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = BusanUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return String.valueOf(rs.getDouble("star"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
			BusanUtil.closeConn(conn);
		}
		return "";
	}

	public int BulletCreate(String userId, String achv, String text, int star) {
		try (Connection conn = BusanUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO Busan.bulletinBord (userId, achv, text, star) values (?, ?, ?, ?)");) {

			pstmt.setString(1, userId);
			pstmt.setString(2, achv);
			pstmt.setString(3, text);
			pstmt.setInt(4, star);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		BulletinDao bb = new BulletinDao();

		int result = bb.BulletCreate(bb.BulletInputId(5), "개발자는  웁니다", "뒤집어놓으셨다~~!!!!!", 5);
		System.out.println(result);
	}
}