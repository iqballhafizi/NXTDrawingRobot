package de.tuhh.diss.plotbot;

/**
 * Class Coord used for defining the coordinate system x and y Conversion to and
 * from a position in (x,y) and robot angle is done here
 * 
 * @author Sebastian, Salvador, Iqbal Hafizi
 *
 */
public class Coord {
	public static final double WHEEL_DIAMETER = 56.0;
	public static final int WHEEL_GEAR_RATIO = 5;
	public static final int DIST_ARM_TO_SENSOR = 110;
	public static final double DIST_ARM_TO_PEN = 80.0;
	public static final double SWIVEL_GEAR_RATIO = 84.0;
	public static final int SWIVEL_MAX_ANGLE = 58;
	public int pointX, pointY;
	private int xCoord, yCoord;
	private int swivelMotorAngle;
	private int wheelsMotorAngle;
	private int swivelAngle;
	private int wheelsAngle;

	public Coord() {

	}

	public Coord(int x, int y) {
		this.pointX = x;
		this.pointY = y;
	}

	/**
	 * Method to Convert an angle to a coordinate position x,y
	 * 
	 * @param swivelMotorAngle
	 *            angle of swivel motor
	 * @param wheelsMotorAngle
	 *            angle of Motor
	 */
	public void angleToXy(int swivelMotorAngle, int wheelsMotorAngle) {
		yCoord = (int) ((DIST_ARM_TO_PEN * Math.cos(Math.toRadians(swivelMotorAngle)))
				+ (wheelsMotorAngle * WHEEL_DIAMETER * Math.PI / 360));
		xCoord = (int) (DIST_ARM_TO_PEN * Math.sin(Math.toRadians(swivelMotorAngle)));
		// return new Coord(x, y);
	}

	/**
	 * Method to get the swivel and wheels motors angle using given x and y
	 * position
	 * 
	 * @param x
	 *            Position in x plane
	 * @param y
	 *            Position in y plane
	 */
	// public void xyToAngle(double x, int y, int currentPositionY, int
	// currentPositionX) {
	// swivelMotorAngle = (int)
	// ((currentPositionX*SWIVEL_GEAR_RATIO)-(SWIVEL_GEAR_RATIO *
	// Math.toDegrees((Math.asin((x / DIST_ARM_TO_PEN))))));
	//
	// wheelsMotorAngle = (int) (((360 * WHEEL_GEAR_RATIO) / (WHEEL_DIAMETER *
	// Math.PI))
	// * (y + DIST_ARM_TO_PEN - ((currentPositionY * WHEEL_DIAMETER * Math.PI) /
	// (WHEEL_GEAR_RATIO * 360))
	// - (DIST_ARM_TO_PEN * (Math.cos(Math.toRadians(currentPositionX))))));
	//
	//// swivelAngle = (int) -(Math.toDegrees((Math.asin((x /
	// DIST_ARM_TO_PEN)))));
	//// wheelsAngle = (int) ((y + DIST_ARM_TO_PEN - ((currentPositionY * 56 *
	// Math.PI) / (5 * 360))
	//// - (DIST_ARM_TO_PEN * (Math.cos(Math.toRadians(swivelAngle))))));
	//
	// }
	/**
	 * Method to convert a coordinate position x,y to an angle
	 * 
	 * @param double
	 *            x position in x
	 * @param int
	 *            y position in y
	 * @param currentSwivelAngle
	 *            current angle of the swivel arm
	 */
	public void xyToAngle(double x, int y, int currentSwivelAngle) {
		swivelMotorAngle = (int) ((-(SWIVEL_GEAR_RATIO * Math.toDegrees((Math.asin((x / DIST_ARM_TO_PEN)))))));

		wheelsMotorAngle = (int) (((360 * WHEEL_GEAR_RATIO) / (WHEEL_DIAMETER * Math.PI))
				* (y + DIST_ARM_TO_PEN - (DIST_ARM_TO_PEN * (Math.cos(Math.toRadians(currentSwivelAngle))))));

	}

	/**
	 * Method to return x coordinate
	 * 
	 * @return xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * Method to return y coordinate
	 * 
	 * @return yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}

	/**
	 * Method to return swivelMotorAngle
	 * 
	 * @return swivelMotorAngle
	 */
	public int getSwivelMotorAngle() {
		return swivelMotorAngle;
	}

	/**
	 * Method to return wheelsMotorAngle
	 * 
	 * @return wheelsMotorAngle
	 */
	public int getWheelsMotorAngle() {
		return wheelsMotorAngle;
	}

	/**
	 * Method to return swivelAngle
	 * 
	 * @return swivelAngle
	 */

	public int getSwivelAngle() {
		return swivelAngle;
	}

	/**
	 * Method to return wheelsAngle
	 * 
	 * @return wheelsAngle
	 */
	public int getWheelsAngle() {
		return wheelsAngle;
	}
}
