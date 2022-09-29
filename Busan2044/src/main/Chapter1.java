package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import kr.co.green.BusanUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Chapter1 extends JFrame {

	private List<Story> list;
	private List<ChoiceSum> choiceList;
	private int sn = 0;
	private int snChoice = 0;
	private JTextArea storyArea;
	private JButton[] btnChoice = new JButton[5]; // 버튼 처리를 위해 옮김
	private JPanel choicePnl; // 버튼 패널 추가
	private Random rd = new Random();

	public static Font mainFont = new Font("한컴산뜻돋움", Font.BOLD, 20);
	private boolean req/* requirement */ = false, bandageEv = false, stop = false;

	// btnChoice 클릭 액션 처리 메소드
	private void storyUpdate(int btnNum) {
		// btnNum = 몇 번째 버튼인지 입력
		int btn = btnNum - 1;
		sn++;

		choicePnl.setVisible(false);
		switch (snChoice) {
		case 0: {
			storyArea.setText(getAchoice(btn).getStoryMain());
			storyArea.setCaretPosition(0);
			break;
		}
		case 1: {
			btn += 4;
			storyArea.setText(getAchoice(btn).getStoryMain());
			storyArea.setCaretPosition(0);
			break;
		}
		case 2: {
			btn += 6;
			storyArea.setText(getAchoice(btn).getStoryMain());
			storyArea.setCaretPosition(0);
			break;
		}
		case 3: {
			btn += 11;
			storyArea.setText("(전투발생 이벤트)");
			break;
		}
		case 4: {
			btn += 13;
			storyArea.setText(getAchoice(btn).getStoryMain());
			storyArea.setCaretPosition(0);
			break;
		}
		case 5: {
			btn += 16;
			storyArea.setText(getAchoice(btn).getStoryMain());
			storyArea.setCaretPosition(0);
			break;
		}
		case 6: {
			// 랜덤 발생
			break;
		}
		case 7: {
			btn += 22;
			storyArea.setText(getAchoice(btn).getStoryMain());
			storyArea.setCaretPosition(0);
			break;
		}
		case 8: {
			btn += 25;
			storyArea.setText(getAchoice(btn).getStoryMain());
			storyArea.setCaretPosition(0);
			break;
		}
		}
	}

	// choiceText에서 사용할 for문 메소드 제작
	private void choiceFor(int btnNum, int listIndex) {
		// btnNum = 선택지 개수(버튼 개수)
		// listIndex = choiceList의 몇 번째 index에서 시작할 지

		for (int i = 0; i < btnChoice.length; i++) {
			btnChoice[i].setVisible(false);
		}

		for (int i = 0; i < btnNum; i++, listIndex++) {
			btnChoice[i].setText(getAchoice(listIndex).getChoiceMain());
			btnChoice[i].setVisible(true);
			if (snChoice == 7 && i == 1) {
				btnChoice[i].setText(getAchoice(listIndex + 1).getChoiceMain());
				break;
			} else if (snChoice == 8 && i == 0) {
				btnChoice[i].setText(getAchoice(listIndex + 1).getChoiceMain());
			} else if (snChoice == 8 && i == 1) {
				btnChoice[i].setText(getAchoice(listIndex + 1).getChoiceMain());
				break;
			}
		}

	}

	// 선택지 변경 메소드
	private void choiceText(int choiceNum) {
		// choiceNum = 몇 번째 선택지 인지(아마 snChoice 입력하면 될듯)
		sn--;
		switch (choiceNum) {
		case 0: {
			choiceFor(4, 0);
			break;
		}
		case 1: {
			choiceFor(2, 4);
			break;
		}
		case 2: {
			choiceFor(5, 6);
			break;
		}
		case 3: {
			// 전투 발생
			break;
		}
		case 4: {
			choiceFor(3, 13);
			break;
		}
		case 5: {
			choiceFor(4, 16);
			break;
		}
		case 6: {
			// 랜덤 발생
			break;
		}
		case 7: {
			choiceFor(2, 22);
			break;
		}
		case 8: {
			choiceFor(2, 24);
			if (req == true) {
				btnChoice[0].setVisible(true);
			}
			break;
		}

		}
		sn++;
		choicePnl.setVisible(false);
	}

	// DB에서 chapter1_choice 테이블 리스트에 담기
	private ChoiceSum getAchoice(int a) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		choiceList = new ArrayList<>();

		try {
			conn = BusanUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM `busan`.chapter1_choiceSum");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ChoiceSum cic = new ChoiceSum(rs.getInt("choiceId"), rs.getString("storyNum"),
						rs.getString("choiceMain"), rs.getString("StoryMain"), rs.getString("storyCheck"),
						rs.getString("choiceCheck"));
				choiceList.add(cic);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
			BusanUtil.closeConn(conn);
		}
		return choiceList.get(a);
	}

	// DB에서 chapter1 테이블 리스트에 담기
	private Story getAstory(int selecRow) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		list = new ArrayList<>();

		try {

			conn = BusanUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM busan.chapter1_storyonly");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Story str = new Story(rs.getInt("storyId"), rs.getString("storyNum"), rs.getString("storyMain"),
						rs.getInt("storyTime"), rs.getString("check"));

				list.add(str);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BusanUtil.closeRS(rs);
			BusanUtil.closeStmt(pstmt);
			BusanUtil.closeConn(conn);
		}
		return list.get(selecRow);
	}

	public Chapter1() {
		super("부산2044");
		// 제일 큰 패널
		JPanel pnlBBG = new JPanel();

// --------------------------------------------

		// 텍스트 나오는 부분
		JPanel pnlTxt = new JPanel();
		pnlTxt.setBounds(12, 10, 824, 841);
		pnlTxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlTxt.setPreferredSize(new Dimension(800, 700));

		// 상태가 표시되는 패널
		JPanel pnlState = new JPanel();
		pnlState.setBounds(848, 10, 324, 841);
		pnlTxt.setLayout(null);
//		대수 추가
		// 스크롤 추가
		JScrollPane jsp = new JScrollPane(); // JScrollPane(스크롤 생성)은 생성자에 참조변수(JTextArea)가 있어야 한다.
		jsp.setBounds(12, 10, 800, 615);
		pnlTxt.add(jsp);
		jsp.setBackground(Color.BLACK);

		// 스토리 나오는 TextArea
		storyArea = new JTextArea("(PRESS ANY KEY)");
		storyArea.setFont(mainFont);
		jsp.setViewportView(storyArea);
		storyArea.setEditable(false);
		pnlBBG.add(pnlState);

//		 클릭 시 다음 스토리 노출
		storyArea.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if (snChoice == 3 || snChoice == 6) {
						snChoice++;
					}

					storyArea.setText(getAstory(sn).getStoryMain());

					if (getAstory(sn).getCheck() != null && getAstory(sn).getCheck().equals("선택지")) {
						choiceText(snChoice);
						choicePnl.setVisible(true);
						sn--;
					}
					if (getAstory(sn).getCheck() != null && getAstory(sn).getCheck().equals("확률")) {
						int aHalf = 20 + rd.nextInt(2);
						storyArea.setText(getAchoice(aHalf).getStoryMain());
					}

					sn++;

					if (snChoice == 8 && stop == true) {
						storyArea.setText(getAchoice(23).getStoryMain());
						stop = false;
						sn--;
					}
//					System.out.println(sn + "\t" + snChoice);
				} catch (IndexOutOfBoundsException ex) {
					storyArea.setText(
							"\n\n\n\n\n\t\tT\n\t\tH\n\t\tA\n\t\tN\n\t\tK\n\n\t\tU\n\t\n\t                    for playing !");
//					System.exit(0);
				} finally {
					storyArea.setCaretPosition(0);
				}
			}
		});
