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
		new Line(new Coord((width/3),UPPER_DRAWING_BOUNDARY),new Coord(0,-(width/2))).plot(pc);
		new Line(new Coord((width*2/3),0)).plot(pc);
		new Line(new Coord(-(width*2/3),(width/2))).plot(pc);
		new Line(new Coord(0,(-width/2)),new Coord(0,-(width/4))).plot(pc);
		new Line(new Coord(-(width/3),0),new Coord(width,0)).plot(pc);
		new Line(new Coord(-(width*3/4),-(width/4)),new Coord((width/2),0)).plot(pc);
		new Line(new Coord((width*1/4),(width*1/4))).plot(pc);
		new Line(new Coord(-(width*3/4),-(width/4)),new Coord(-(width*1/4),(width*1/4))).plot(pc);
	}
}
