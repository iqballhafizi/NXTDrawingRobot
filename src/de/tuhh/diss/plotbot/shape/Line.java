package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.Coord;
import de.tuhh.diss.plotbot.PlotbotControl;
import lejos.util.Delay;

public class Line implements Plottable {
	private Coord start = new Coord();
	private Coord end = new Coord();


	public Line(Coord end) {
		this.start = new Coord(0,0);
		this.end = end;
	}

	public Line(Coord start, Coord end) {
		this.start = start;
		this.end = end;
	}

	public void plot(PlotbotControl pc) {
		if (end.pointX == 0) {
			pc.goToXy(start);
			pc.penDown();
			pc.goToXy(end);
			pc.penUp();
		} else {
			// Horizontal (test)
			pc.goToXy(start);
			pc.penDown();
			double m = end.pointY / end.pointX;

			double current = pc.getSwivelAngle();
			if (end.pointX > current) {
				pc.handleSwivelTolerance(-2);
				pc.resetTachoSwivel();
				pc.swivelBackward(400);
				while (end.pointX > (Math.abs(pc.getCurrentSwivelAngle()))) {
					pc.wheelsForward((int) ((Math.abs(m) * 70
							* Math.sin((Math.toRadians(((Math.abs(pc.getCurrentSwivelAngle()) + current))))))
							+ (70 * Math.sin((Math.toRadians(((Math.abs(pc.getCurrentSwivelAngle()) + current))))))));
					// LCD.drawString( "Vel: "+(int) ( (10000 *
					// Math.sin((Math.toRadians(((Math.abs(pc.getCurrentSwivelAngle())+current))))))),
					// 0, 6);
					Delay.msDelay(5);
				}
			} else {
				pc.handleSwivelTolerance(2);
				pc.resetTachoSwivel();
				pc.swivelForward(400);
				while (Math.abs(end.pointX) > (pc.getCurrentSwivelAngle())) {
					pc.wheelsForward((int) ((Math.abs(m) * 70
							* Math.sin((Math.toRadians(((Math.abs(pc.getCurrentSwivelAngle()) + current))))))
							+ (70 * Math.sin((Math.toRadians(((Math.abs(pc.getCurrentSwivelAngle()) + current))))))));
					// pc.wheelsForward((int) ((
					// (Math.abs(m*end.pointY) *
					// Math.sin((Math.toRadians(((Math.abs(pc.getCurrentSwivelAngle())
					// + current)))))))));
					// LCD.drawString(": "+(int) (((end.pointY)
					// + (Math.abs(m) *20*
					// Math.sin((Math.toRadians(((Math.abs(pc.getCurrentSwivelAngle())
					// + current)))))))), 0, 6);
					Delay.msDelay(5);
				}
			}

			pc.stopSwivel();
			pc.stopWheels();
			pc.penUp();
		}
	}

	public void drawLine() {

	}
}
