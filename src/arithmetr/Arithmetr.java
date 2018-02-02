package arithmetr;

import javax.swing.*;

public class Arithmetr {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Arithmetr");
		APanel p = new APanel();
		frame.add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		p.setFocusable(true);
		p.validate();
		frame.setVisible(true);
		while(true){
			try { Thread.sleep(1000/60); } catch(Exception e){}
			p.repaint();
			frame.pack();
		}
	}
}
