package scdamagecalc;

public class WikiOutput implements OutPut {
	private static String tableheaderp = "{| cellspacing=\"4\" border=\"0\" align=\"center\" style=\"text-align:center\" class=\"wikitable greentable\"\n "
			+ "!|Attack up\\Armor up\n"
			+ "!|0\n"
			+ "!|1\n"
			+ "!|2\n"
			+ "!|3\n"
			+ "|- \n";
	private static String tableheadert = "{| cellspacing=\"4\" border=\"0\" align=\"center\" style=\"text-align:center\" class=\"wikitable bluetable\"\n "
			+ "!|Attack up\\Armor up\n"
			+ "!|0\n"
			+ "!|1\n"
			+ "!|2\n"
			+ "!|3\n"
			+ "|- \n";
	private static String tableheaderz = "{| cellspacing=\"4\" border=\"0\" align=\"center\" style=\"text-align:center\" class=\"wikitable redtable\"\n "
			+ "!|Attack up\\Armor up\n"
			+ "!|0\n"
			+ "!|1\n"
			+ "!|2\n"
			+ "!|3\n"
			+ "|- \n";
	
	private static String tableheader;
	private static String tableend = "|} \n\n";

	@Override
	public String resultToString(Result r) {
		String ret = r.getTitle() + "\n";
		
		if(r.getRaceAttacker() == Race.PROTOSS)
			tableheader = tableheaderp;
		if(r.getRaceAttacker() == Race.ZERG)
			tableheader = tableheaderz;
		if(r.getRaceAttacker() == Race.TERRAN)
			tableheader = tableheadert;

		if (r instanceof ProtossResult) {
			ProtossResult pr = (ProtossResult) r;
			ret += "'''Damage to armor:''' \n\n";
			ret += drawTable(pr.damageDealt);
			ret += "'''Damage to shield:''' \n\n";
			ret += drawTable(pr.damageToShield);
			for (int shield = 0; shield <= 3; shield++) {
				ret += "'''Shield =''' "+shield+" \n\n";
				ret += "'''Time to kill (frames):''' \n\n";
				ret += drawTossTable(pr.timeToKill, shield);
				ret += "'''Hits to kill:''' \n\n";
				ret += drawTossTable(pr.hitsToKill, shield);
			}

		} else {
			ret += "'''Damage dealt:''' \n\n";
			ret += drawTable(r.damageDealt);
			ret += "'''Time to kill (frames):''' \n\n";
			ret += drawTable(r.timeToKill);
			ret += "'''Hits to kill:''' \n\n";
			ret += drawTable(r.hitsToKill);
		}

		return ret;
	}

	private String drawTable(int[][] array) {
		String ret = tableheader;

		for (int attack = 0; attack <= 3; attack++) {
			ret += "!|" + attack + "\n";
			for (int defend = 0; defend <= 3; defend++) {
				ret += "|" + array[attack][defend] + "\n";
			}
			ret += "|- \n";
		}
		ret += tableend;
		return ret;
	}

	private String drawTable(float[][] array) {
		String ret = tableheader;

		for (int attack = 0; attack <= 3; attack++) {
			ret += "!|" + attack + "\n";
			for (int defend = 0; defend <= 3; defend++) {
				ret += "|" + array[attack][defend] + "\n";
			}
			ret += "|- \n";
		}
		ret += tableend;
		return ret;
	}
	private String drawTossTable(int[][][] array, int shield) {
		String ret = tableheader;

		for (int attack = 0; attack <= 3; attack++) {
			ret += "!|" + attack + "\n";
			for (int defend = 0; defend <= 3; defend++) {
				ret += "|" + array[attack][defend][shield] + "\n";
			}
			ret += "|- \n";
		}
		ret += tableend;
		return ret;
	}
}
