package hyo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

public class GameConsole extends JFrame {
	private List<JLabel> userInven;
	private List<JLabel> userInvenCount;
	
	UserInfo user = new UserInfo();
	ItemConsole console = new ItemConsole(new ItemDao(), user);
	JPanel panel;
//	Trade trade;
//	Talk talk;
	
	public void userInven() {
		List<Item> items = user.getInventory();
		console.lblImg(userInven, items);
		console.lblCount(userInven, userInvenCount);
		for (int i = 0; i<userInven.size(); i++) {
			console.imgHover(userInven.get(i));
		}
	}
	
	public GameConsole() {
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(14, 12, 800, 600);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(830, 12, 303, 83);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		// 인벤토리 패널
		JPanel pnl_userInven = new JPanel();
		pnl_userInven.setBounds(840, 140, 304, 450);
		getContentPane().add(pnl_userInven);
		pnl_userInven.setLayout(null);
		
//		JLabel lblNewLabel = new JLabel("0");
//		lblNewLabel.setBounds(50, 64, 26, 26);
//		pnl_userInven.add(lblNewLabel);
		
		// 유저 인벤토리 count용
		userInvenCount = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			JLabel lbl = new JLabel("0");
//			lbl.setOpaque(true);
			lbl.setFont(new Font("휴먼굴림체", Font.BOLD, 14));
			lbl.setForeground(Color.red);
			userInvenCount.add(lbl);
			pnl_userInven.add(lbl);
		}

		userInvenCount.get(0).setBounds(50,  64,  26,  26);
		userInvenCount.get(1).setBounds(126,  64,  26,  26);
		userInvenCount.get(2).setBounds(202,  64,  26,  26);
		userInvenCount.get(3).setBounds(278,  64,  26,  26);
		userInvenCount.get(4).setBounds(50,  154,  26,  26);
		userInvenCount.get(5).setBounds(126,  154,  26,  26);
		userInvenCount.get(6).setBounds(202,  154,  26,  26);
		userInvenCount.get(7).setBounds(278,  154,  26,  26);
		userInvenCount.get(8).setBounds(50,  244,  26,  26);
		userInvenCount.get(9).setBounds(126,  244,  26,  26);
		userInvenCount.get(10).setBounds(202,  244,  26,  26);
		userInvenCount.get(11).setBounds(278,  244,  26,  26);
		userInvenCount.get(12).setBounds(50,  334,  26,  26);
		userInvenCount.get(13).setBounds(126,  334,  26,  26);
		userInvenCount.get(14).setBounds(202,  334,  26,  26);
		userInvenCount.get(15).setBounds(278,  334,  26,  26);
		userInvenCount.get(16).setBounds(50,  424,  26,  26);
		userInvenCount.get(17).setBounds(126,  424,  26,  26);
		userInvenCount.get(18).setBounds(202,  424,  26,  26);
		userInvenCount.get(19).setBounds(278,  424,  26,  26);
		
		// 유저 인벤토리 img용
		userInven = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			JLabel lbl = new JLabel("");
//			lbl.setOpaque(true);
			userInven.add(lbl);
			pnl_userInven.add(lbl);

		}
		userInven.get(0).setBounds(0,  0,  76,  90);
		userInven.get(1).setBounds(76,  0,  76,  90);
		userInven.get(2).setBounds(152,  0,  76,  90);
		userInven.get(3).setBounds(228,  0,  76,  90);
		userInven.get(4).setBounds(0,  90,  76,  90);
		userInven.get(5).setBounds(76,  90,  76,  90);
		userInven.get(6).setBounds(152,  90,  76,  90);
		userInven.get(7).setBounds(228,  90,  76,  90);
		userInven.get(8).setBounds(0,  180,  76,  90);
		userInven.get(9).setBounds(76,  180,  76,  90);
		userInven.get(10).setBounds(152,  180,  76,  90);
		userInven.get(11).setBounds(228,  180,  76,  90);
		userInven.get(12).setBounds(0,  270,  76,  90);
		userInven.get(13).setBounds(76,  270,  76,  90);
		userInven.get(14).setBounds(152,  270,  76,  90);
		userInven.get(15).setBounds(228,  270,  76,  90);
		userInven.get(16).setBounds(0,  360,  76,  90);
		userInven.get(17).setBounds(76,  360,  76,  90);
		userInven.get(18).setBounds(152,  360,  76,  90);
		userInven.get(19).setBounds(228,  360,  76,  90);


		
		user.setInventory(new ArrayList<Item>(
				Arrays.asList(new Item(9, 1, 1), new Item(8, 1, 999), new Item(3, 1, 1), new Item(19, 1, 3))));
		userInven();
		ToolTipManager m = ToolTipManager.sharedInstance();
		m.setInitialDelay(0);
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton btnNewButton = new JButton("상점가기");
		btnNewButton.setBounds(34, 5, 115, 34);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				user.setInventory(new ArrayList<Item>(
						Arrays.asList(new Item(9, 1, 1), new Item(8, 1, 999), new Item(3, 1, 1), new Item(19, 1, 3))));
				Trade trade = new Trade(console);
				panel.setVisible(false);
//				JPanel another = new JPanel();
//				another.setBackground(Color.RED);
//				another.setBounds(14, 12, 800, 600);
//				getContentPane().add(another);
				trade.setVisible(true);
				trade.setBounds(14, 12, 800, 600);
				getContentPane().add(trade);
				trade.setStoreItem(3, 1, 1);
				trade.setMyItem();
				for (int i = 0; i < trade.getStoreItem().size(); i++) {
					console.imgHover(trade.getStoreItem().get(i));
				}
				for (int i = 0; i < trade.getMyItem().size(); i++) {
					console.imgHover(trade.getMyItem().get(i));
				}
				trade.tradeItem_npcFaceImg(0);
				ToolTipManager m = ToolTipManager.sharedInstance();
				m.setInitialDelay(0);
			}
		});
		
		JButton btnNewButton2 = new JButton("대화하기");
		btnNewButton2.setBounds(154, 5, 106, 34);
		panel_1.add(btnNewButton2);
		btnNewButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				Talk talk = new Talk(1일차);
//				1일차의 테이블을 맵에 넣고
//				panel.setVisible(false);
//				trade.setVisible(true);
//				trade.setBounds(14, 12, 800, 600);
//				getContentPane().add(trade);
//				trade.setStoreItem(3, 1, 1);
//				trade.setMyItem();
//				for (int i = 0; i < trade.getStoreItem().size(); i++) {
//					trade.imgHover(trade.getStoreItem().get(i));
//				}
//				for (int i = 0; i < trade.getMyItem().size(); i++) {
//					trade.imgHover(trade.getMyItem().get(i));
//				}
//				trade.tradeItem_npcFaceImg(trade.getNpcImg(), 0);
//				ToolTipManager m = ToolTipManager.sharedInstance();
//				m.setInitialDelay(0);
			}
		});
		
		setSize(1200, 900);
	}
	
	
	public static void main(String args[]) {
		GameConsole a = new GameConsole();
		a.setVisible(true);
		
	}
}
