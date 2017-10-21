package scdamagecalc;

/**
 * Represents the information gained by the calculation
 * @author h3r1n6
 *
 */
public class Result {
	private String title;
	private Race attacker;
	private Race defender;
	
	public Race getRaceAttacker() {
		return attacker;
	}



	public void setRaceAttacker(Race raceAttacker) {
		this.attacker = raceAttacker;
	}



	public Race getRaceDefender() {
		return defender;
	}



	public void setRaceDefender(Race raceDefender) {
		this.defender = raceDefender;
	}


	//public because of equal opportunity laws for public and private variables
	public int[] upgradedDamage;
	//[attack upgrade, armor upgrade]
	public float[][] damageDealt;
	public int[][] hitsToKill;
	public int[][] timeToKill;
	

	
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public Result(String title, Race attacker, Race defender){
		this.attacker = attacker;
		this.defender = defender;
		this.title = title;
		upgradedDamage = new int[4];
		damageDealt = new float[4][4];
		hitsToKill = new int[4][4];
		timeToKill = new int[4][4];
	}
}
