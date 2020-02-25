package fr.jd.main;

import java.util.Scanner;

import javax.swing.JFrame;

/**
 * @author Jonathan
 * Classe Main qui assure le fonctionnement de l'application
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

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