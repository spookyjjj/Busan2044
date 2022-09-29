package bulletin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class nonono {
	private BulletinInfo blt;

	class DrawingPanel extends JPanel {
		int five, four, three, two, one;

		
		
		public void paint(Graphics g) {
//	        g.clearRect(0,0,getWidth(),getHeight());
			g.setColor(Color.GREEN);
			g.fillRect(0, 50, 300, 10);
			g.fillRect(0, 65, 250, 10);
			g.fillRect(0, 80, 200, 10);
			g.fillRect(0, 95, 150, 10);
			g.fillRect(0, 110, 100, 10);
			g.dispose();
		}

		void setStar(int five, int four, int three, int two, int one) {
			this.five = five;
			this.four = four;
			this.three = three;
			this.two = two;
			this.one = one;
		}
	}

	public nonono() {
		JFrame frame = new JFrame("Bulletin");

		frame.setPreferredSize(new Dimension(1000, 800));
		Container contentPane = frame.getContentPane();
		DrawingPanel drawingPanel = new DrawingPanel();
		drawingPanel.setPreferredSize(new Dimension(300, 150));
//		contentPane.add(drawingPanel);
		JPanel pnl = new JPanel();
		JLabel star = new JLabel("별점");
//		star.setPreferredSize(new Dimension(50, 10));
	//	star.setBounds(x, y, width, height);
		pnl.add(star);
		pnl.add(drawingPanel);
		pnl.setPreferredSize(new Dimension(800, 800));
		contentPane.add(pnl);
		JPanel mainPnl = new JPanel();
		JTextArea text = new JTextArea();
		text.setPreferredSize(new Dimension(800, 400));
		frame.add(mainPnl, BorderLayout.SOUTH);
		mainPnl.add(text);
		
		pnl.setBackground(Color.black);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setSize(1000, 800);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new nonono();
	}
}
