package scdamagecalc.units;

import scdamagecalc.ArmorType;
import scdamagecalc.AttackType;
import scdamagecalc.DamageType;

public class TerranBuilding extends Unit implements Building{

	public TerranBuilding(String name, boolean flying, float hp,
			ArmorType armor, DamageType damage, AttackType attack, int attacks,
			int baseDamage, int upgradeDamage, int cooldown, int baseArmor) {
		super(name, flying, hp, armor, damage, attack, attacks, baseDamage,
				upgradeDamage, cooldown, baseArmor);
	}

}
