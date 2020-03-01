package fr.jd.ihm;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class FontGame extends Font {
	Font fontGame = new Font("Poker Kings", Font.BOLD, 20);
	String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	protected FontGame(Font fontGame) {
		super(fontGame);
	}

	public void displayAllFonts() {
		for (String font : fonts)
			System.out.println(font);
	}

}
