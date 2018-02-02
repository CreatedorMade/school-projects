package algebr;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class APanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = -400139748276786027L;
	
	String mode = "title";
	
	int mouseX = 0;
	int mouseY = 0;
	
	double startTime = 0;
	double endTime = 0;
	
	int level = 0;
	int intensity = 1;
	boolean leveledUp = false;
	
	int lives = 0;
	boolean mistake = false;
	
	boolean waiting = false;
	boolean hasQuestion = false;
	int position = 0;
	int num1 = 0;
	int num2 = 0;
	int num3 = 0;
	
	int[] bgRects = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	
	String in = "";
	
	int score = 0;
	
	public Dimension getPreferredSize(){
		return new Dimension(400, 650);
	}
	
	public APanel(){
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(209, 255, 209));
		g.fillRect(0, 0, 400, 650);
		
		if(Math.random() < 0.01){
			boolean available = false;
			for(int i = 0; i < 4; i++){
				if(bgRects[i*3] == -1) available = true;
			}
			if(available){
				int side;
				while(true){
					side = (int) Math.round(Math.random()*3);
					if(bgRects[side*3] == -1) break;
				}
				
				bgRects[side*3] = 0;
				bgRects[side*3+1] = (int) Math.round(Math.random()*150 + 50);
				bgRects[side*3+2] = (int) Math.round(Math.random()*350 + 50);
			}
		}
		
		g.setColor(new Color(192, 255, 192, 128));
		if(bgRects[0] != -1){
			g.fillRect(0, bgRects[0]-bgRects[2], bgRects[1], bgRects[2]);
			bgRects[0]++;
			
			if(bgRects[0] > 650+bgRects[2]) bgRects[0] = -1;
		}
		
		if(bgRects[3] != -1){
			g.fillRect(400-bgRects[4], 650-bgRects[3], bgRects[4], bgRects[5]);
			bgRects[3]++;
			
			if(bgRects[3] > 650+bgRects[5]) bgRects[3] = -1;
		}
		
		if(bgRects[6] != -1){
			g.fillRect(bgRects[6]-bgRects[8], 650-bgRects[7], bgRects[8], bgRects[7]);
			bgRects[6]++;
			
			if(bgRects[6] > 400+bgRects[8]) bgRects[6] = -1;
		}
		
		if(bgRects[9] != -1){
			g.fillRect(400-bgRects[9], 0, bgRects[11], bgRects[10]);
			bgRects[9]++;
			
			if(bgRects[9] > 400+bgRects[11]) bgRects[9] = -1;
		}
		
		if(mode == "title"){
			
			g.setColor(new Color(0, 0, 0));
			
			g.setFont(new Font("SansSerif", Font.PLAIN, 30));
			drawCenteredString(g, 200, 100, "Algebr");

			g.setFont(new Font("SansSerif", Font.PLAIN, 15));
			drawCenteredString(g, 200, 150, "ANOTHER GAME OF MATHEMATICAL EFFICIENCY");
			
			g.drawRect(100, 500, 200, 50);
			
			if(mouseX >= 100 && mouseX <= 300 && mouseY >= 500 && mouseY <= 550) g.setColor(new Color(192, 255, 192));
			else g.setColor(new Color(128, 255, 128));
			g.fillRect(101, 501, 199, 49);
			
			g.setColor(new Color(0,0,0));
			
			g.setFont(new Font("SansSerif", Font.PLAIN, 40));
			drawCenteredString(g, 200, 525, "START");
		} else if(mode == "game"){
			g.setColor(new Color(128, 255, 255));
			g.fillRect(0, 0, 400, 50);
			
			if(!hasQuestion) {
				position = (int) Math.round(Math.random()*2);
				
				num1 = (int) Math.round(Math.random()*(level+1))+1;
				num2 = (int) Math.round(Math.random()*(level+1))+1;
				num3 = num1*num2;
				
				waiting = true;
				endTime = (float) (System.nanoTime())/1000000000 + 2.0/((float) (level)/5+1);
				hasQuestion = true;
			}
			
			if(waiting){
				g.setColor(new Color(0, 255, 0));
				g.fillRect(0, 0, (int) ((endTime - (float) (System.nanoTime())/1000000000.0)/(2.0/((float) (level)/5+1))*400), 50);
				g.setColor(new Color(0, 0, 0));
				g.setFont(new Font("SansSerif", Font.PLAIN, 70));
				if(leveledUp){
					drawCenteredString(g, 200, 325, "NICE!");
					g.setFont(new Font("SansSerif", Font.PLAIN, 25));
					drawCenteredString(g, 200, 375, "SPEED REDUCED");
					drawCenteredString(g, 200, 400, "+1 LIFE");
				}
				else if(mistake){
					drawCenteredString(g, 200, 325, "WRONG.");
					g.setFont(new Font("SansSerif", Font.PLAIN, 25));
					drawCenteredString(g, 200, 375, "ALL HEAT GONE");
					drawCenteredString(g, 200, 400, "-1 LIFE");
				} else drawCenteredString(g, 200, 325, "NEXT!");
				if(endTime - (float) (System.nanoTime())/1000000000.0 < 0) {
					if(leveledUp){ intensity++; leveledUp = false; lives++; }
					startTime = (float) (System.nanoTime())/1000000000;
					endTime = startTime + 5/((float) (level)/5+1);
					mistake = false;
					waiting = false;
				}
				g.setColor(new Color(0, 0, 0));
				String lifeString = "";
				for(int i = 0; i < lives; i++){
					lifeString += "O";
				}
				g.setFont(new Font("SansSerif", Font.PLAIN, 30));
				drawCenteredString(g, 200, 600, lifeString);
				g.setColor(new Color(0,0,0));
				g.fillRect(0, 625, 400, 25);
				g.setColor(new Color(128,255,128));
				g.fillRect(0, 625, Math.round(((float) (level)/(float) (intensity*3))*400), 25);
				if(leveledUp) g.fillRect(0, 625, 400, 25);
			} else {
				double remaining = (endTime - (float) (System.nanoTime())/1000000000.0)/(endTime-startTime);
				
				g.setColor(greenToRed(remaining));
				g.fillRect(0, 0, (int) ((endTime - (float) (System.nanoTime())/1000000000.0)/(5/((float) (level)/5+1))*400), 50);
				
				g.setColor(new Color(0, 0, 0));
				g.setFont(new Font("SansSerif", Font.PLAIN, 70));
				
				drawCenteredString(g, 140, 325, "*");
				drawCenteredString(g, 260, 325, "=");
				
				g.setFont(new Font("SansSerif", Font.PLAIN, 50));
				if(position == 0){
					g.setColor(new Color(255, 192, 192));
					g.fillRect(50, 295, 60, 60);
					g.setColor(new Color(0, 0, 0));
					drawCenteredString(g, 200, 325, Integer.toString(num2));
					drawCenteredString(g, 320, 325, Integer.toString(num3));
					g.setFont(inSize(g));
					drawCenteredString(g, 80, 325, in);
				} else if(position == 1){
					g.setColor(new Color(255, 192, 192));
					g.fillRect(170, 295, 60, 60);
					g.setColor(new Color(0, 0, 0));
					drawCenteredString(g, 80, 325, Integer.toString(num1));
					drawCenteredString(g, 320, 325, Integer.toString(num3));
					g.setFont(inSize(g));
					drawCenteredString(g, 200, 325, in);
				} else if(position == 2){
					g.setColor(new Color(255, 192, 192));
					g.fillRect(300, 295, 60, 60);
					g.setColor(new Color(0, 0, 0));
					drawCenteredString(g, 80, 325, Integer.toString(num1));
					drawCenteredString(g, 200, 325, Integer.toString(num2));
					g.setFont(inSize(g));
					drawCenteredString(g, 330, 325, in);
				}
				
				g.setColor(new Color(0, 0, 0));
				String lifeString = "";
				for(int i = 0; i < lives; i++){
					lifeString += "O";
				}
				g.setFont(new Font("SansSerif", Font.PLAIN, 30));
				drawCenteredString(g, 200, 600, lifeString);
				
				g.setColor(new Color(0,0,0));
				g.fillRect(0, 625, 400, 25);
				g.setColor(new Color(128,255,128));
				g.fillRect(0, 625, Math.round(((float) (level)/(float) (intensity*3))*400), 25);
				
				if(endTime - (float) (System.nanoTime())/1000000000.0 < 0){
					in = "";
					lives--;
					level = 0;
					intensity++;
					if(lives < 0) mode = "loss";
					hasQuestion = false;
					mistake = true;
				}
			}
			
			g.setColor(new Color(0, 0, 0));
			g.drawLine(0, 50, 400, 50);
			
			
		} else if(mode == "ready"){
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("SansSerif", Font.PLAIN, 30));
			drawCenteredString(g, 200, 100, "GET READY");
			
			if(endTime - (float) (System.nanoTime())/1000000000 > 2){
				g.setFont(new Font("SansSerif", Font.PLAIN, 30));
				drawCenteredString(g, 200, 325, "3");
			} else if(endTime - (float) (System.nanoTime())/1000000000 > 1){
				g.setFont(new Font("SansSerif", Font.PLAIN, 50));
				drawCenteredString(g, 200, 325, "2");
			} else if(endTime - (float) (System.nanoTime())/1000000000 > 0){
				g.setFont(new Font("SansSerif", Font.PLAIN, 70));
				drawCenteredString(g, 200, 325, "1");
			} else {
				mode = "game";
			}
			
		} else if(mode == "loss"){
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("SansSerif", Font.PLAIN, 25));
			drawCenteredString(g, 200, 150, "You couldn't handle the numbers.");
			g.setFont(new Font("SansSerif", Font.PLAIN, 50));
			drawCenteredString(g, 200, 100, "oof.");
			g.setFont(new Font("SansSerif", Font.PLAIN, 40));
			drawCenteredString(g, 200, 300, "FINAL SCORE:");
			drawCenteredString(g, 200, 350, Integer.toString(score));
			
			g.setFont(new Font("SansSerif", Font.PLAIN, 25));
			if(score == 0){
				drawCenteredString(g, 200, 400, "Did...");
				drawCenteredString(g, 200, 425, "Did you fall asleep?");
			} else if(score < 20){
				drawCenteredString(g, 200, 400, "Eh. It's okay, I guess.");
			} else if(score <40){
				drawCenteredString(g, 200, 400, "Hey, you're doing it!");
			} else if(score <60){
				drawCenteredString(g, 200, 400, "Slow down!");
			} else if(score <80){
				drawCenteredString(g, 200, 400, "That's more than I got.");
				drawCenteredString(g, 200, 425, "*slow clap*");
			} else if(score <100){
				drawCenteredString(g, 200, 400, "...how?!");
			} else if(score >= 100){
				drawCenteredString(g, 200, 400, "Honestly, I didn't even");
				drawCenteredString(g, 200, 425, "think anyone would get");
				drawCenteredString(g, 200, 450, "that many points.");
				drawCenteredString(g, 200, 475, "*slow clap*");
			}
			
			if(mouseX >= 100 && mouseX <= 300 && mouseY >= 500 && mouseY <= 550) g.setColor(new Color(192, 255, 192));
			else g.setColor(new Color(128, 255, 128));
			g.fillRect(101, 501, 199, 49);
			
			g.setColor(new Color(0,0,0));
			g.drawRect(100, 500, 200, 50);
			
			g.setFont(new Font("SansSerif", Font.PLAIN, 40));
			drawCenteredString(g, 200, 525, "RESTART");
		}
	}
	
	//thanks, stackoverflow
	public void drawCenteredString(Graphics g, int x, int y, String text) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(g.getFont());
	    // Determine the X coordinate for the text
	    int xc = x + (-metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int yc = y + ((-metrics.getHeight()) / 2) + metrics.getAscent();
	    // Draw the String
	    g.drawString(text, xc, yc);
	}
	
	Color greenToRed(double n){
		int red = (int) Math.round(Math.min(255, Math.max(0, n*-512.0+512.0)));
		int grn = (int) Math.round(Math.min(255, Math.max(0, n*512.0)));
		return new Color(red, grn, 0);
	}
	
	Font inSize(Graphics g){
		int size = 50;
		while(true){
			FontMetrics metrics = g.getFontMetrics(new Font("SansSerif", Font.PLAIN, size));
			if(metrics.stringWidth(in) < 60) break;
			size--;
		}
		return new Font("SansSerif", Font.PLAIN, size);
	}
	
	public void mouseClicked(MouseEvent e) {
		if(mode == "title"){
			if(mouseX >= 100 && mouseX <= 300 && mouseY >= 500 && mouseY <= 550){
				mode = "ready";
				endTime = (float) (System.nanoTime())/1000000000 + 3;
			}
		} else if(mode == "loss"){
			if(mouseX >= 100 && mouseX <= 300 && mouseY >= 500 && mouseY <= 550){
				mode = "ready";
				
				lives = 0;
				level = 0;
				intensity = 1;
				score = 0;
				hasQuestion = false;
				mistake = false;
				leveledUp = false;
				
				endTime = (float) (System.nanoTime())/1000000000 + 3;
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		if(mode == "game" && !waiting){
			if(e.getKeyCode() >= 48 && e.getKeyCode() <= 57){
				in += e.getKeyCode()-48;
			} else if(e.getKeyCode() == 10){
				boolean correct = false;
				
				if(position == 0 && in.equals(Integer.toString(num1))) {
					correct = true;
				} else if(position == 1 && in.equals(Integer.toString(num2))) {
					correct = true;
				} else if(position == 2 && in.equals(Integer.toString(num3))) {
					correct = true;
				}
				
				if(correct){
					hasQuestion = false;
					level++;
					if(level == intensity*3) {level = 0; leveledUp = true;}
					score++;
				}
				in = "";
			} else if(e.getKeyCode() == 8){
				if(in.length() > 0) in = in.substring(0, in.length() - 1);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
}
