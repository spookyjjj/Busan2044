package bulletin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import loginUser.BusanUser;

public class Bulletin extends JFrame {
	private JPanel[] pnlC = new JPanel[1000];
	private JLabel[] lblC = new JLabel[1000];
	private JLabel[] lblD = new JLabel[1000];
	private int total = 0;  // 미리 입력되있는 후기들 갯수를 넣기위한 변수
	private String first = "";  // 70자 까지 담아주려고 만든 변수
	private String second = "";  // 그 이후에 글자를 담아주려고 만든 변수
	private int score5 = 0;  // 별점 5점의 갯수를 추가하기 위한 변수 (총 50개)
	private int score4 = 0;  // 별점 4점의 갯수를 추가하기 위한 변수 (총 50개)
	private int score3 = 0;  // 별점 3점의 갯수를 추가하기 위한 변수 (총 50개)
	private int score2 = 0;  // 별점 2점의 갯수를 추가하기 위한 변수 (총 50개)
	private int score1 = 0;  // 별점 1점의 갯수를 추가하기 위한 변수 (총 50개)
	private int starPoint = 0;  // 별점 몇점을 체크했는지 알아내려고 만든 변수 >> 디비로 바로 create함
	private BusanUser user;
	private BulletinDao bul = new BulletinDao();
	private List<BulletinInfo> list = new ArrayList<>(); // DB에서 불러온 정보를 담으려고 만든 리스트

	
	public String line() {
		return "<html><body><center>" + first + "<br>" + second + "</center></body></html>";
	}

	public String star(int r) {
		String star = "";
		for (int i = 0; i < r; i++) {
			star += "■";
		}

		return star;
	}

