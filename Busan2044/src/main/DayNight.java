package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import bulletin.Bulletin;
import kr.co.green.BusanUtil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class DayNight extends JFrame {

	public JPanel pnlBBG;
	private UserInfo user = new UserInfo();
	private JTextArea storyArea;
	private JButton[] btnChoice = new JButton[5]; // 버튼 처리를 위해 옮김
	private JPanel choicePnl; // 버튼 패널 추가
	private int find = 0;
	private int buy = 0;
	private int sleep = 0;
	private int date = 1;

	public static Font mainFont = new Font("한컴산뜻돋움", Font.BOLD, 20);

	public DayNight() {
		super("부산2044");
		// 제일 큰 패널
		pnlBBG = new JPanel();

// --------------------------------------------

		// 텍스트 나오는 부분
		JPanel pnlTxt = new JPanel();
		pnlTxt.setBounds(12, 10, 824, 841);
		pnlTxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlTxt.setPreferredSize(new Dimension(800, 700));
		pnlTxt.setLayout(null);
//		대수 추가
		// 스크롤 추가
		JScrollPane jsp = new JScrollPane();
		jsp.setBounds(12, 10, 800, 615);
		pnlTxt.add(jsp);
		jsp.setBackground(Color.BLACK);

		// 스토리 나오는 TextArea
		storyArea = new JTextArea("(PRESS ANY KEY)");
		storyArea.setFont(mainFont);
		jsp.setViewportView(storyArea);
		storyArea.setEditable(false);

//	

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

		btnChoice[1] = new JButton("선택지2");
		btnChoice[1].setBounds(10, 68, 782, 35);
		choicePnl.add(btnChoice[1]);

		btnChoice[2] = new JButton("선택지3");
		btnChoice[2].setBounds(10, 108, 782, 35);
		choicePnl.add(btnChoice[2]);

		btnChoice[3] = new JButton("선택지4");
		btnChoice[3].setBounds(10, 148, 782, 35);
		choicePnl.add(btnChoice[3]);

		btnChoice[4] = new JButton("선택지5");
		btnChoice[4].setBounds(10, 188, 782, 35);
		choicePnl.add(btnChoice[4]);

		choicePnl.setVisible(false);
//			선택지 끝

		getContentPane().add(pnlBBG);

// 밤낮 버튼 잘라쓰기
// -----------------------------------------------------------------------------------------------------
		// 필드에 추가하기
//		private int find = 0;
//		private int buy = 0;
//		private int sleep = 0;
//		private int date = 1;
		
		
		URL morningImg = Bulletin.class.getClassLoader().getResource("bg1.png");
		URL afternoonImg = Bulletin.class.getClassLoader().getResource("bg2.png");
		URL nightImg = Bulletin.class.getClassLoader().getResource("bg3.png");
		ImageIcon morning = new ImageIcon(morningImg);
		ImageIcon afternoon = new ImageIcon(afternoonImg);
		ImageIcon night = new ImageIcon(nightImg);

		JLabel dayNightImg = new JLabel("낮밤이미지");
		dayNightImg.setIcon(morning);
		jsp.setColumnHeaderView(dayNightImg);

		JButton btnFind = new JButton("탐색하기");
		btnFind.setBounds(865, 32, 97, 23);
		pnlBBG.add(btnFind);

		JButton btnBuy = new JButton("상점가기");
		btnBuy.setBounds(975, 32, 97, 23);
		pnlBBG.add(btnBuy);

		JButton btnSleep = new JButton("휴식하기");
		btnSleep.setBounds(1080, 32, 97, 23);
		pnlBBG.add(btnSleep);

		JLabel lblDate = new JLabel("1일");
		lblDate.setBounds(865, 75, 57, 15);
		pnlBBG.add(lblDate);

		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (date < 5) {
					find++;
					if (find == 1) {
						dayNightImg.setIcon(afternoon);
					} else if (find == 2) {
						dayNightImg.setIcon(night);
					} else if (find == 3) {
						dayNightImg.setIcon(morning);
						find = 0;
						date++;
						lblDate.setText(date + "일");
					}
				} else {
					System.out.println("게임끝");
				}

			}
		});

		btnBuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (date < 5) {
					buy++;
					if (buy == 1) {
						dayNightImg.setIcon(morning);
						buy = 0;
						find = 0;
						date++;
						lblDate.setText(date + "일");
					}
				} else {
					System.out.println("게임끝");
				}
			}
		});

		btnSleep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (date < 5) {
					sleep++;
					if (sleep == 1) {
						dayNightImg.setIcon(morning);
						sleep = 0;
						find = 0;
						date++;
						lblDate.setText(date + "일");
					}
				} else {
					System.out.println("게임끝");
				}
			}

		});

// -------------------------------------------------------------------------------------------------------------		

		setSize(1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

	}

	public static void main(String[] args) {
		new DayNight().setVisible(true);

	}
}
