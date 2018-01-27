package de.tuhh.diss.plotbot;

import lejos.nxt.LCD;
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
	private boolean lastMoveSwivelForward;
	private int currentSwivelAngle;
	static final int DEFAULT_SPEED = 400;
	static final int DEFAULT_SPEED_PEN = 120;
	static final int DEFAULT_SPEED_SWIVEL = 620;
	static final int UPPER_DRAWING_BOUNDARY = 230;
	static final int DISTANCE_PEN_TO_TABLE = 360;

	public PlotbotControl() {

	}

	public void goToXy(Coord position) {
		position.xyToAngle(position.pointX, position.pointY, currentSwivelAngle);
		currentSwivelAngle += position.pointX;
		LCD.drawInt(currentSwivelAngle, 0, 5);
		wheelsMotor.rotate(position.getWheelsMotorAngle());
		handleSwivelTolerance(position.getSwivelMotorAngle());
		swivelMotor.rotate(position.getSwivelMotorAngle());
	}

	public void wheelsForward(int speed) {
		wheelsMotor.setSpeed(speed);
		
		if (wheelsMotor.isMoving() == false) {
			wheelsMotor.forward();
		}

	}

	public int getSwivelAngle() {
		return currentSwivelAngle;
	}
	public double getCurrentSwivelAngle() {
		return swivelMotor.getPosition()/Coord.SWIVEL_GEAR_RATIO;
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
		swivelMotor.setSpeed(DEFAULT_SPEED_SWIVEL);
	}

	public void stopWheels() {
		wheelsMotor.stop();
		wheelsMotor.setSpeed(DEFAULT_SPEED);
	}

	public void penDown() {
		penMotor.rotate(DISTANCE_PEN_TO_TABLE);

	}

	public void penUp() {
		penMotor.rotate(-DISTANCE_PEN_TO_TABLE);

	}

	public void resetTachoSwivel() {
		swivelMotor.resetTachoCount();
	}

	public void handleSwivelTolerance(int direction) {

		if (lastMoveSwivelForward == false && direction > 0) {
			swivelMotor.rotate(500);

			lastMoveSwivelForward = true;
		} else if (lastMoveSwivelForward == true && direction < 0) {
			swivelMotor.rotate(-500);

			lastMoveSwivelForward = false;
		}
		// }else
		// if(lastMoveSwivelForward==true&&direction>swivelMotor.getPosition()){
		// positionx=swivelMotor.getPosition();
		// }else
		// if(lastMoveSwivelForward==false&&direction<swivelMotor.getPosition())
		// {
		// positionx=swivelMotor.getPosition();
		// }

	}

	// public void penDown() {
	// penMotor.forward();
	// while (penMotor.getPosition() < DISTANCE_PEN_TO_TABLE) {
	//
	// }
	// penMotor.stop();
	// }
	//
	// public void penUp() {
	// penMotor.backward();
	// while (penSensor.isPressed() == false) {
	//
	// }
	// penMotor.stop();
	// }
	public void initializePlotbot() {

		lightSensor.setFloodlight(true);
		wheelsMotor.setSpeed(DEFAULT_SPEED);
		penMotor.setSpeed(DEFAULT_SPEED_PEN);
		swivelMotor.setSpeed(DEFAULT_SPEED_SWIVEL);
		swivelMotor.backward();

		while (swivelMotor.isMoving() == true) {
			// Swivel arm
			if (swivelSensor.isPressed() == true) {
				swivelMotor.stop();
				swivelMotor.rotate((int) (Coord.SWIVEL_MAX_ANGLE * Coord.SWIVEL_GEAR_RATIO));
				swivelMotor.resetTachoCount();
				lastMoveSwivelForward = true;
				currentSwivelAngle = 0;
			}
		}

		penMotor.backward();
		wheelsMotor.backward();

		while (penMotor.isMoving() == true || wheelsMotor.isMoving() == true) {
			// Wheels of the robot
			if (lightSensor.getNormalizedLightValue() < 520) {
				wheelsMotor.stop();
				wheelsMotor.rotate((int) ((Coord.DIST_ARM_TO_SENSOR - Coord.DIST_ARM_TO_PEN)
						* (Coord.WHEEL_GEAR_RATIO * 360 / (Coord.WHEEL_DIAMETER * Math.PI))));
				// wheelsMotor.rotate(306);
				wheelsMotor.resetTachoCount();
			}
			// Pen
			if (penSensor.isPressed() == true) {
				penMotor.stop();
				penMotor.rotate(50);
				penMotor.resetTachoCount();
			}

		}

	}
}
