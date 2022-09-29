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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.green.BusanUtil;


public class Join extends JFrame {
	private Busanlogin user = new Busanlogin();

	private Map<String, String> map;


	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	
	public Join() {
		super("회원가입 프로그램");
		map = new HashMap<>();
		
		JPanel pnl = new JPanel();
		JTextField id = new JTextField(10);
		// TODO 폰트
		id.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		id.setBounds(125, 25, 145, 28);
		JPasswordField ps = new JPasswordField(10);
		ps.setBounds(125, 70, 145, 30);

		JPasswordField psre = new JPasswordField();
		psre.setBounds(125, 115, 145, 28);
		pnl.add(psre);
		psre.setColumns(10);

		getContentPane().add(pnl);
		pnl.setLayout(null);
		pnl.add(id);
		pnl.add(ps);

		JButton btn = new JButton("회원가입");
		// TODO 폰트
		btn.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		btn.setBounds(90, 302, 126, 31);
		btn.setBackground(Color.white);
		pnl.add(btn);

		JLabel idlbl = new JLabel("아이디(닉네임)");
		// TODO 폰트
		idlbl.setFont(new Font("HY목각파임B", Font.BOLD, 11));
		idlbl.setBounds(27, 32, 90, 15);
		pnl.add(idlbl);

		JLabel pslbl = new JLabel("비밀번호");
		// TODO 폰트
		pslbl.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		pslbl.setBounds(27, 77, 57, 15);
		pnl.add(pslbl);

		setSize(300, 400);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		pnl.setBackground(Color.LIGHT_GRAY);

		JLabel psrelbl = new JLabel("비밀번호 확인");
		// TODO 폰트
		psrelbl.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		psrelbl.setBounds(27, 122, 84, 15);
		pnl.add(psrelbl);
		
		JLabel username = new JLabel("이름");
		// TODO 폰트
		username.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		username.setBounds(27, 167, 57, 15);
		pnl.add(username);
		
		JLabel userbirthDay = new JLabel("생년월일");
		// TODO 폰트
		userbirthDay.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		userbirthDay.setBounds(27, 212, 57, 15);
		pnl.add(userbirthDay);
		
		JTextField usernameF = new JTextField();
		// TODO 폰트
		usernameF.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		usernameF.setBounds(125, 160, 145, 28);
		pnl.add(usernameF);
		usernameF.setColumns(10);
		
		JTextField userbirthDayF = new JTextField(10);
		// TODO 폰트
		userbirthDayF.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		userbirthDayF.setBounds(125, 205, 145, 28);
		pnl.add(userbirthDayF);
		
		JLabel userPhone = new JLabel("전화번호");
		// TODO 폰트
		userPhone.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		userPhone.setBounds(27, 257, 57, 15);
		pnl.add(userPhone);
		
		JTextField userPhoneF = new JTextField(10);
		// TODO 폰트
		userPhoneF.setFont(new Font("HY목각파임B", Font.BOLD, 12));
		userPhoneF.setBounds(125, 250, 145, 28);
		pnl.add(userPhoneF);
		
		userPhoneF.setText("010-0000-0000");
		userbirthDayF.setText("생년월일 6글자");
		
		userbirthDayF.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				
				userbirthDayF.setText("");
				super.focusGained(e);
			}
			
