package fr.jd.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Dealer {

	private List<Cards> cardsPackage = new ArrayList<Cards>(EnumSet.allOf(Cards.class));
	private List<Cards> carpet = new ArrayList<Cards>();

	public Dealer(List<Cards> cardsPackage, List<Cards> carpet) {
		super();
		this.cardsPackage = cardsPackage;
		this.carpet = carpet;
	}

	public List<Cards> getCardsPackage() {
		return cardsPackage;
	}

	public void setCardsPackage(List<Cards> cardsPackage) {
		this.cardsPackage = cardsPackage;
	}
	public List<Cards> getCarpet() {
		return carpet;
	}

	public void setCarpet(List<Cards> carpet) {
		this.carpet = carpet;
	}

	@Override
	public String toString() {
		return "Package [cardsPackage=" + cardsPackage + "]";
	}

	public void shuffle() {
		Collections.shuffle(cardsPackage);
	}

	private void checkShuffle() {
		cardsPackage.forEach(System.out::println);
	}

	public void distribute(Player player) {
		player.getHand().add(cardsPackage.get(cardsPackage.size() - 1));
		cardsPackage.remove(cardsPackage.get(cardsPackage.size() - 1));
	}

	public void putOnTable(int tour) {
		switch (tour) {
		case 0:
			break;
		case 1:
			cardsPackage.remove(cardsPackage.get(cardsPackage.size() - 1));
			for (int i = 0; i < 3; i++)
				carpet.add(cardsPackage.get(cardsPackage.size() - 1));
			break;
		case 2:
		case 3:
			cardsPackage.remove(cardsPackage.get(cardsPackage.size() - 1));
			carpet.add(cardsPackage.get(cardsPackage.size() - 1));
			break;
		}
	}

	public void recoverCards() {
		carpet.clear();
		cardsPackage.clear();
		cardsPackage.addAll(new ArrayList<Cards>(EnumSet.allOf(Cards.class)));
	}
}