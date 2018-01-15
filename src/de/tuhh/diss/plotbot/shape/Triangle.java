package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.PlotbotControl;
import lejos.nxt.Motor;


public class Triangle implements Plottable{
//	public void plot(PlotbotControl pc){
	public void plot(){
		Motor.C.setSpeed(720);
		Motor.C.forward();
		Motor.C.stop();
		
	}
}
