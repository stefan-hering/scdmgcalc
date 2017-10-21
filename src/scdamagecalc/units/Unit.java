package scdamagecalc.units;

import scdamagecalc.ArmorType;
import scdamagecalc.AttackType;
import scdamagecalc.DamageType;

/**
 * Representation of a Starcraft unit for this calculation
 * @author h3r1n6
 *
 */
public class Unit {
	private String name;
	private boolean flying;
	private float hp;
	private ArmorType armor;
	private DamageType damage;
	private AttackType attack;
	private int attacks;
	private int baseDamage;
	private int upgradeDamage;
	private int cooldown;
	private int baseArmor;
	private int armorupgrades;
	private int weaponupgrades;
	
	public Unit(String name, boolean flying, float hp, ArmorType armor, DamageType damage, AttackType attack, int attacks, int baseDamage, int upgradeDamage, int cooldown,
			int baseArmor) {
		this.name = name;
		this.flying = flying;
		this.hp = hp;
		this.damage = damage;
		this.armor = armor;
		this.attack = attack;
		this.attacks = attacks;
		this.baseDamage = baseDamage;
		this.upgradeDamage = upgradeDamage;
		this.cooldown = cooldown;
		this.baseArmor = baseArmor;
		this.armorupgrades = 0;
		this.weaponupgrades = 0;
	}
	
	public AttackType getAttack() {
		return attack;
	}

	public void setAttack(AttackType attack) {
		this.attack = attack;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}

	public ArmorType getArmor() {
		return armor;
	}

	public void setArmor(ArmorType armor) {
		this.armor = armor;
	}

	public DamageType getDamage() {
		return damage;
	}

	public void setDamage(DamageType damage) {
		this.damage = damage;
	}

	public int getAttacks() {
		return attacks;
	}

	public void setAttacks(int attacks) {
		this.attacks = attacks;
	}

	public int getArmorupgrades() {
		return armorupgrades;
	}

	public void setArmorupgrades(int armorupgrades) {
		this.armorupgrades = armorupgrades;
	}

	public int getWeaponupgrades() {
		return weaponupgrades;
	}

	public void setWeaponupgrades(int weaponupgrades) {
		this.weaponupgrades = weaponupgrades;
	}

	public float getHp() {
		return hp;
	}
	public void setHp(float hp) {
		this.hp = hp;
	}
	public int getBaseDamage() {
		return baseDamage;
	}
	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	public int getUpgradeDamage() {
		return upgradeDamage;
	}
	public void setUpgradeDamage(int upgradeDamage) {
		this.upgradeDamage = upgradeDamage;
	}
	public float getCooldown() {
		return cooldown;
	}
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	public int getBaseArmor() {
		return baseArmor;
	}
	public void setBaseArmor(int baseArmor) {
		this.baseArmor = baseArmor;
	}
	
	
}
