package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.Coord;
import de.tuhh.diss.plotbot.Line;
import de.tuhh.diss.plotbot.PlotbotControl;

public class Calibration implements Plottable {

	// private LightSensor lightSensor = new LightSensor(SensorPort.S3);
	// private TouchSensor swivelSensor = new TouchSensor(SensorPort.S1);
	// private TouchSensor penSensor = new TouchSensor(SensorPort.S2);
	// private NXTRegulatedMotor wheelsMotor = new
	// NXTRegulatedMotor(MotorPort.C);
	// private NXTRegulatedMotor swivelMotor = new
	// NXTRegulatedMotor(MotorPort.A);
	// private NXTRegulatedMotor penMotor = new NXTRegulatedMotor(MotorPort.B);
	private Coord coordStart = new Coord(20, 20);
	private Coord coordEnd = new Coord(20, 100);
	private Line line;

	public void plot(PlotbotControl pc) {
		pc.initializePlotbot();

		// coordStart = new Coord(60, 200);
		// pc.goToXy(coordStart);
		line = new Line(coordStart, coordEnd);
		line.drawLine();
	}

}
