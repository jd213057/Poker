package fr.jd.main;

import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		Controller controller = new Controller();

		controller.initialize();
//		if (controller.getGame().isDebugMode())
//			controller.configure();
		while (controller.getGame().isOnPlay()) {
			controller.round();
			controller.endRound();
		}

	}

}