//		대수 추가

// --------------------------------------------

		// 메뉴 버튼
		JButton menu;
		menu = new JButton("메뉴");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		menu.setBounds(243, 10, 69, 71);

		// 현재상태 표시되는 곳
		JPanel state = new JPanel();
		state.setBounds(12, 101, 300, 160);
		JLabel stateSet;
		stateSet = new JLabel("상태들은 여기에 표시");
		stateSet.setBounds(66, 41, 186, 15);

		// 시간 표시되는 부분
		JPanel time = new JPanel();
		time.setBounds(12, 10, 217, 81);
		JLabel timeSet;
		timeSet = new JLabel("낮 밤 표시");
		timeSet.setBounds(57, 25, 106, 15);

		// 인벤토리
		JPanel inven = new JPanel();
		inven.setBounds(12, 271, 300, 384);
		JLabel weight;
		weight = new JLabel("무게");
		weight.setBounds(35, 10, 73, 27);
		JLabel invenSet;
		invenSet = new JLabel("인벤토리 여기에 표시");
		invenSet.setBounds(45, 173, 174, 15);

		// 동료
		JPanel team = new JPanel();
		team.setBounds(12, 665, 300, 166);

		// 우리 할머니는 항상 고정 1번
		JLabel team1;
		team1 = new JLabel("할머니");
		team1.setBounds(32, 56, 69, 15);

		JLabel team2;
		team2 = new JLabel("조원1");
		team2.setBounds(113, 56, 53, 15);

		JLabel team3;
		team3 = new JLabel("조원2");
		team3.setBounds(178, 56, 80, 15);

		pnlState.setLayout(null);
		pnlState.add(menu);
		pnlState.add(state);
		pnlState.add(time);
		pnlState.add(inven);
		pnlState.add(team);
		state.setLayout(null);

		state.add(stateSet);
		time.setLayout(null);
		time.add(timeSet);
		inven.setLayout(null);
		inven.add(invenSet);
		inven.add(weight);
		team.setLayout(null);
		team.add(team1);
		team.add(team2);
		team.add(team3);

		pnlBBG.setLayout(null);
		pnlBBG.add(pnlTxt);

		choicePnl = new JPanel();
		choicePnl.setBounds(12, 607, 800, 224);
		pnlTxt.add(choicePnl);
		choicePnl.setLayout(null);

		// 선택지 고르는 버튼 (최대 다섯개)
		btnChoice[0] = new JButton("선택지1");
		btnChoice[0].setBounds(10, 28, 782, 35);
		choicePnl.add(btnChoice[0]);

		btnChoice[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choiceText(snChoice);
				storyUpdate(1);
				if (snChoice == 7) {
					stop = true;
				}
				snChoice++;
			}
		});

		btnChoice[1] = new JButton("선택지2");
		btnChoice[1].setBounds(10, 68, 782, 35);
		choicePnl.add(btnChoice[1]);

		btnChoice[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choiceText(snChoice);
				storyUpdate(2);
				if (snChoice == 7) {
					storyArea.setText(getAchoice(24).getStoryMain());
				}
				snChoice++;
			}
		});

		btnChoice[2] = new JButton("선택지3");
		btnChoice[2].setBounds(10, 108, 782, 35);
		choicePnl.add(btnChoice[2]);

		btnChoice[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choiceText(snChoice);
				storyUpdate(3);
				snChoice++;
			}
		});

		btnChoice[3] = new JButton("선택지4");
		btnChoice[3].setBounds(10, 148, 782, 35);
		choicePnl.add(btnChoice[3]);

		btnChoice[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choiceText(snChoice);
				storyUpdate(4);
				snChoice++;
			}
		});

		btnChoice[4] = new JButton("선택지5");
		btnChoice[4].setBounds(10, 188, 782, 35);
		choicePnl.add(btnChoice[4]);

		btnChoice[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choiceText(snChoice);
				storyUpdate(5);
				snChoice++;
			}
		});

		choicePnl.setVisible(false);
//			선택지 끝

		getContentPane().add(pnlBBG);
		setSize(1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

	}

	public static void main(String[] args) {
		new Chapter1().setVisible(true);
	}
}
