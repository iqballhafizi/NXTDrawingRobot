package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.Coord;
import de.tuhh.diss.plotbot.PlotbotControl;


public class Triangle implements Plottable{

	private int width, height;
	public Triangle(int width,int height){
		this.width=width;
		this.height=height;
	}
	public void plot(PlotbotControl pc){
		new Line(new Coord(0,UPPER_DRAWING_BOUNDARY),new Coord(0,-height)).plot(pc);
		new Line(new Coord(width,0)).plot(pc);
		new Line(new Coord(-width,height)).plot(pc);
	}
}
