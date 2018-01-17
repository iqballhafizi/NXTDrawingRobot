package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.Coord;
import de.tuhh.diss.plotbot.PlotbotControl;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Calibration implements Plottable {

	private LightSensor lightSensor = new LightSensor(SensorPort.S3);
	private TouchSensor swivelSensor = new TouchSensor(SensorPort.S1);
	private TouchSensor penSensor = new TouchSensor(SensorPort.S2);
	private NXTRegulatedMotor wheelsMotor = new NXTRegulatedMotor(MotorPort.C);
	private NXTRegulatedMotor swivelMotor = new NXTRegulatedMotor(MotorPort.A);
	private NXTRegulatedMotor penMotor = new NXTRegulatedMotor(MotorPort.B);
	private Coord cord = new Coord(0,230);

	public void plot(PlotbotControl pc) {
		pc.initializePlotbot();
		
		cord=new Coord(60,200);
		pc.goToXy(cord);
		
		
	}

}
