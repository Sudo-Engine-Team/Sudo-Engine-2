package site.root3287.sudo2.utils;

public class Elo {
	public static float elo(int otherElo, int wins, int loose){
		int totalMatches = wins+loose;
		return (otherElo+400*(wins-loose))/totalMatches;
	}
	
	public static void main(String[] args) {
		System.out.println(elo(316, 84, 10));
	}
}
