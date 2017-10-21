package scdamagecalc.units;

import scdamagecalc.ArmorType;
import scdamagecalc.AttackType;
import scdamagecalc.DamageType;

public class ProtossBuilding extends ProtossUnit implements Building{

	public ProtossBuilding(String name, boolean flying, float hp, float shield,
			ArmorType armor, DamageType damage, AttackType attack, int attacks,
			int baseDamage, int upgradeDamage, int cooldown, int baseArmor) {
		super(name, flying, hp, shield, armor, damage, attack, attacks, baseDamage,
				upgradeDamage, cooldown, baseArmor);
	}

}