	public Bulletin(BusanUser user) {
		super("후기");
		this.user = user;
		
		URL emptyStar = Bulletin.class.getClassLoader().getResource("empty.png");
		URL fullStar = Bulletin.class.getClassLoader().getResource("full.png");
		ImageIcon emptyimg = new ImageIcon(emptyStar);
		ImageIcon fullimg = new ImageIcon(fullStar);
		
		// DB에서 댓글 불러오기
		try {
			list = bul.read();
		} catch (SQLException e1) {
					e1.printStackTrace();
		}
		total = list.size();

		setVisible(true);
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel starPnl = new JPanel();
		starPnl.setBounds(12, 10, 970, 212);
		starPnl.setBackground(Color.white);
		getContentPane().add(starPnl);
		starPnl.setLayout(null);
		

		JLabel five = new JLabel("5점");
		five.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		five.setBounds(204, 24, 57, 15);
		starPnl.add(five);
		JLabel fiveG = new JLabel();
		fiveG.setBounds(257, 24, 674, 15);
		starPnl.add(fiveG);

		JLabel four = new JLabel("4점");
		four.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		four.setBounds(204, 59, 57, 15);
		starPnl.add(four);
		JLabel fourG = new JLabel();
		fourG.setBounds(257, 59, 674, 15);
		starPnl.add(fourG);

		JLabel three = new JLabel("3점");
		three.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		three.setBounds(204, 94, 57, 15);
		starPnl.add(three);
		JLabel threeG = new JLabel();
		threeG.setBounds(257, 94, 674, 15);
		starPnl.add(threeG);

		JLabel two = new JLabel("2점");
		two.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		two.setBounds(204, 129, 57, 15);
		starPnl.add(two);
		JLabel twoG = new JLabel();
		twoG.setBounds(257, 129, 674, 15);
		starPnl.add(twoG);

		JLabel one = new JLabel("1점");
		one.setFont(new Font("한컴산뜻돋움", Font.BOLD, 14));
		one.setBounds(204, 164, 57, 15);
		starPnl.add(one);
		JLabel oneG = new JLabel();
		oneG.setBounds(257, 164, 674, 15);
		starPnl.add(oneG);

		JLabel img = new JLabel();
		img.setFont(new Font("한컴산뜻돋움", Font.BOLD, 70));
		img.setBounds(46, 21, 116, 125);
		starPnl.add(img);

		JLabel star1 = new JLabel();
		star1.setBounds(25, 153, 30, 30);
		starPnl.add(star1);

		JLabel star2 = new JLabel();
		star2.setBounds(55, 153, 30, 30);
		starPnl.add(star2);

		JLabel star3 = new JLabel();
		star3.setBounds(85, 153, 30, 30);
		starPnl.add(star3);

		JLabel star4 = new JLabel();
		star4.setBounds(115, 153, 30, 30);
		starPnl.add(star4);

		JLabel star5 = new JLabel();
		star5.setBounds(145, 153, 30, 30);
		starPnl.add(star5);
		
		// DB에서 별점 불러오기
		score5 = bul.BulletStar(5);
		score4 = bul.BulletStar(4);
		score3 = bul.BulletStar(3);
		score2 = bul.BulletStar(2);
		score1 = bul.BulletStar(1);
		fiveG.setText(star(score5));
		fourG.setText(star(score4));
		threeG.setText(star(score3));
		twoG.setText(star(score2));
		oneG.setText(star(score1));
		
		// DB에서 평균 불러오기 
		img.setText(bul.StarAvg());

		// 별점 이미지 넣는부분
		star1.setIcon(emptyimg);
		star2.setIcon(emptyimg);
		star3.setIcon(emptyimg);
		star4.setIcon(emptyimg);
		star5.setIcon(emptyimg);

		
		
	

		// 별점 바뀌는 부분
		star1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				star1.setIcon(fullimg);
				star2.setIcon(emptyimg);
				star3.setIcon(emptyimg);
				star4.setIcon(emptyimg);
				star5.setIcon(emptyimg);
				starPoint = 1;
			}
		});

		star2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				star1.setIcon(fullimg);
				star2.setIcon(fullimg);
				star3.setIcon(emptyimg);
				star4.setIcon(emptyimg);
				star5.setIcon(emptyimg);
				starPoint = 2;
			}
		});

		star3.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				star1.setIcon(fullimg);
				star2.setIcon(fullimg);
				star3.setIcon(fullimg);
				star4.setIcon(emptyimg);
				star5.setIcon(emptyimg);
				starPoint = 3;
			}
		});

		star4.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				star1.setIcon(fullimg);
				star2.setIcon(fullimg);
				star3.setIcon(fullimg);
				star4.setIcon(fullimg);
				star5.setIcon(emptyimg);
				starPoint = 4;
			}
		});

		star5.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				star1.setIcon(fullimg);
				star2.setIcon(fullimg);
				star3.setIcon(fullimg);
				star4.setIcon(fullimg);
				star5.setIcon(fullimg);
				starPoint = 5;
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 232, 970, 470);
		getContentPane().add(scrollPane);
		scrollPane.getVerticalScrollBarPolicy();

		JPanel mainPnl = new JPanel();
		scrollPane.setViewportView(mainPnl);
		mainPnl.setLayout(null);
		

		BoxLayout box = new BoxLayout(mainPnl, BoxLayout.Y_AXIS);
		mainPnl.setLayout(box);

		JPanel pnlComment = new JPanel();
		pnlComment.setBounds(12, 712, 970, 49);
		getContentPane().add(pnlComment);
		pnlComment.setLayout(null);

		JTextField textField = new JTextField();
		textField.setBounds(12, 10, 846, 29);
		textField.setColumns(10);
		pnlComment.add(textField);

		JButton upload = new JButton("등록하기");
		upload.setBackground(Color.WHITE);
		upload.setBounds(868, 10, 90, 29);
		pnlComment.add(upload);
		

		// 배열수 만큼 패널이랑 라벨 만들기
		for (int i = 0; i < pnlC.length; i++) {
			pnlC[i] = new JPanel();
//			pnlC[i].setBounds(0, 0, 100, 70);
			pnlC[i].setBackground(Color.white);
			mainPnl.add(pnlC[i]);
			lblC[i] = new JLabel();
			lblD[i] = new JLabel();
			pnlC[i].add(lblC[i]);
			pnlC[i].add(lblD[i]);
			lblC[i].setFont(new Font("한컴산뜻돋움", Font.BOLD, 16));
			lblD[i].setFont(new Font("한컴산뜻돋움", Font.BOLD, 16));
		
		}
		
		// DB 에서 내용 불러오기 (아이디, 후기)
		for(int i = 0; i < list.size(); i++) {
			lblC[i].setText(list.get(i).getUserId() + "   :  ");
			lblD[i].setText(list.get(i).getText());
		}

		// 텍스트 필드에서 엔터를 눌렀을때
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) { // 텍스트 필드가 비어있지 않다면
					if (textField.getText().length() >= 70) { // 텍스트 길이가 70자보다 길다면

						if (star5.getIcon().equals(fullimg) && score5 <= 50) {
							score5++;
							fiveG.setText(star(score5));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star4.getIcon().equals(fullimg) && star5.getIcon().equals(emptyimg)
								&& score4 <= 50) {
							score4++;
							fourG.setText(star(score4));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star3.getIcon().equals(fullimg) && star4.getIcon().equals(emptyimg)
								&& star5.getIcon().equals(emptyimg) && score3 <= 50) {
							score3++;
							threeG.setText(star(score3));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star2.getIcon().equals(fullimg) && star3.getIcon().equals(emptyimg)
								&& star4.getIcon().equals(emptyimg) && star5.getIcon().equals(emptyimg)
								&& score2 <= 50) {
							score2++;
							twoG.setText(star(score2));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star1.getIcon().equals(fullimg) && star2.getIcon().equals(emptyimg)
								&& star3.getIcon().equals(emptyimg) && star4.getIcon().equals(emptyimg)
								&& star5.getIcon().equals(emptyimg) && score1 <= 50) {
							score1++;
							oneG.setText(star(score1));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star1.getIcon().equals(emptyimg)) {
							JOptionPane.showMessageDialog(Bulletin.this, "별점을 넣어주세요!");
						} else {
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						}
						;

					} else {
						if (star5.getIcon().equals(fullimg) && score5 <= 50) {
							score5++;
							fiveG.setText(star(score5));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star4.getIcon().equals(fullimg) && star5.getIcon().equals(emptyimg)
								&& score4 <= 50) {
							score4++;
							fourG.setText(star(score4));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star3.getIcon().equals(fullimg) && star4.getIcon().equals(emptyimg)
								&& star5.getIcon().equals(emptyimg) && score3 <= 50) {
							score3++;
							threeG.setText(star(score3));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star2.getIcon().equals(fullimg) && star3.getIcon().equals(emptyimg)
								&& star4.getIcon().equals(emptyimg) && star5.getIcon().equals(emptyimg)
								&& score2 <= 50) {
							score2++;
							twoG.setText(star(score2));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star1.getIcon().equals(fullimg) && star2.getIcon().equals(emptyimg)
								&& star3.getIcon().equals(emptyimg) && star4.getIcon().equals(emptyimg)
								&& star5.getIcon().equals(emptyimg) && score1 <= 50) {
							score1++;
							oneG.setText(star(score1));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star1.getIcon().equals(emptyimg)) {
							JOptionPane.showMessageDialog(Bulletin.this, "별점을 넣어주세요!");
						} else {
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						}
						
					}
				} else {
					JOptionPane.showMessageDialog(Bulletin.this, "후기를 입력해주세요!");
				}
