package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.PlotbotControl;

/**
 * Interface marking a shape which can be plotted by the Plotbot.
 * 
 */
public interface Plottable {
	public void plot(PlotbotControl pc);
}
