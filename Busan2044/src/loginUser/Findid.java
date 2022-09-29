package loginUser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.co.green.BusanUtil;

public class Findid extends JFrame {

	private boolean b = false;

	public Findid() {
		super("아이디찾기");

		JPanel pnl = new JPanel();
	
		getContentPane().add(pnl);
		pnl.setLayout(null);
		

		JButton btn = new JButton("아이디찾기");
		// TODO 폰트
		btn.setFont(new Font("HY목각파임B", Font.BOLD, 14));
		btn.setBounds(80, 205, 126, 31);
		btn.setBackground(Color.white);
		pnl.add(btn);

		setSize(300, 300);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		pnl.setBackground(Color.LIGHT_GRAY);

		JLabel username = new JLabel("이름");
		// TODO 폰트
		username.setFont(new Font("HY목각파임B", Font.BOLD, 14));
		username.setBounds(25, 61, 57, 15);
		pnl.add(username);

		JLabel phoneNum = new JLabel("전화번호");
		// TODO 폰트
		phoneNum.setFont(new Font("HY목각파임B", Font.BOLD, 14));
		phoneNum.setBounds(25, 142, 81, 15);
		pnl.add(phoneNum);

		JTextField usernameF = new JTextField(10);
		// TODO 폰트
		usernameF.setFont(new Font("HY목각파임B", Font.BOLD, 14));
		usernameF.setBounds(110, 55, 152, 28);
		pnl.add(usernameF);

		JTextField phoneNumF = new JTextField(10);
		// TODO 폰트
		phoneNumF.setFont(new Font("HY목각파임B", Font.BOLD, 14));
		phoneNumF.setBounds(110, 136, 152, 28);
		pnl.add(phoneNumF);
		phoneNumF.setText("010-0000-0000");
		
		phoneNumF.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				phoneNumF.setText("");
				super.focusGained(e);
			}
			
			 @Override
	         public void focusLost(FocusEvent e) { //포커스가 벗어났을때 ""이었으면 "값을 입력하세요"이고, 뭐라도 값이 있었으면 아무짓도 안함
	            if (phoneNumF.getText().equals("")) {
	            	phoneNumF.setText("010-0000-0000");
	            }
	         }
			
		});

		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT * FROM login_info";

				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;

				try {
					conn = BusanUtil.getConnection();
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query);
					String a = "";

					while (rs.next()) {
						String userid = rs.getString("id");
						String name = rs.getString("name");
						String phoneNum = rs.getString("phoneNum");
						if (usernameF.getText().equals(name) && phoneNumF.getText().equals(phoneNum)) {
							JOptionPane.showMessageDialog(Findid.this, "아이디는 " + userid);
							b = true;
							usernameF.setText("");
							phoneNumF.setText("");
							setVisible(false);
						} else {

							if (usernameF.getText().equals("") || phoneNumF.getText().equals("")
									|| !usernameF.getText().equals(name) || !phoneNumF.getText().equals(phoneNum)) {
								a = "이름 또는 전화번호를 확인하세요.";

							}
						}
					}
					if (!b) {
						JOptionPane.showMessageDialog(Findid.this, a);

					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					BusanUtil.closeRS(rs);
					BusanUtil.closeStmt(stmt);
					BusanUtil.closeConn(conn);
				}
			}
		});

		setLocationRelativeTo(null); // 창이 가운데에서 출력된다
		setResizable(false);
		usernameF.setToolTipText("이름을 입력하세요!");
		phoneNumF.setToolTipText("전화번호를 입력하세요!");

	}

	public static void main(String[] args) {
		new Findid().setVisible(true);
	}

}