//				BB.BulletCreate(BB.BulletInputId(i), BB.BulletInputName(i), textField.getText(), 5);
			}
		});


		upload.addActionListener(new ActionListener() {

			// 등록하기 버튼을 눌렀을때
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) { // 텍스트 필드가 비어있지 않다면
					if (textField.getText().length() >= 70) { // 텍스트 길이가 70자보다 길다면

						if (star5.getIcon().equals(fullimg) && score5 <= 50) {
							score5++;
							fiveG.setText(star(score5));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star4.getIcon().equals(fullimg) && star5.getIcon().equals(emptyimg)
								&& score4 <= 50) {
							score4++;
							fourG.setText(star(score4));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star3.getIcon().equals(fullimg) && star4.getIcon().equals(emptyimg)
								&& star5.getIcon().equals(emptyimg) && score3 <= 50) {
							score3++;
							threeG.setText(star(score3));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star2.getIcon().equals(fullimg) && star3.getIcon().equals(emptyimg)
								&& star4.getIcon().equals(emptyimg) && star5.getIcon().equals(emptyimg)
								&& score2 <= 50) {
							score2++;
							twoG.setText(star(score2));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star1.getIcon().equals(fullimg) && star2.getIcon().equals(emptyimg)
								&& star3.getIcon().equals(emptyimg) && star4.getIcon().equals(emptyimg)
								&& star5.getIcon().equals(emptyimg) && score1 <= 50) {
							score1++;
							oneG.setText(star(score1));
							first = textField.getText().substring(0, 70);
							second = textField.getText().substring(71);
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(line());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star1.getIcon().equals(emptyimg)) {
							JOptionPane.showMessageDialog(Bulletin.this, "별점을 넣어주세요!");
						} else {
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						}
						;

					} else {
						if (star5.getIcon().equals(fullimg) && score5 <= 50) {
							score5++;
							fiveG.setText(star(score5));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star4.getIcon().equals(fullimg) && star5.getIcon().equals(emptyimg)
								&& score4 <= 50) {
							score4++;
							fourG.setText(star(score4));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star3.getIcon().equals(fullimg) && star4.getIcon().equals(emptyimg)
								&& star5.getIcon().equals(emptyimg) && score3 <= 50) {
							score3++;
							threeG.setText(star(score3));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star2.getIcon().equals(fullimg) && star3.getIcon().equals(emptyimg)
								&& star4.getIcon().equals(emptyimg) && star5.getIcon().equals(emptyimg)
								&& score2 <= 50) {
							score2++;
							twoG.setText(star(score2));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star1.getIcon().equals(fullimg) && star2.getIcon().equals(emptyimg)
								&& star3.getIcon().equals(emptyimg) && star4.getIcon().equals(emptyimg)
								&& star5.getIcon().equals(emptyimg) && score1 <= 50) {
							score1++;
							oneG.setText(star(score1));
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						} else if (star1.getIcon().equals(emptyimg)) {
							JOptionPane.showMessageDialog(Bulletin.this, "별점을 넣어주세요!");
						} else {
							lblC[total].setText(user.getId() + "  :	");
							lblD[total].setText(textField.getText());
							bul.BulletCreate(user.getId(), "업적", textField.getText(), starPoint);
							total += 1;
							textField.setText("");
							star1.setIcon(emptyimg);
							star2.setIcon(emptyimg);
							star3.setIcon(emptyimg);
							star4.setIcon(emptyimg);
							star5.setIcon(emptyimg);
						}
						
					}
				} else {
					JOptionPane.showMessageDialog(Bulletin.this, "후기를 입력해주세요!");
				}
//				BB.BulletCreate(BB.BulletInputId(i), BB.BulletInputName(i), textField.getText(), 5);
			}
		});

	}

//
//	public static void main(String[] args) {
//		new Bullentin().setVisible(true);
//	}
}