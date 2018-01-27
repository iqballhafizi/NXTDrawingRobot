package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.shape.Plottable;
import de.tuhh.diss.plotbot.Coord;
import de.tuhh.diss.plotbot.PlotbotControl;

public class Ship implements Plottable{
	
	private int width;
	public Ship(int width){
		this.width=width;
	}
	public void plot(PlotbotControl pc){
		new Line(new Coord(0,UPPER_DRAWING_BOUNDARY),new Coord(0,-(width/2))).plot(pc);
		new Line(new Coord((width*2/3),0)).plot(pc);
		new Line(new Coord(-(width*2/3),(width/2))).plot(pc);
	}
}