	         @Override
	         public void focusLost(FocusEvent e) { //포커스가 벗어났을때 ""이었으면 "값을 입력하세요"이고, 뭐라도 값이 있었으면 아무짓도 안함
	            if (userbirthDayF.getText().equals("")) {
	            	userbirthDayF.setText("생년월일 6글자");
	            }
	         }
	       
			
		});
		
		
		
		userPhoneF.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				
				userPhoneF.setText("");
				super.focusGained(e);
			}
			
			  @Override
		         public void focusLost(FocusEvent e) { //포커스가 벗어났을때 ""이었으면 "값을 입력하세요"이고, 뭐라도 값이 있었으면 아무짓도 안함
		            if (userPhoneF.getText().equals("")) {
		            	userPhoneF.setText("010-0000-0000");
		            }
		         }
			
		});
		
		Pattern pattern = Pattern.compile("[ !@#$%^&*(),.?\":{}|<>]");
		

		setLocationRelativeTo(null); // 창이 가운데에서 출력된다
		setResizable(false);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (id.getText().length() >= 4 && id.getText().length() <= 12 && ps.getText().length() >= 4
						&& ps.getText().length() <= 12) {

					String query = "SELECT * FROM login_info";

					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;

					try {
						conn = BusanUtil.getConnection();
						stmt = conn.createStatement();
						rs = stmt.executeQuery(query);

						while (rs.next()) {
							String id = rs.getString("id");
							String password = rs.getString("password");
							user.read();
							map.put(id, password);
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					} finally {
						BusanUtil.closeRS(rs);
						BusanUtil.closeStmt(stmt);
						BusanUtil.closeConn(conn);
					}
					
					if(map.containsKey(id.getText())) {
						JOptionPane.showMessageDialog(Join.this, "이미 가입된 아이디입니다");
					} else if (!id.getText().matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) {
						
							JOptionPane.showMessageDialog(Join.this, "아이디에는 특수문자를 사용할수없습니다.");
							id.setText("");
					} else if (!usernameF.getText().matches("[ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) {
						JOptionPane.showMessageDialog(Join.this, "이름에는 영어를 사용할수없습니다.");
						usernameF.setText("");
					} else {
						if (ps.getText().equals(psre.getText()) && !ps.getText().equals("") && !psre.getText().equals("") && 
								!usernameF.getText().equals("") && !userbirthDayF.getText().equals("") && userbirthDayF.getText().length() == 6 
								&& usernameF.getText().length() < 4 && userPhoneF.getText().length() == 13 
								&& (((Integer.valueOf(userbirthDayF.getText()) % 10000 / 100) <= 12 &&
										(Integer.valueOf(userbirthDayF.getText()) % 100 <= 31))) ) {
							
								try {
									user.create(id.getText(), ps.getText(), usernameF.getText(), userbirthDayF.getText(), userPhoneF.getText());
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								map.put(id.getText(), ps.getText()); // 맵에 아이디 비번 저장
								JOptionPane.showMessageDialog(Join.this, "회원가입 완료");
								id.setText("");
								ps.setText("");
								psre.setText("");
								usernameF.setText("");
								userbirthDayF.setText("생년월일 6글자");
								userPhoneF.setText("010-0000-0000");
								//System.out.println("되고있니");
								setVisible(false);
								
						} else if (usernameF.getText().equals("") || usernameF.getText().length() > 4 ) {
							JOptionPane.showMessageDialog(Join.this, "이름을 확인해주세요");
						} else if(userbirthDayF.getText().equals("") || !(userbirthDayF.getText().length() == 6)
								|| !(((Integer.valueOf(userbirthDayF.getText()) % 10000 / 100) <= 12 &&
										(Integer.valueOf(userbirthDayF.getText()) % 100 <= 31)))) {
							JOptionPane.showMessageDialog(Join.this, "생년월일을 확인해주세요");
						} else if(userPhoneF.getText().equals("") || !(userPhoneF.getText().length() == 13)) {
							JOptionPane.showMessageDialog(Join.this, "전화번호를 확인해주세요");
						} else {
							JOptionPane.showMessageDialog(Join.this, "비밀번호를 확인해주세요");
						}}

				} else {
					
					if (id.getText().length() < 4) {
						JOptionPane.showMessageDialog(Join.this, "아이디가 너무 짧습니다.");
					} else if (id.getText().length() > 12) {
						JOptionPane.showMessageDialog(Join.this, "아이디가 너무 길어요우.");
					} else if (ps.getText().length() < 4) {
						JOptionPane.showMessageDialog(Join.this, "비밀번호가 너무 짧습니다.");
					} else if (ps.getText().length() > 12) {
						JOptionPane.showMessageDialog(Join.this, "비밀번호가 너무 길어요우.");
					}
				}
			}
			
		});

		
		
	}
	

	public static void main(String[] args) {
		Join l = new Join();
		l.setVisible(true);
		
		try {
			System.out.println(l.user.read());
//			System.out.println(l.user.matchId("magic22x"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
