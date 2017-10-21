package scdamagecalc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import scdamagecalc.units.Unit;

/**
 * Main class that runs the calculator and prints out the results.
 * 
 * @author h3r1n6
 * 
 */
public class SCDMGCalc {
	private void run(OutPut out) {
		Calculator c = new Calculator();
		LinkedList<LinkedList<Result>> results = new LinkedList<LinkedList<Result>>();
		for (List<Unit> attackers : UnitData.Units) {
			for (List<Unit> defenders : UnitData.Units) {
				LinkedList<Result> temp = new LinkedList<Result>();
				results.add(temp);
				for (Unit attacker : attackers) {
					for (Unit defender : defenders) {
						Result r = c.calculateBattle(attacker, defender);
						if (r != null)
							temp.add(r);
					}
				}
			}
		}

		try {
			for(LinkedList<Result> l : results){
				String file = "scdmgcalc" +l.get(0).getRaceAttacker().toString() + "vs" +l.get(0).getRaceDefender().toString() +".txt";				
				BufferedWriter b = new BufferedWriter(new FileWriter(file));
				for(Result r : l)
					b.write(out.resultToString(r));

				b.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new SCDMGCalc().run(new WikiOutput());
	}
}
