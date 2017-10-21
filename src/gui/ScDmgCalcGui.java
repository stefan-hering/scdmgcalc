package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import scdamagecalc.Calculator;
import scdamagecalc.ProtossResult;
import scdamagecalc.Result;
import scdamagecalc.UnitData;

public class ScDmgCalcGui extends JPanel {
	private Calculator calc;
	private JComboBox attacker;
	private JComboBox defender;
	private JComboBox shield;
	private JTable damageDone;
	private JTable hitsToKill;
	private JTable timeToKill;
	private JTable damageShield;
	private JPanel shieldDamagePanel;
	private JPanel attackerPanel;
	private JPanel defenderPanel;
	private JPanel shieldPanel;
	private JPanel hitsPanel;
	private JPanel damagePanel;
	private JPanel timePanel;
	private JPanel menu;
	private JPanel tables;
	
	public ScDmgCalcGui(){
		this.setLayout(new BorderLayout());
		setSize(new Dimension(600,750));
		calc = new Calculator();
		
		menu = new JPanel();
		tables = new JPanel();
		menu.setLayout(new GridLayout(1,3,16,0));
		tables.setLayout(new GridLayout(4,1,0,16));
		
		attacker = new JComboBox(UnitData.getAttackerNames());
		defender = new JComboBox(UnitData.getDefenderNames());
		shield = new JComboBox(new Object[]{0,1,2,3});
		
		shield.setEnabled(false);
		
		attacker.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fillTables();
				
			}
		});
		defender.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fillTables();
				
			}
		});		
		shield.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fillTables();
				
			}
		});
		
		damagePanel = new JPanel();
		hitsPanel = new JPanel();
		timePanel = new JPanel();
		attackerPanel = new JPanel();
		defenderPanel = new JPanel();
		shieldPanel = new JPanel();
		
		attackerPanel.setBorder(BorderFactory.createTitledBorder("Attacker:"));
		defenderPanel.setBorder(BorderFactory.createTitledBorder("Defender:"));
		shieldPanel.setBorder(BorderFactory.createTitledBorder("Shield-upgrade:"));
		
		attackerPanel.add(attacker);
		defenderPanel.add(defender);
		shieldPanel.add(shield);
		
		menu.add(attackerPanel);
		menu.add(defenderPanel);
		menu.add(shieldPanel);
		
		
		damagePanel = new JPanel();
		hitsPanel = new JPanel();
		timePanel = new JPanel();
		shieldDamagePanel = new JPanel();
		damagePanel.setBorder(BorderFactory.createTitledBorder("Damage done per hit"));
		hitsPanel.setBorder(BorderFactory.createTitledBorder("Hits for a kill"));
		timePanel.setBorder(BorderFactory.createTitledBorder("Time for a kill"));
		shieldDamagePanel.setBorder(BorderFactory.createTitledBorder("Damage to shield"));
		
		damageDone = new JTable();
		hitsToKill = new JTable();
		timeToKill = new JTable();
		damageShield = new JTable();
		
		damagePanel.add(damageDone);
		hitsPanel.add(hitsToKill);
		timePanel.add(timeToKill);
		shieldDamagePanel.add(damageShield);
				
		tables.add(damagePanel);
		tables.add(hitsPanel);
		tables.add(timePanel);
		tables.add(shieldDamagePanel);
		
		this.add(menu, BorderLayout.NORTH);
		this.add(tables, BorderLayout.CENTER);

		attacker.setSelectedItem("Zealot");
		defender.setSelectedItem("Zergling");
		
		updateUI();
	}

	public void fillTables(){
		Result r = calc.calculateBattle(UnitData.getUnit(attacker.getSelectedItem().toString()), UnitData.getUnit(defender.getSelectedItem().toString()));
		if (r != null) {
			tables.setBorder(BorderFactory.createTitledBorder(r.getTitle()));
			if(! (r instanceof ProtossResult)){
				shield.setEnabled(false);
				damageDone.setModel(new ScTableModel(r.damageDealt));
				hitsToKill.setModel(new ScTableModel(r.hitsToKill));
				timeToKill.setModel(new ScTableModel(r.timeToKill));		
				damageShield.setModel(new EmptyTableModel());
			}else{
				shield.setEnabled(true);
				damageDone.setModel(new ScTableModel(r.damageDealt));
				hitsToKill.setModel(new ScTossTableModel(((ProtossResult)r).hitsToKill, (Integer)shield.getSelectedItem()));
				timeToKill.setModel(new ScTossTableModel(((ProtossResult)r).timeToKill, (Integer)shield.getSelectedItem()));		
				damageShield.setModel(new ScTableModel(((ProtossResult)r).damageToShield));		
			}
		} 

		updateUI();
	}
}
