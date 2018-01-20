package de.tuhh.diss.plotbot;

public class Line {
	private Coord start = new Coord();
	private Coord end = new Coord();
	private static PlotbotControl pc = new PlotbotControl();

	public Line(){
		
	}
	public Line(Coord start, Coord end) {
		this.start = start;
		this.end = end;
	}

	public void drawLine() {
		if (end.pointX == start.pointX) {
			pc.goToXy(start);
			pc.penDown();
			pc.goToXy(end);
			pc.penUp();
		} else {
			//Horizontal (test)
			pc.goToXy(start);
			pc.penDown();
			double m=(end.pointY-start.pointY)/(end.pointX-start.pointX);
			pc.swivelBackward(400);
			while(end.pointX>(Coord.DIST_ARM_TO_PEN*Math.sin((Math.toRadians(pc.getSwivelAngle()))))){
				pc.wheelsForward((int) ((m*2)+(5.9839*Math.sin((Math.toRadians(pc.getSwivelAngle()))))));
			}
			pc.stopSwivel();
			pc.stopWheels();
			pc.penUp();
		}
	}
}
