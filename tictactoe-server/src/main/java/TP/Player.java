package TP;

public class Player {
	private String name;
	private int wins;
	private int losses;
	private char icon;
	
	Player(String name, char icon){
		this.name = name;
		wins = 0;
		losses = 0;
		this.icon = icon;
	}
	
	public void incWins() {
		wins++;
	}
	public void incLosses() {
		losses++;
	}
	
	public int getWins() {
		return wins;
	}
	public int getLosses() {
		return losses;
	}
	public String getName() {
		return name;
	}
	public char getIcon() {
		return icon;
	}
}
