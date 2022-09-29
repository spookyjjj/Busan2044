package hyo;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import kr.co.green.BusanUtil;

//여기에는 UserInfo의 정보들과 교류하는 내용들

public class ItemConsole {
	private ItemDao dao;
	private UserInfo user;
//	private boolean invenFull = false;

	public ItemConsole(ItemDao dao, UserInfo user) {
		this.dao = dao;
		this.user = user;
	}
	
	public ItemDao getDao() {
		return dao;
	}

	public UserInfo getUser() {
		return user;
	}

	// 아이템 획득시 진행될 로직 (1회용)
	// item_id에 맞게 durability, count가져오기
	public Item setItemInfo(Connection conn, int item_id) throws SQLException {
		String query = "select * from item_table where item_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Item item = new Item();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				item.setItem_id(item_id);
				item.setItem_durability(rs.getInt("item_durability"));
				item.setItem_count(rs.getInt("item_count"));
			}
			return item;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}

	public int trade_npcItem(Connection conn, String item_code) throws SQLException {
		String query = "select * from item_table where item_code = ? and item_nodeal = 0;";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> rare1 = new ArrayList<>();
		List<Integer> rare2 = new ArrayList<>();
		List<Integer> rare3 = new ArrayList<>();
		List<Integer> rare4 = new ArrayList<>();
		List<Integer> rare5 = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, item_code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rare = rs.getInt("item_rare");
				switch (rare) {
				case 0:
					break;
				case 1:
					rare1.add(rs.getInt("item_id"));
					break;
				case 2:
					rare2.add(rs.getInt("item_id"));
					break;
				case 3:
					rare3.add(rs.getInt("item_id"));
					break;
				case 4:
					rare4.add(rs.getInt("item_id"));
					break;
				case 5:
					rare5.add(rs.getInt("item_id"));
					break;
				}
			}
			// 100퍼 중에서, 1:40퍼, 2:25퍼, 3:17퍼, 4:10퍼, 5:8퍼
			Random ran = new Random();
			int random = ran.nextInt(100) + 1;
			int item_id = 0;
			if (random <= 40) {
				// rare1에서 랜덤뽑기
				item_id = rare1.get(ran.nextInt(rare1.size()));
			} else if (random <= 65) {
				item_id = rare2.get(ran.nextInt(rare2.size()));
			} else if (random <= 82) {
				item_id = rare3.get(ran.nextInt(rare3.size()));
			} else if (random <= 92) {
				item_id = rare4.get(ran.nextInt(rare4.size()));
			} else {
				item_id = rare5.get(ran.nextInt(rare5.size()));
			}
			return item_id;
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
		}
	}

	public void getItem(int item_id) {
		try (Connection conn = BusanUtil.getConnection()) {
			int amount;
			if ((amount = dao.getItemHp(conn, item_id)) != 0) { // 아이템의 hp가 0이 아니면 체력회복아이템
				if (user.getHp() < user.FULL_HP) {
					int newHp = user.getHp() + amount;
					if (newHp > user.FULL_HP) {
						newHp = user.FULL_HP;
					}
					user.setHp(newHp);
				} else {
					inputInven(item_id);
				}
			} else if ((amount = dao.getItemMental(conn, item_id)) != 0) { // 아이템의 Mental이 0이 아니면 멘탈회복아이템
				if (user.getMental() < user.FULL_MENTAL) {
					int newMental = user.getMental() + amount;
					if (newMental > user.FULL_MENTAL) {
						newMental = user.FULL_MENTAL;
					}
					user.setMental(newMental);
				} else {
					inputInven(item_id);
				}
			} else { // 위의 두개가 이나면 잡템
				inputInven(item_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public void getItem(List<Integer> list) {
//		for (int i : list) {
//			getItem(i);
//		}
//	}

	// 중복아이템을 주으면 해당 인벤토리 인덱스의 count를 ++하는 걸로 바꾸기~
	public void inputInven(int item_id) {
		int index = checkDuple(item_id);
		if (index != -1) { // 중복이면
			user.getInventory().get(index).setItem_count(user.getInventory().get(index).getItem_count() + 1);
		} else { // 중복이 아니면
			try (Connection conn = BusanUtil.getConnection()) {
				user.getInventory().add(setItemInfo(conn, item_id));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 중복인지 체크
	// 중복이면 인벤토리에서의 index반환, 중복 아니라면 -1반환
	public int checkDuple(int item_id) {
		List<Item> inventory = user.getInventory();
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getItem_id() == item_id) {
				return i;
			}
		}
		return -1;
	}

	// 유저가 인벤토리에서 item을 버릴 시 인벤토리 List에서 item을 삭제할 수 있는 메소드 만들어야 함
	public void itemRemove(int item_id) {
		List<Item> inventory = user.getInventory();
//		      유저가 패널에서 이미지를 누르면  9번 이름이 삭제되게 
//				int nowid =  user.getInventory().get(0).getItem_id();
		int index = -1;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getItem_id() == item_id) {
				index = i;
			}
		}
		user.getInventory().remove(index);
	}

	// 거래가능 아이템만 list<Item>으로 옮기는 기능
	public List<Item> pickDealItem() {
		List<Item> inven = user.getInventory();
		List<Item> move = new ArrayList<>();
		try (Connection conn = BusanUtil.getConnection()) {
			for (Item i : inven) {
				if (!dao.getItemNodeal(conn, i.getItem_id())) {
					move.add(i);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return move;
	}
	

	// list<integer> -> list<item>메소드
	public List<Item> integerToItem(List<Integer> list) {
		List<Item> list2 = new ArrayList<>();
		try (Connection conn = BusanUtil.getConnection()) {
			for (int i : list) {
				list2.add(setItemInfo(conn, i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list2;
	}
	
	// String값 반환 : name, memo, code, nodeal
	public String IdToInfo(int item_id, String select) {
		Connection conn = null;
		String info = "";
		boolean nodeal = true;
		try {
			conn = BusanUtil.getConnection();

			if (select.equals("name")) {
				info = dao.getItemName(conn, item_id);
			}
			if (select.equals("code")) {
				info = dao.getItemCode(conn, item_id);
				if (info.equals("wpn")) {
					info = "무기";
				} else if (info.equals("rcv")) {
					info = "회복";
				} else if (info.equals("evt")) {
					info = "이벤트";
				} else {
					info = null;
				}
			}
			if (select.equals("memo")) {
				info = dao.getItemMemo(conn, item_id);
			}
			if (select.equals("nodeal")) {
				nodeal = dao.getItemNodeal(conn, item_id);
				if (nodeal == false) {
					info = "교환 가능";
				} else {
					info = "교환 불가";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BusanUtil.closeConn(conn);
		}
		return info;
	}

	// int값 반환 : rare, Durability, hp, mental
	public int IdToInfo2(int item_id, String select) {
		Connection conn = null;
		int info = 0;
		try {
			conn = BusanUtil.getConnection();
			if (select.equals("durability"))
				info = dao.getItemDurability(conn, item_id);
			if (select.equals("rare"))
				info = dao.getItemRare(conn, item_id);
			if (select.equals("hp"))
				info = dao.getItemHp(conn, item_id);
			if (select.equals("mental"))
				info = dao.getItemMental(conn, item_id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BusanUtil.closeConn(conn);
		}
		return info;
	}

	
	// 패널의 id값을 받아오는 메소드 -> 정보없는 공란lbl이면 -1반환
	public int lbl_searchID(JLabel lbl) {
		String lblstr = lbl.getText();
		if (!lblstr.equals("")) {
			String[] idstr = lblstr.split("/", 3);
			int id = Integer.parseInt(idstr[0]);
			return id;
		} else {
			return -1;
		}
	}
	// 라벨에서 id값을 가져와서 툴팁박스로 메모 보여주기
	public void imgHover(JLabel lbl) {
		int itemId = lbl_searchID(lbl);
		if (itemId != -1) {
			int count = 0;
			count = IdToInfo2(itemId, "rare");

			// 레어도에 따라 이름 색 변경하는 메소드
			String color = "";
			switch (count) {
			case 1:
				color = " 이름 : <font face=\"sansserif\" color=\"black\">";
				break;
			case 2:
				color = " 이름 : <font face=\"sansserif\" color=\"mandarine\">";
				break;
			case 3:
				color = " 이름 : <font face=\"sansserif\" color=\"green\">";
				break;
			case 4:
				color = " 이름 : <font face=\"sansserif\" color=\"yellow\">";
				break;
			case 5:
				color = " 이름 : <font face=\"sansserif\" color=\"red\">";
				break;
			default:
				color = " 이름 : <font face=\"sansserif\" color=\"black\">";
				break;
			}
			// String name = color + IdToInfo(itemId, "name") + "</font> [ " +
			// IdToInfo(itemId, "code") +" 아이템 ]<br>";
			String name = color + IdToInfo(itemId, "name") + "</font>     [  " + IdToInfo(itemId, "code") + " 아이템  ]<br>";

			// 내구도가 0일 때 나오지 않게 해주는
			count = IdToInfo2(itemId, "durability");
			String durability = "";
			if (count > 0) {
				durability = " 내구도 : " + count + "<br>";
			}
			// hp회복량이 0일경우 보이지 않게 해주는
			count = IdToInfo2(itemId, "hp");
			String hp = "";
			if (count > 0) {
				hp = " 체력 회복량 : " + count + "<br>";
			}
			// hp회복량이 0일경우 보이지 않게 해주는
			count = IdToInfo2(itemId, "mental");
			String mental = "";
			if (count > 0) {
				mental = " 멘탈 회복량 : " + count + "<br>";
			}
			// 교환가능일때 파란색, 교환불가일때 빨간색
			color = IdToInfo(itemId, "nodeal");
			String nodeal = "";
			if (color.equals("교환 가능")) {
				color = "<font face=\"sansserif\" color=\"blue\">";
			} else {
				color = "<font face=\"sansserif\" color=\"red\">";
			}
			nodeal = color + IdToInfo(itemId, "nodeal") + "<br>" + "</font>";

			String info_Item = "<html>" + name + durability + hp + mental + " 설명 : " + IdToInfo(itemId, "memo") + "<br>"
					+ nodeal + "</html>";

			lbl.setToolTipText(info_Item);
		}
	}
	// 아이템클래스 -> 라벨에 입히기
	public void lblImg(JLabel lbl, Item item) {
		try (Connection conn = BusanUtil.getConnection()) {
			Toolkit kit = Toolkit.getDefaultToolkit();
			String path = dao.getItemImg(conn, item.getItem_id());
			URL url = Trade.class.getClassLoader().getResource(path);
			Image img = kit.getImage(url);
			lbl.setIcon(new ImageIcon(img)); // 해당하는 이미지 넣었음
			lbl.setText(
					String.valueOf(item.getItem_id() + "/" + item.getItem_durability() + "/" + item.getItem_count())); // 텍스트에
																														// 정보넣기
			lbl.setFont(new Font("휴먼엑스포", Font.PLAIN, 0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 이미지 들어간 lbl에 맞춰서 lbl에 count 세팅
	public void lblCount(List<JLabel> imgLbl, List<JLabel> countLbl) { // 앞에거는 img -> 뒤에꺼는 count(Trade파일)
		for (int i = 0; i < imgLbl.size(); i++) {
			int count = lbl_searchCount(imgLbl.get(i));
			if (count != -1) {
				countLbl.get(i).setText(String.valueOf(count));
			}
		}
	}
	// 패널의 id값을 받아오는 메소드 -> 정보없는 공란lbl이면 -1반환
	public int lbl_searchCount(JLabel lbl) {
		String lblstr = lbl.getText();
		if (!lblstr.equals("")) {
			String[] idstr = lblstr.split("/", 3);
			int count = Integer.parseInt(idstr[2]);
			return count;
		} else {
			return -1;
		}
	}
	// 아이템id들 list -> 패널 list
	public void lblImg(List<JLabel> lbls, List<Item> items) {
		try (Connection conn = BusanUtil.getConnection()) {
			for (int i = 0; i < items.size(); i++) {
				Toolkit kit = Toolkit.getDefaultToolkit();
				String path = dao.getItemImg(conn, items.get(i).getItem_id());
				URL url = Trade.class.getClassLoader().getResource(path);
				Image img = kit.getImage(url);
				lbls.get(i).setIcon(new ImageIcon(img)); // 해당하는 이미지 넣었음
				lbls.get(i).setText(String.valueOf(items.get(i).getItem_id() + "/" + items.get(i).getItem_durability()
						+ "/" + items.get(i).getItem_count())); // 텍스트에 정보넣기
				lbls.get(i).setFont(new Font("휴먼엑스포", Font.PLAIN, 0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
}
