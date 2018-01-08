package de.tuhh.diss.plotbot;

import de.tuhh.diss.plotbot.shape.Ship;
import de.tuhh.diss.plotbot.shape.Calibration;
import de.tuhh.diss.plotbot.shape.Plottable;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.TextMenu;

public class MainMenu {

	private static final String[] ITEMS = {"Calibration","Triangle","Ship"};	//add new text menu entries here
	private	static final String TITLE = "Choose Shape to draw:";
	private TextMenu menu;

	/**
	 * Creates a new MainMenu object.
	 */
	public MainMenu() {
		menu = new TextMenu(ITEMS, 1, TITLE);
	}
	public void start(){
		int selection = -1;
		do {
			selection = menu.select();
		}while(selection < 0);

		while(Button.ENTER.isDown()) {
		}
		Plottable toDraw = null;
		if (selection == 0) {
			toDraw = new Calibration();
			//Think about what you have to do to start the drawing routine
		}	
		/*if (selection == 1) {
			toDraw = new Triangle();
			//Think about what you have to do to start the drawing routine
		}	*/
		if (selection == 2) {
			toDraw = new Ship();
			//Think about what you have to do to start the drawing routine
		}	
	}
}
