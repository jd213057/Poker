package fr.jd.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class GameWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameWindow() throws IOException {
		ImagePanel imagePanel = new ImagePanel();
		this.setTitle("Poker Game v2");
		this.setSize(1200, 801);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel contentPane = (JPanel) this.getContentPane();
		Font fontMenu = new Font("Poker Kings", Font.BOLD, 20);

		ButtonMenu boutonCommencer = new ButtonMenu("Commencer", new ImageIcon(
				PathConstants.startParty), "ENTREE");
		imagePanel.add(boutonCommencer);
		contentPane.add(imagePanel);

		ButtonMenu boutonCharger = new ButtonMenu("Charger", new ImageIcon(
				PathConstants.loadParty), "L");
		imagePanel.add(boutonCharger);
		contentPane.add(imagePanel);

		
		ButtonMenu boutonParametres = new ButtonMenu("Paramètres", new ImageIcon(
				PathConstants.paramParty), "P");
		imagePanel.add(boutonParametres);
		contentPane.add(imagePanel);

		
		ButtonMenu boutonDebugMode = new ButtonMenu("DebugMode", new ImageIcon(
				PathConstants.debugModeParty), "D");
		imagePanel.add(boutonDebugMode);
		contentPane.add(imagePanel);

		
		ButtonMenu boutonQuitter = new ButtonMenu("Quitter", new ImageIcon(
				PathConstants.exitParty), "Q");
		boutonQuitter.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
			
		imagePanel.add(boutonQuitter);
		contentPane.add(imagePanel);

		boutonQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int exitButtonClicked = JOptionPane.showConfirmDialog(GameWindow.this,
						"Etes-vous sur de vouloir quitter l'application ?", "Menu Fermer", JOptionPane.YES_NO_OPTION);
				if (exitButtonClicked == JOptionPane.YES_OPTION) {
					System.out.println("Fermeture de l'application");
					System.exit(EXIT_ON_CLOSE);
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exitButtonClicked = JOptionPane.showConfirmDialog(GameWindow.this,
						"Etes-vous sur de vouloir quitter l'application ?", "Menu Fermer", JOptionPane.YES_NO_OPTION);
				if (exitButtonClicked == JOptionPane.YES_OPTION) {
					System.out.println("Fermeture de l'application");
					System.exit(EXIT_ON_CLOSE);
				}
			}
		});
		this.setVisible(true);
	}

}
