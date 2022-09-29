package hyo;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;

public class Talk extends JFrame {
	public Talk() {
		
		setSize(800,600);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(119, 136, 153));
		panel.setBounds(0, 0, 784, 561);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 29, 216, 350);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel storeChar = new JLabel("상점캐 이미지");
		storeChar.setBounds(0, 0, 216, 350);
		panel_2.add(storeChar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 389, 760, 162);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel youSay = new JLabel("상점캐가 말함");
		youSay.setBounds(0, 0, 760, 162);
		panel_3.add(youSay);
		
		JButton myChoice1 = new JButton("선택지1");
		myChoice1.setBounds(240, 283, 518, 43);
		panel.add(myChoice1);
		
		JButton myChoice2 = new JButton("선택지2");
		myChoice2.setBounds(240, 336, 518, 43);
		panel.add(myChoice2);

	}
	
	public static void main(String[] args) {
		new Talk().setVisible(true);
	}
}
