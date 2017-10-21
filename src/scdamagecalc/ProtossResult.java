package scdamagecalc;

public class ProtossResult extends Result{
	public float[][] damageToShield;
	//[attack up, armor up, shield up]
	public int[][][] hitsToKill;
	public int[][][] timeToKill;

	public ProtossResult(String title,Race attacker, Race defender) {
		super(title,attacker, defender);
		damageToShield = new float[4][4];
		hitsToKill = new int[4][4][4];
		timeToKill = new int[4][4][4];
	}

}
