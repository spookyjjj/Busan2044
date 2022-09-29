package loginUser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bulletin.Bulletin;

public class Login extends JFrame {
//	private boolean b = false;
	private BusanUser user;
	private Busanlogin dao = new Busanlogin();
	private List<BusanUser> list = new ArrayList<>();
//	

	public Login() {
		super("로그인 프로그램");
		JPanel pnl = new JPanel();
		JTextField id = new JTextField(10);
		// TODO 폰트
		id.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		id.setBounds(160, 463, 253, 28);
		JPasswordField ps = new JPasswordField(10);
		ps.setBounds(160, 510, 253, 30);

		Join join = new Join();
		Findpw fpw = new Findpw();
		Findid fid = new Findid();

		URL mainImg = Login.class.getClassLoader().getResource("main.jpg");
		ImageIcon mainIcon = new ImageIcon(mainImg);
		JLabel mainlbl = new JLabel();
		mainlbl.setBounds(0, 0, 480, 800);
		mainlbl.setIcon(mainIcon);

		// TODO 폰트
		JButton btn = new JButton("로그인");
		btn.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		btn.setBounds(74, 575, 126, 31);
		btn.setBackground(Color.DARK_GRAY);
		btn.setForeground(Color.white);

		// TODO 폰트
		JButton btn2 = new JButton("회원가입");
		btn2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		btn2.setBounds(276, 575, 126, 31);
		btn2.setBackground(Color.DARK_GRAY);
		btn2.setForeground(Color.white);

		// TODO 폰트
		JLabel idlbl = new JLabel("아이디");
		idlbl.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
		idlbl.setBounds(63, 469, 57, 15);
		idlbl.setForeground(Color.DARK_GRAY);

		// TODO 폰트
		JLabel pwlbl = new JLabel("비밀번호");
		pwlbl.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
		pwlbl.setBounds(63, 517, 70, 15);
		pwlbl.setForeground(Color.DARK_GRAY);

		// TODO 폰트
		JButton btn3 = new JButton("아이디 찾기");
		btn3.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		btn3.setBounds(74, 632, 126, 31);
		btn3.setBackground(Color.DARK_GRAY);
		btn3.setForeground(Color.white);

		JButton btn4 = new JButton("비밀번호 찾기");
		btn4.setFont(new Font("한컴산뜻돋움", Font.BOLD, 13));
		btn4.setBounds(276, 632, 126, 31);
		btn4.setBackground(Color.DARK_GRAY);
		btn4.setForeground(Color.white);

		getContentPane().add(pnl);
		pnl.setLayout(null);
		pnl.add(idlbl);
		pnl.add(id);
		pnl.add(pwlbl);
		pnl.add(ps);
		pnl.add(btn);
		pnl.add(btn2);
		pnl.add(btn3);
		pnl.add(btn4);
		pnl.add(mainlbl);

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					list = dao.read();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				user = new BusanUser(id.getText(), ps.getText());

				if (list.contains(user)) {
					user = list.get(list.indexOf(user));
					Login.this.setVisible(false);
					new Bulletin(user).setVisible(true);
					// 다음 프레임에 유저 정보를 전달
					// Frame a = new Frame(user);

				} else if (id.getText().equals("")) {
					JOptionPane.showMessageDialog(Login.this, "아이디를 입력해주세요.");
				} else if (ps.getText().equals("")) {
					JOptionPane.showMessageDialog(Login.this, "비밀번호를 입력해주세요.");
				} else {
					JOptionPane.showMessageDialog(Login.this, "아이디나 비밀번호를 확인하세요.");
				}

//				String query = "SELECT * FROM login_info";		

//				Connection conn = null;
//				Statement stmt = null;
//				ResultSet rs = null;
//				
//				try {
//					conn = BusanUtil.getConnection();
//					stmt = conn.createStatement();
//					rs = stmt.executeQuery(query);
//					String a = "";
//					
//					while (rs.next()) {
//						String userid = rs.getString("id");
//						String userpassword = rs.getString("password");
//						
//						
//						if (id.getText().equals(userid) && ps.getText().equals(userpassword)) {
//							JOptionPane.showMessageDialog(Login.this, "로그인 되었습니다");
//							b = true;
//							setVisible(false);
//						} else { 
//							if (!id.getText().equals(userid) || !ps.getText().equals(userpassword)) {
//								a = "아이디 또는 비밀번호를 확인하세요.";
//								}
//						}
//					}
//					if (!b) {
//					JOptionPane.showMessageDialog(Login.this, a);
//					}
//					
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				} finally {
//					BusanUtil.closeRS(rs);
//					BusanUtil.closeStmt(stmt);
//					BusanUtil.closeConn(conn);
//				}

			}
		});

		id.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					list = dao.read(); // DB에서 정보를 다 읽어와서 리스트에 저장
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				user = new BusanUser(id.getText(), ps.getText());

				if (list.contains(user)) { // 리스트안에 유저의 아이디가 있으면
					user = list.get(list.indexOf(user)); // 그 아이디가 있는 인덱스번호를 알아낸후 그 안에 모든 정보를 유저에 저장
					new Bulletin(user).setVisible(true); // 다음 프레임에 생성자에 유저를 받을수있게 만든후 실행
					Login.this.setVisible(false);
					// 다음 프레임에 유저 정보를 전달
					// Frame a = new Frame(user);

				} else if (id.getText().equals("")) {
					JOptionPane.showMessageDialog(Login.this, "아이디를 입력해주세요.");
				} else if (ps.getText().equals("")) {
					JOptionPane.showMessageDialog(Login.this, "비밀번호를 입력해주세요.");
				} else {
					JOptionPane.showMessageDialog(Login.this, "아이디나 비밀번호를 확인하세요.");
				}
			}
		});

		ps.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					list = dao.read(); // DB에서 정보를 다 읽어와서 리스트에 저장
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				user = new BusanUser(id.getText(), ps.getText());

				if (list.contains(user)) { // 리스트안에 유저의 아이디가 있으면
					user = list.get(list.indexOf(user)); // 그 아이디가 있는 인덱스번호를 알아낸후 그 안에 모든 정보를 유저에 저장
					new Bulletin(user).setVisible(true); // 다음 프레임에 생성자에 유저를 받을수있게 만든후 실행
					Login.this.setVisible(false);
					// 다음 프레임에 유저 정보를 전달
					// Frame a = new Frame(user);

				} else if (id.getText().equals("")) {
					JOptionPane.showMessageDialog(Login.this, "아이디를 입력해주세요.");
				} else if (ps.getText().equals("")) {
					JOptionPane.showMessageDialog(Login.this, "비밀번호를 입력해주세요.");
				} else {
					JOptionPane.showMessageDialog(Login.this, "아이디나 비밀번호를 확인하세요.");
				}
			}
		});

		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				join.setVisible(true);

			}
		});

		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fid.setVisible(true);

			}
		});

		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fpw.setVisible(true);

			}
		});

		setSize(480, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pnl.setBackground(Color.black);
		id.setToolTipText("아이디를 입력하세요!");
		ps.setToolTipText("비밀번호를 입력하세요!");

		setLocationRelativeTo(null); // 창이 가운데에서 출력된다
		setResizable(false); // 사이즈 조정 불가

	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			
		}
		Login l = new Login();
		l.setVisible(true);
	}

}
