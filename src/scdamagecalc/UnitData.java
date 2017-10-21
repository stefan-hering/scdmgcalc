package scdamagecalc;

import java.util.List;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ComboBoxModel;

import scdamagecalc.units.ProtossBuilding;
import scdamagecalc.units.ProtossUnit;
import scdamagecalc.units.TerranBuilding;
import scdamagecalc.units.Unit;
import scdamagecalc.units.ZergBuilding;
import scdamagecalc.units.ZergUnit;

/**
 * The data feeding the calculations
 * @author h3r1n6
 *
 */
public class UnitData {
	//basic idea and formulas from: http://www.teamliquid.net/forum/viewmessage.php?topic_id=104594
	
	//name, flyer, hp, (shield), armor, damage, # of attacks, base damage, upgrade damagebonus, cooldown, base armor+
	//When a unit is set to 0hp, the battle won't be fought, likewise when a unit is cannot attack the other, the battle won't be fought either.
	public static final List<Unit> TerranUnits = Arrays.asList(new Unit[]{
			new Unit("SCV", false, 60f, ArmorType.SMALL, DamageType.NORMAL, AttackType.GROUND, 1, 5, 0, 15, 0),
			new Unit("Marine", false, 40f, ArmorType.SMALL, DamageType.NORMAL, AttackType.BOTH, 1, 6, 1, 15, 0),
			new Unit("Marine (stim)", false, 0f, ArmorType.SMALL, DamageType.NORMAL, AttackType.BOTH, 1, 6, 1, 8, 0), //for now rounded, is the value really 7.5? So they attack with a cd of 7 and 8 alternating? Or somehow inbetween the frames?
			new Unit("Firebat", false, 60f, ArmorType.SMALL, DamageType.CONCUSSIVE, AttackType.GROUND, 2, 8, 1, 22, 1),
			new Unit("Firebat (stim)", false, 0f, ArmorType.SMALL, DamageType.CONCUSSIVE, AttackType.GROUND, 2, 8, 1, 11, 1),
			new Unit("Ghost", false, 45f, ArmorType.SMALL, DamageType.CONCUSSIVE, AttackType.BOTH, 1, 10, 1, 22, 0),
			new Unit("Medic", false, 60f, ArmorType.SMALL, DamageType.NORMAL, AttackType.NONE, 1, 6, 1, 15, 1),
			new Unit("Vulture", false, 75, ArmorType.MEDIUM, DamageType.CONCUSSIVE, AttackType.GROUND, 1, 20, 2, 30, 0),
			new Unit("Spidermine", false, 20f, ArmorType.SMALL, DamageType.EXPLOSIVE, AttackType.GROUND, 1, 125, 0, 15, 0),
			new Unit("Unsieged Tank", false, 150f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.GROUND, 1, 30, 3, 37, 1),
			new Unit("Sieged Tank", false, 0, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.GROUND, 1, 70, 5, 75, 1),
			new Unit("Goliath", false, 125f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.NONE, 2, 10, 2, 22, 0),
			new Unit("Goliath (air)", false, 0f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.AIR, 2, 10, 2, 22, 0),
			new Unit("Goliath (ground)", false, 0f, ArmorType.BIG, DamageType.NORMAL, AttackType.GROUND, 1, 10, 1, 22, 0),
			new Unit("Wraith", true, 120f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.NONE, 1, 20, 2, 22, 0), 
			new Unit("Wraith (air)", true, 0f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.AIR, 1, 20, 2, 22, 0), 
			new Unit("Wraith (ground)", true, 0f, ArmorType.BIG, DamageType.NORMAL, AttackType.GROUND, 1, 8, 1, 30, 0),
			new Unit("Dropship", true, 150f, ArmorType.BIG, DamageType.NORMAL, AttackType.NONE, 1, 6, 1, 15, 1),
			new Unit("Valkyrie", true, 200f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.AIR, 8, 6, 1, 64, 2), //for now all attacks hit
			new Unit("Battle Cruiser", true, 500f, ArmorType.BIG, DamageType.NORMAL, AttackType.BOTH, 1, 25, 3, 30, 3),
			new Unit("Science Vessel", true, 200f, ArmorType.BIG, DamageType.NORMAL, AttackType.NONE, 1, 6, 1, 15, 1),
			new TerranBuilding("Turret", false, 200f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.AIR, 1, 20, 0, 15, 1),
			new TerranBuilding("Bunker", false, 350f, ArmorType.BIG, DamageType.NORMAL, AttackType.NONE, 1, 6, 1, 15, 1), 
	});
	public static final List<Unit> ProtossUnits = Arrays.asList(new Unit[]{
			new ProtossUnit("Probe", false, 20f, 20f, ArmorType.SMALL, DamageType.NORMAL, AttackType.GROUND, 1, 5, 0, 22, 0),
			new ProtossUnit("Zealot", false, 100f, 60f, ArmorType.SMALL, DamageType.NORMAL, AttackType.GROUND, 2, 8, 1, 22, 0),
			new ProtossUnit("Dragoon", false, 100f, 80f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.BOTH, 1, 20, 2, 30, 1),
			new ProtossUnit("High Templar", false, 40f, 40f, ArmorType.SMALL, DamageType.NORMAL, AttackType.NONE, 2, 8, 1, 22, 0),
			new ProtossUnit("Dark Templar", false, 80f, 40f, ArmorType.SMALL, DamageType.NORMAL, AttackType.GROUND, 1, 40, 3, 30, 1),
			new ProtossUnit("High Archon", false, 10f, 350f, ArmorType.BIG, DamageType.NORMAL, AttackType.BOTH, 1, 30, 3, 20, 0),
			new ProtossUnit("Dark Archon", false, 25f, 200f, ArmorType.BIG, DamageType.NORMAL, AttackType.NONE, 2, 8, 1, 22, 1),
			new ProtossUnit("Reaver", false, 100f, 80f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.GROUND, 1, 100, 0, 60, 0),
			new ProtossUnit("Reaver (Upgraded)", false, 0f, 0f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.GROUND, 1, 125, 0, 60, 0),
			new ProtossUnit("Shuttle", true, 80f, 60f, ArmorType.BIG, DamageType.NORMAL, AttackType.NONE, 2, 8, 1, 22, 0),
			new ProtossUnit("Observer", true, 40f, 20f, ArmorType.SMALL, DamageType.NORMAL, AttackType.NONE, 2, 8, 1, 22, 0),
			new ProtossUnit("Corsair", true, 100f, 60f, ArmorType.MEDIUM, DamageType.EXPLOSIVE, AttackType.AIR, 1, 5, 1, 8, 0),
			new ProtossUnit("Scout", true, 150f, 100f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.NONE, 2, 14, 1, 22, 0),
			new ProtossUnit("Scout (Ground)", true, 0f, 100f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.AIR, 2, 14, 1, 22, 0),
			new ProtossUnit("Scout (Air)", true, 0f, 0f, ArmorType.BIG, DamageType.NORMAL, AttackType.GROUND, 1, 8, 1, 30, 0),
			new ProtossUnit("Carrier (full)", true, 300f, 150f, ArmorType.BIG, DamageType.NORMAL, AttackType.BOTH, 8, 6, 1, 37, 4),
			new ProtossUnit("Interceptor", true, 40f, 40f, ArmorType.SMALL, DamageType.NORMAL, AttackType.BOTH, 1, 6, 1, 37, 0),
			new ProtossUnit("Arbiter", true, 250f, 150f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.BOTH, 1, 10, 1, 45, 1),
			new ProtossBuilding("Cannon", false, 100f, 100f, ArmorType.BIG, DamageType.NORMAL, AttackType.BOTH, 1, 20, 0, 22, 0) 
	});
	public static final List<Unit> ZergUnits = Arrays.asList(new Unit[]{
			new ZergUnit("Drone", false, 40f, ArmorType.SMALL, DamageType.NORMAL, AttackType.GROUND, 1, 5, 1, 22, 0),
			new ZergUnit("Zergling", false, 35f, ArmorType.SMALL, DamageType.NORMAL, AttackType.GROUND, 1, 5, 1, 8, 0),
			new ZergUnit("Zergling (crack)", false, 0f, ArmorType.SMALL, DamageType.NORMAL, AttackType.GROUND, 1, 5, 1, 6, 0),
			new ZergUnit("Hydralisk", false, 80f, ArmorType.MEDIUM, DamageType.EXPLOSIVE, AttackType.BOTH, 1, 10, 1, 15, 0),
			new ZergUnit("Overlord", true, 200f, ArmorType.BIG, DamageType.NORMAL, AttackType.NONE, 1, 5, 1, 8, 0),
			new ZergUnit("Mutalisk", true, 120f, ArmorType.SMALL, DamageType.NORMAL, AttackType.GROUND, 1, 9, 1, 30, 0),
			new ZergUnit("Scourge", true, 25f, ArmorType.SMALL, DamageType.NORMAL, AttackType.AIR, 1, 120, 0, 20, 0),
			new ZergUnit("Lurker", false, 125f, ArmorType.MEDIUM, DamageType.NORMAL, AttackType.GROUND, 1, 20, 2, 37, 1),
			new ZergUnit("Queen", true, 120f, ArmorType.MEDIUM, DamageType.NORMAL, AttackType.NONE, 1, 5, 1, 8, 0),
			new ZergUnit("Ultralisk", false, 400f, ArmorType.BIG, DamageType.NORMAL, AttackType.GROUND, 1, 20, 3, 15, 1),
			new ZergUnit("Ultralisk (armor)", false, 400f, ArmorType.BIG, DamageType.NORMAL, AttackType.NONE, 1, 5, 1, 8, 3),
			new ZergUnit("Defiler", false, 80f, ArmorType.MEDIUM, DamageType.NORMAL, AttackType.NONE, 1, 5, 1, 8, 0),
			new ZergUnit("Devourer", true, 250f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.AIR, 1, 25, 2, 100, 2),
			new ZergUnit("Guardian", true, 150f, ArmorType.BIG, DamageType.NORMAL, AttackType.GROUND, 1, 20, 2, 30, 2),
			new ZergBuilding("Sunken Colony", false, 300f, ArmorType.BIG, DamageType.EXPLOSIVE, AttackType.GROUND, 1, 40, 1, 32, 2),
			new ZergBuilding("Spore Colony", false, 400f, ArmorType.BIG, DamageType.NORMAL, AttackType.AIR, 1, 15, 1, 15, 0), 
	});
	
	public static List<List> Units = Arrays.asList(new List[]{TerranUnits, ProtossUnits, ZergUnits});	
	
	
	//regen rates estimates in frames (based on http://www.teamliquid.net/forum/viewmessage.php?topic_id=97093#11 )
	public static int shieldRegen = 37;
	public static int zergRegen = 65;
	public static int burning = 13;
	//Regarding Protoss shield upgrades: http://www.teamliquid.net/forum/viewmessage.php?topic_id=76803#12
	
	
	public static Vector<String> getAttackerNames() {
		Vector<String> names  = new Vector();
		for(List<Unit> l : Units){
			for(Unit u : l){
				if(u.getAttack() != AttackType.NONE){
					names.add(u.getName());
				}
			}
		}
		return names;
	}	
	
	public static Vector<String> getDefenderNames() {
		Vector<String> names  = new Vector();
		for(List<Unit> l : Units){
			for(Unit u : l){
				if(u.getHp() != 0f){
					names.add(u.getName());
				}
			}
		}
		return names;
	}
	
	public static Unit getUnit(String name){		
		for(List<Unit> l : Units){
			for(Unit u : l){
				if(u.getName().equals(name))
					return u;
			}
		}
		return null;
	}
	
}
