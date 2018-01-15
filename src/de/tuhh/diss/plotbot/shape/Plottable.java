package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.PlotbotControl;

 
/**
 * Interface marking a shape which can be plotted by the Plotbot.
 * 
 */
public interface Plottable {
	static final int DEFAULT_SPEED=400;
	static final int WHEEL_DIAMETER=56;
	static final int WHEEL_GEAR_RATIO=5;
	static final int DIST_ARM_TO_SENSOR=105;
	static final int DIST_ARM_TO_PEN=80;
	
//	public void plot(PlotbotControl pc);
	public void plot();
}
