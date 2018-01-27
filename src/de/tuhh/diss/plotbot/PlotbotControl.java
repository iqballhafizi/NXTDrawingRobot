package de.tuhh.diss.plotbot;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

/**
 * This Class is used to control the plotting robot. Motors and Sensors are
 * declared and ports are given.
 * 
 * @author Sebastian, Salvador, Iqbal Hafizi
 *
 */
public class PlotbotControl {
	private LightSensor lightSensor = new LightSensor(SensorPort.S3);
	private TouchSensor swivelSensor = new TouchSensor(SensorPort.S1);
	private TouchSensor penSensor = new TouchSensor(SensorPort.S2);
	private NXTRegulatedMotor wheelsMotor = new NXTRegulatedMotor(MotorPort.C);
	private NXTRegulatedMotor swivelMotor = new NXTRegulatedMotor(MotorPort.A);
	private NXTRegulatedMotor penMotor = new NXTRegulatedMotor(MotorPort.B);

	private boolean lastMoveSwivelForward; // boolean varibale tells if last
											// move of swievel was to right or
											// left
	private int currentSwivelAngle;
	static final int DEFAULT_SPEED = 400;
	static final int DEFAULT_SPEED_PEN = 120;
	static final int DEFAULT_SPEED_SWIVEL = 620;
	static final int UPPER_DRAWING_BOUNDARY = 230;
	static final int DISTANCE_PEN_TO_TABLE = 360;

	public PlotbotControl() {

	}

	/**
	 * Function to move the robot to the given coordinate. Converts the
	 * coordinates to an angle and moves the Wheels and Swievel arm to that
	 * position
	 * 
	 * @param Coord
	 *            position
	 */
	public void goToXy(Coord position) {
		position.xyToAngle(position.pointX, position.pointY, currentSwivelAngle);
		currentSwivelAngle += position.pointX;
		LCD.drawInt(currentSwivelAngle, 0, 5);
		wheelsMotor.rotate(position.getWheelsMotorAngle());
		handleSwivelTolerance(position.getSwivelMotorAngle());
		swivelMotor.rotate(position.getSwivelMotorAngle());
	}

	/**
	 * function to move the wheels forward
	 * 
	 * @param speed
	 *            defines the speed at which to move the wheels
	 * 
	 */
	public void wheelsForward(int speed) {
		wheelsMotor.setSpeed(speed);

		if (wheelsMotor.isMoving() == false) {
			wheelsMotor.forward();
		}

	}

	/**
	 * @return
	 */
	public int getSwivelAngle() {
		return currentSwivelAngle;
	}

	/**
	 * 
	 * @return
	 */
	public double getCurrentSwivelAngle() {
		return swivelMotor.getPosition() / Coord.SWIVEL_GEAR_RATIO;
	}

	/**
	 * method to move the wheels backward
	 * 
	 * @param speed
	 *            defines the speed at which to move the wheels
	 * 
	 */
	public void wheelsBackward(int speed) {
		wheelsMotor.setSpeed(speed);
		if (wheelsMotor.isMoving() == false) {
			wheelsMotor.backward();
		}
	}

	/**
	 * method to move the swivel forward
	 * 
	 * @param speed
	 *            defines the speed at which to move the swivel
	 * 
	 */
	public void swivelForward(int speed) {
		swivelMotor.setSpeed(speed);
		if (swivelMotor.isMoving() == false) {
			swivelMotor.forward();
		}
	}

	/**
	 * method to move the swivel backward
	 * 
	 * @param speed
	 *            defines the speed at which to move the swivel
	 * 
	 */
	public void swivelBackward(int speed) {
		swivelMotor.setSpeed(speed);
		if (swivelMotor.isMoving() == false) {
			swivelMotor.backward();
		}
	}

	/**
	 * Stop the swivel and reset its sepeed to defaul speed
	 */
	public void stopSwivel() {
		swivelMotor.stop();
		swivelMotor.setSpeed(DEFAULT_SPEED_SWIVEL);
	}

	/**
	 * Stop the motor and reset its sepeed to defaul speed
	 */
	public void stopWheels() {
		wheelsMotor.stop();
		wheelsMotor.setSpeed(DEFAULT_SPEED);
	}

	/**
	 * function to bring the pen down for drawing
	 */
	public void penDown() {
		penMotor.rotate(DISTANCE_PEN_TO_TABLE);

	}

	/**
	 * function to take the pen up
	 */
	public void penUp() {
		penMotor.rotate(-DISTANCE_PEN_TO_TABLE);

	}

	/**
	 * function to reset the tacho count of swievel
	 */
	public void resetTachoSwivel() {
		swivelMotor.resetTachoCount();
	}

	/**
	 * method to handle the swivel tolerance when the swivel is trying to move
	 * the opposite direction of last movement the motor rotates a certain
	 * amount with swivel staying stationary for some time
	 * 
	 * @param direction
	 *            if higher than 0 then swivel has to move forward otherwise
	 *            backward
	 * 
	 */
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
	/**
	 * This function initializes the robot by driving the robot to 0,0 position.
	 * Moves the swivel to the right untill swivel sesnor is pressed Moves the
	 * wheel motor backward untill the black bar is detected Moves the pen up
	 * untill pen sensor is pressed
	 * 
	 */
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
