package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.PlotbotControl;
import lejos.nxt.Motor;

public class Calibration implements Plottable  {
	public void plot(PlotbotControl pc){
		Motor.C.setSpeed(720);
		Motor.C.forward();
		Motor.C.stop();
		// put your plot routine in here
	}
}
