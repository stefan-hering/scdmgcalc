package scdamagecalc.units;

import scdamagecalc.ArmorType;
import scdamagecalc.AttackType;
import scdamagecalc.DamageType;

public class ProtossUnit extends Unit{
	private float shield;

	public float getShield() {
		return shield;
	}

	public void setShield(float shield) {
		this.shield = shield;
	}

	public ProtossUnit(String name, boolean flying, float hp, float shield, ArmorType armor, DamageType damage, AttackType attack, int attacks, int baseDamage, int upgradeDamage,
			int cooldown, int baseArmor) {
		super(name, flying, hp, armor, damage, attack, attacks, baseDamage, upgradeDamage, cooldown, baseArmor);
		this.shield = shield;
	}
}
