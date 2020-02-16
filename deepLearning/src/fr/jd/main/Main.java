package fr.jd.main;

import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		Controller controller = new Controller();

		controller.initialize();
		if (controller.getGame().isDebugMode()) controller.configure();
		while (controller.getGame().isOnPlay()) {
			controller.round();
			controller.endRound();
			System.out.println("Continuer la prochaine manche? y/n");
			Scanner keyboard = new Scanner(System.in);
			String answer = keyboard.toString();
			if (answer == "n")
				controller.getGame().setOnPlay(false);
			keyboard.close();
		}

	}

}
