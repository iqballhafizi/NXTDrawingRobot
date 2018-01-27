package de.tuhh.diss.plotbot;

import de.tuhh.diss.plotbot.shape.Ship;
import de.tuhh.diss.plotbot.shape.Triangle;
import de.tuhh.diss.plotbot.shape.Calibration;
import de.tuhh.diss.plotbot.shape.Plottable;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.Delay;
import lejos.util.TextMenu;

public class MainMenu {

	private static final String[] ITEMS_MENU = { "Calibration", "Triangle", "Ship", "Exit" };
	private static final String TITLE_MENU = "Choose Shape to draw:";
	private TextMenu lcdMenu;
	private static PlotbotControl pc = new PlotbotControl();
private int width,height;
	/**
	 * Creates a new MainMenu object.
	 */
	public MainMenu() {
		lcdMenu = new TextMenu(ITEMS_MENU, 1, TITLE_MENU);
	}

	public void start() {
		int selection = -1;
		while (selection < 3) {
			do {
				selection = lcdMenu.select();
			} while (selection < 0);
			Plottable toDraw = null;
			switch (selection) {
			case 0:
				LCD.clearDisplay();

				toDraw = new Calibration();
				toDraw.plot(pc);
				break;
			case 1:
				 width=selectAmount("Triangle", "Height");
				 height=selectAmount("Triangle", "Width");
				toDraw = new Triangle(width,height);
				toDraw.plot(pc);
				break;
			case 2:
				 width=selectAmount("Ship", "Width");
				toDraw = new Ship(width);
				break;
			case 3:
				break;
			}
		}

		// if (selection == 0) {
		// toDraw = new Calibration();
		// toDraw.plot(pc);
		//
		// // Think about what you have to do to start the drawing routine
		// } else if (selection == 1) {
		// toDraw = new Triangle();
		// // Think about what you have to do to start the drawing routine
		// } else if (selection == 2) {
		// toDraw = new Ship();
		// // Think about what you have to do to start the drawing routine
		// }

	}

	public int selectAmount(String title,String subTitle) {
		int selection=0;
		LCD.clearDisplay();
		LCD.drawString(title, 0, 0);
		LCD.drawString(subTitle+": ", 0, 1);	
		while(Button.ENTER.isUp()){
			LCD.drawInt(selection, 0, 3);
			while(Button.RIGHT.isDown()){
				selection++;
				if(subTitle.equalsIgnoreCase("Widht")&&selection>=61){
					selection=66;
				}
				if(subTitle.equalsIgnoreCase("Height")&&selection>=230){
					selection=230;
				}
				Delay.msDelay(500);
			}
			while(Button.LEFT.isDown()){
				selection--;
				if(selection<=0){
					selection=0;
				}
				Delay.msDelay(500);
			}
		}
		return selection;
	}
}
