package fr.jd.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		JPanel contentPane = (JPanel) this.getContentPane();

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		JButton boutonCommencer = new JButton("Commencer");
		boutonCommencer.setSize(220, 40);
		boutonCommencer.setAlignmentX(CENTER_ALIGNMENT);
		JButton boutonCharger = new JButton("Charger");
		boutonCharger.setSize(120, 40);
		boutonCharger.setAlignmentX(CENTER_ALIGNMENT);
		JButton boutonParametres = new JButton("Paramètres");
		boutonParametres.setSize(120, 40);
		boutonParametres.setAlignmentX(CENTER_ALIGNMENT);
		JButton boutonDebugMode = new JButton("DebugMode");
		boutonDebugMode.setSize(120, 40);
		boutonDebugMode.setAlignmentX(CENTER_ALIGNMENT);
		JButton boutonQuitter = new JButton("Quitter");
		boutonQuitter.setSize(120, 40);
		boutonQuitter.setAlignmentX(CENTER_ALIGNMENT);
		boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Fermeture de l'application");
				System.exit(EXIT_ON_CLOSE);
			}
		});
		contentPane.add(imagePanel);
		imagePanel.add(boutonCommencer);
		imagePanel.add(boutonCharger);
		imagePanel.add(boutonParametres);
		imagePanel.add(boutonDebugMode);
		imagePanel.add(boutonQuitter);
	}

}
