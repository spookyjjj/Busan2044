package hyo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.green.BusanUtil;

public class NpcConsole {
	private NpcDao dao;
	private UserInfo user;
	
	//user.getParty() -> npc_skill list로 반환
	public List<Integer> idToSkill() {
		List<Integer> list = new ArrayList<>();
		try (Connection conn = BusanUtil.getConnection()) {
			for(int i : user.getParty()) {
				list.add(dao.getNpcSkill(conn, i));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
