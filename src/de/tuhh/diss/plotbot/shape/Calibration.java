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
		// public void plot() {
//		lightSensor.setFloodlight(false);
//		wheelsMotor.setSpeed(DEFAULT_SPEED);
//		penMotor.setSpeed(DEFAULT_SPEED);
//		swivelMotor.setSpeed(DEFAULT_SPEED * 2);
//		swivelMotor.backward();
//		while (swivelMotor.isMoving() == true) {
//			// Swivel arm
//			if (swivelSensor.isPressed() == true) {
//				swivelMotor.stop();
//				// Drive the swivel arm to a center positionb (Robot 3)
//				swivelMotor.rotate(SWIVEL_MAX_ANGLE * SWIVEL_GEAR_RATIO);
//				swivelMotor.resetTachoCount();
//
//			}
//		}
//
//		penMotor.backward();
//		wheelsMotor.backward();
//		while (penMotor.isMoving() == true || wheelsMotor.isMoving() == true) {
//			// Wheels of the robot
//			if (lightSensor.getNormalizedLightValue() < 180) {
//				wheelsMotor.stop();
//				wheelsMotor.rotate(DIST_ARM_TO_SENSOR - DIST_ARM_TO_PEN);
//				wheelsMotor.resetTachoCount();
//			}
//			// Pen
//			if (penSensor.isPressed() == true) {
//				penMotor.stop();
//				penMotor.resetTachoCount();
//			}
//
//		}

//		cord.xyToAngle(cord.pointX, cord.pointY, wheelsMotor.getTachoCount());
		
//		wheelsMotor.rotate(cord.getWheelsMotorAngle());
//		swivelMotor.rotate(cord.getSwivelMotorAngle());
//		cord = new Coord(0, 10);
//		pc.goToXy(cord);
		cord=new Coord(60,200);
		pc.goToXy(cord);
		LCD.drawString("This"+cord.getSwivelMotorAngle(), 0, 4);
		Button.ENTER.waitForPressAndRelease();
		// wheelsMotor.rotate(cord.wheelsMotorAngle);
		// swivelMotor.rotate(cord.swivelMotorAngle);
		// +- 10mm
		// wheelsMotor.getPosition();
		// wheelsMotor.rotate(cord.xyToAngle(0,
		// 0).wheelsAngle*WHEEL_GEAR_RATIO);
		// wheelsMotor.rotate(
		// (int) (((DIST_ARM_TO_SENSOR - DIST_ARM_TO_PEN +
		// UPPER_DRAWING_BOUNDARY) * WHEEL_GEAR_RATIO * 360)
		// / (WHEEL_DIAMETER * Math.PI)));

	}

}
