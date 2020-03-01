package fr.jd.main;


import java.awt.GraphicsEnvironment;
import java.io.IOException;

import fr.jd.ihm.GameWindow;

/**
 * @author Jonathan
 * Classe Main qui assure le fonctionnement de l'application
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
       GameWindow gameWindowMenu = new GameWindow();

		Controller controller = new Controller(); 

		controller.initialize();
//		if (controller.getGame().isDebugMode())
//			controller.configure();
		while (controller.getGame().getPlayersInGame().size() > 1 && controller.getGame().isOnPlay()) {
			controller.startRound();
			controller.round();
			controller.endRound();
			controller.getGame().isOnPlay();
		}
		controller.endGame();

	}

}