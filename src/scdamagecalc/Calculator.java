package scdamagecalc;

import java.util.HashMap;

import scdamagecalc.units.Building;
import scdamagecalc.units.ProtossUnit;
import scdamagecalc.units.TerranBuilding;
import scdamagecalc.units.Unit;
import scdamagecalc.units.ZergUnit;

/**
 * Class for calculating two units against each user, putting out a {@link Result}. Disregards splash damage as of now.
 * @author h3r1n6
 *
 */
public class Calculator {
	
	private HashMap<DamageType, HashMap<ArmorType, Float>> damageReduction;
	
	private float damageDealt(float damage, DamageType damageType, ArmorType armor){
		float damageDealt = damage * damageReduction.get(damageType).get(armor);
		if(damageDealt < 0.5f)
			damageDealt = 0.5f;
		return damageDealt;
	}
	
	public Race getRace(Unit unit){
		if(unit instanceof ProtossUnit)
			return Race.PROTOSS;
		if(unit instanceof ZergUnit)
			return Race.ZERG;
		return Race.TERRAN;
	}
	
	
	public Result calculateBattle(Unit attacker, Unit defender){
		if(attacker.getAttack() == AttackType.NONE || 
				(attacker.getAttack() == AttackType.GROUND && !(defender instanceof TerranBuilding) && defender.isFlying() == true) ||
				(attacker.getAttack() == AttackType.AIR && defender.isFlying() == false) ||
				defender.getHp() == 0f)
			return null;
		
		Result r;
		boolean toss = defender instanceof ProtossUnit;
		boolean building = (defender instanceof Building);
		
		
		if(toss){
			r = new ProtossResult(attacker.getName()+" attacking " + defender.getName(), getRace(attacker), getRace(defender));
		}
		else{	
			r = new Result(attacker.getName()+" attacking " + defender.getName(), getRace(attacker), getRace(defender));
		}

		
		for(int attackUp = 0; attackUp <= 3; attackUp++){
			r.upgradedDamage[attackUp] = ( attacker.getBaseDamage() + attacker.getUpgradeDamage() * attackUp) * attacker.getAttacks();
			for(int armorUp = 0; armorUp <= 3; armorUp++){
				r.damageDealt[attackUp][armorUp] = damageDealt(r.upgradedDamage[attackUp]- (defender.getBaseArmor() +(building ? 0 : armorUp))*attacker.getAttacks(), attacker.getDamage(), defender.getArmor());
				if(toss){
					for(int shieldUp = 0; shieldUp <= 3; shieldUp++){
						((ProtossResult)r).damageToShield[attackUp][shieldUp] = r.upgradedDamage[attackUp] - shieldUp*attacker.getAttacks();
						float hp = defender.getHp();
						float shield = ((ProtossUnit)defender).getShield();
						int deathFrame = 0;
						int hits = 0;
						while(true){
							deathFrame++;
							if(deathFrame > 100000){
								deathFrame = 0;
								hits = 0;
								break;
							}

							if (deathFrame % attacker.getCooldown() == 1) {
								if (shield > 0) {
									shield -= ((ProtossResult) r).damageToShield[attackUp][shieldUp];
									hits++;
									if (shield < 0) {
										if(attacker.getAttacks() > 1){
											hp -= damageDealt(-shield- defender.getBaseArmor() -(building ? 0 : armorUp),attacker.getDamage(), defender.getArmor());
										}
										else{
											hp -= damageDealt(-shield- defender.getBaseArmor() -(building ? 0 : armorUp),attacker.getDamage(), defender.getArmor());
										}
										shield = 0f;
									}
								} else {
									hp -= r.damageDealt[attackUp][armorUp];
									hits++;
								}
							}
							
							if(deathFrame % UnitData.shieldRegen == 0){
								shield += 1;
							}
							if(hp <= 0)
								break;
						}
						((ProtossResult)r).timeToKill[attackUp][armorUp][shieldUp] = deathFrame;
						((ProtossResult)r).hitsToKill[attackUp][armorUp][shieldUp] = hits;
					}
				} else{
					float hp = defender.getHp();
					int deathFrame = 0;
					int hits = 0;
					while(true){
						deathFrame++;
						if(deathFrame > 100000){
							deathFrame = 0;
							hits = 0;
							break;
						}

						if(deathFrame % attacker.getCooldown() == 1){
							hp -= r.damageDealt[attackUp][armorUp];
							hits++;
						}
						
						if((defender instanceof TerranBuilding) && (defender.getHp() - hp) / defender.getHp() < 1f/3f && deathFrame % UnitData.burning == 0){
							hp -= 1;
						}
						if(hp <= 0)
							break;
						if(defender instanceof ZergUnit && deathFrame % UnitData.zergRegen == 1){
							hp += 1;
						}
					}
					r.timeToKill[attackUp][armorUp] = deathFrame;
					r.hitsToKill[attackUp][armorUp] = hits;
				}
			}
		}
		return r;
	}
	
	public Calculator(){
		damageReduction = new HashMap<DamageType, HashMap<ArmorType, Float>>();
		HashMap<ArmorType, Float> normal = new HashMap<ArmorType, Float>(), explosive = new HashMap<ArmorType, Float>(), concussive = new HashMap<ArmorType, Float>();
		normal.put(ArmorType.BIG, 1f);
		normal.put(ArmorType.MEDIUM, 1f);
		normal.put(ArmorType.SMALL, 1f);
		explosive.put(ArmorType.BIG, 1f);
		explosive.put(ArmorType.MEDIUM, 0.75f);
		explosive.put(ArmorType.SMALL, 0.5f);
		concussive.put(ArmorType.BIG, 0.25f);
		concussive.put(ArmorType.MEDIUM, 0.5f);
		concussive.put(ArmorType.SMALL, 1f);
		damageReduction.put(DamageType.NORMAL, normal);
		damageReduction.put(DamageType.CONCUSSIVE, concussive);
		damageReduction.put(DamageType.EXPLOSIVE, explosive);
	}
}
