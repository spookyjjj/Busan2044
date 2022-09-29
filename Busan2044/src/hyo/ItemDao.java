package hyo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import kr.co.green.BusanUtil;
//다오에서는 DB와 교류하는 매우 기본적인것만 넣어주세요 delete나 update, select, getter setter같은것들,,
public class ItemDao {
	//item_code를 지정해주면 확률게임 진행 후 item_id 하나를 뱉음
	
	
	

	
	//id로 item정보 다 뜯어오기
	public String getItemCode(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("item_code");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}
	public String getItemName(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("item_name");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}
	public String getItemMemo(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("item_memo");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}
	public String getItemImg(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("item_img");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}
	public String getItemBm(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("item_breakmessage");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}
	public int getItemRare(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("item_rare");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}
	public int getItemHp(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("item_hp");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}
	public int getItemMental(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("item_mental");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}

	public boolean getItemNodeal(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = true;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getBoolean("item_nodeal");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}
	
	public int getItemDurability(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("item_durability");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}
	public int getItemCount(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("item_count");
			}
			return result;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}

	
	
	
//	public static void main(String[] args) {
//		ItemDao mm = new ItemDao();
//		Character user1 = new Character();
//		List<Integer>storeInven = new ArrayList<>();
//		Connection conn = null;
//		try {
//			conn = BusanUtil.getConnection();
//			mm.trade_npcItem(conn, "rcv");
//			mm.trade_npcItem(conn, "rcv");
//			mm.trade_npcItem(conn, "rcv");
//			mm.trade_npcItem(conn, "wpn");
//			mm.trade_npcItem(conn, "evt");
//			
//			user1.getInventory().add(mm.inputInventory(conn, 3));
//			user1.getInventory().add(mm.inputInventory(conn, 21));
//			System.out.println(user1.getInventory().toString());
//			
//		} catch (Exception e) {
//			System.out.println("에러");
//		} finally {
//			BusanUtil.closeConn(conn);
//		}
//		
//				
//	}
}
