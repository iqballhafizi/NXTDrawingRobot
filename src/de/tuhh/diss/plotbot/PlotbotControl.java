package de.tuhh.diss.plotbot;

import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

/**
 * This Class is used for control of the plotting robot. A great amount of time
 * should spend for controlling the robot. Add a suitable constructor and add
 * further methods you need for driving the motors, evaluating the sensors etc.
 */
public class PlotbotControl {
	private LightSensor lightSensor = new LightSensor(SensorPort.S3);
	private TouchSensor swivelSensor = new TouchSensor(SensorPort.S1);
	private TouchSensor penSensor = new TouchSensor(SensorPort.S2);
	private NXTRegulatedMotor wheelsMotor = new NXTRegulatedMotor(MotorPort.C);
	private NXTRegulatedMotor swivelMotor = new NXTRegulatedMotor(MotorPort.A);
	private NXTRegulatedMotor penMotor = new NXTRegulatedMotor(MotorPort.B);

	static final int DEFAULT_SPEED = 400;
	static final int UPPER_DRAWING_BOUNDARY = 230;
	static final int DISTANCE_PEN_TO_TABLE = 360;

	public PlotbotControl() {

	}

	public void goToXy(Coord position) {
		position.xyToAngle(position.pointX, position.pointY, wheelsMotor.getPosition());
		wheelsMotor.rotate(position.getWheelsMotorAngle());
		swivelMotor.rotate(position.getSwivelMotorAngle());
	}

	public void drawHorizontalLine() {
		swivelMotor.setSpeed(720);
		while (swivelMotor.isMoving() == true) {

			// wheelsMotor.setSpeed((int)
			// ((720*2*Math.PI*Coord.DIST_ARM_TO_PEN*Math.sin(arg0))/(Coord.SWIVEL_GEAR_RATIO*360)));
		}

	}

	public int getSwivelAngle() {
		return -swivelMotor.getPosition();
	}

	public void wheelsForward(int speed) {
		wheelsMotor.setSpeed(speed);
		if (wheelsMotor.isMoving() == false) {
			wheelsMotor.forward();
		}

	}

	public void wheelsBackward(int speed) {
		wheelsMotor.setSpeed(speed);
		if (wheelsMotor.isMoving() == false) {
			wheelsMotor.backward();
		}
	}

	public void swivelForward(int speed) {
		swivelMotor.setSpeed(speed);
		if (swivelMotor.isMoving() == false) {
			swivelMotor.forward();
		}
	}

	public void swivelBackward(int speed) {
		swivelMotor.setSpeed(speed);
		if (swivelMotor.isMoving() == false) {
			swivelMotor.backward();
		}
	}

	public void stopSwivel() {
		swivelMotor.stop();
	}

	public void stopWheels() {
		wheelsMotor.stop();
	}

	public void penDown() {
		penMotor.rotate(DISTANCE_PEN_TO_TABLE);
	}

	public void penUp() {
		penMotor.rotate(-DISTANCE_PEN_TO_TABLE);
	}

	public void initializePlotbot() {

		lightSensor.setFloodlight(true);
		wheelsMotor.setSpeed(DEFAULT_SPEED);
		penMotor.setSpeed(DEFAULT_SPEED);
		swivelMotor.setSpeed(DEFAULT_SPEED * 2);
		swivelMotor.backward();

		while (swivelMotor.isMoving() == true) {
			// Swivel arm
			if (swivelSensor.isPressed() == true) {
				swivelMotor.stop();
				// Drive the swivel arm to a center positionb (Robot 3)
				swivelMotor.rotate(Coord.SWIVEL_MAX_ANGLE * Coord.SWIVEL_GEAR_RATIO);
				swivelMotor.resetTachoCount();

			}
		}

		penMotor.backward();
		wheelsMotor.backward();

		while (penMotor.isMoving() == true || wheelsMotor.isMoving() == true) {
			// Wheels of the robot
			if (lightSensor.getNormalizedLightValue() < 550) {
				wheelsMotor.stop();
				wheelsMotor.rotate((int) ((Coord.DIST_ARM_TO_SENSOR - Coord.DIST_ARM_TO_PEN)
						* (Coord.WHEEL_GEAR_RATIO * 360 / (Coord.WHEEL_DIAMETER * Math.PI))));
				// wheelsMotor.rotate(306);
				wheelsMotor.resetTachoCount();
			}
			// Pen
			if (penSensor.isPressed() == true) {
				penMotor.stop();
				penMotor.resetTachoCount();
			}

		}

	}
}
