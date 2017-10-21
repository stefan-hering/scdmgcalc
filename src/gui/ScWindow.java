package gui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScWindow extends Frame {
	public ScWindow(){
		this.add(new ScDmgCalcGui());
		this.setSize(600,750);
		this.setVisible(true);
		this.addWindowListener ( new WindowAdapter () {
			public void windowClosing ( WindowEvent evt )
			{
				System.exit(0);
			}
		});

	}
	
	public static void main(String[] args){
		new ScWindow();
	}
}
