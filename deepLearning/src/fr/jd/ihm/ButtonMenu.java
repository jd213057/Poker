package fr.jd.ihm;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonMenu extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font fontMenu = new Font("Poker Kings", Font.BOLD, 20);
//	KeyListener keyListener;
	
	ButtonMenu(String title, ImageIcon imageIcon, String keyValue) {
		super(title, imageIcon);
		this.setHorizontalTextPosition(SwingConstants.RIGHT);
		switch(keyValue) {
		case "A":
			this.setMnemonic(KeyEvent.VK_A);
			break;
		case "C":
			this.setMnemonic(KeyEvent.VK_C);
			break;
		case "D":
			this.setMnemonic(KeyEvent.VK_D);
			break;
		case "F":
			this.setMnemonic(KeyEvent.VK_F);
			break;
		case "L":
			this.setMnemonic(KeyEvent.VK_L);
			break;
		case "P":
			this.setMnemonic(KeyEvent.VK_P);
			break;
		case "Q":
			this.setMnemonic(KeyEvent.VK_Q);
			break;
		case "R":
			this.setMnemonic(KeyEvent.VK_R);
			break;
		case "S":
			this.setMnemonic(KeyEvent.VK_S);
			break;
		case "ENTREE":
			this.setMnemonic(KeyEvent.VK_ENTER);
			break;
		case "ESPACE":
			this.setMnemonic(KeyEvent.VK_SPACE);
			break;
		
		}
		this.setFont(new FontGame(fontMenu));
		this.setPreferredSize(new Dimension(190, 60));
//		this.addKeyListener(keyListener);
	}
	
	
}
