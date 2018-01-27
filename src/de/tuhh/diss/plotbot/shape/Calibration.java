package de.tuhh.diss.plotbot.shape;


import de.tuhh.diss.plotbot.PlotbotControl;
import lejos.nxt.Button;
/**
 * Class Calibration 
 * @author Sebastian, Salvador, Iqbal
 *
 */

public class Calibration implements Plottable {

	
/**
 * Method to initialize the robot.
 * @param PlotbotControl pc 
 */
	public void plot(PlotbotControl pc) {
		pc.initializePlotbot();
	}

}
