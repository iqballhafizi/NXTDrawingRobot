package de.tuhh.diss.plotbot;

public class Coord {
	public static final int WHEEL_DIAMETER = 56;
	public static final int WHEEL_GEAR_RATIO = 5;
	public static final int DIST_ARM_TO_SENSOR = 110;
	public static final int DIST_ARM_TO_PEN = 80;
	public static final int SWIVEL_GEAR_RATIO = 84;
	public static final int SWIVEL_MAX_ANGLE = 60;
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
	public void xyToAngle(double x, int y, int currentPositionY) {
		swivelMotorAngle = (int) -(SWIVEL_GEAR_RATIO * Math.toDegrees((Math.asin((x / DIST_ARM_TO_PEN)))));

		wheelsMotorAngle = (int) (((360 * WHEEL_GEAR_RATIO) / (WHEEL_DIAMETER * Math.PI))
				* (y + DIST_ARM_TO_PEN - ((currentPositionY * WHEEL_DIAMETER * Math.PI) / (WHEEL_GEAR_RATIO * 360))
						- (DIST_ARM_TO_PEN * (Math.cos(Math.toRadians(-swivelMotorAngle / SWIVEL_GEAR_RATIO))))));

		swivelAngle = (int) -(Math.toDegrees((Math.asin((x / DIST_ARM_TO_PEN)))));
		wheelsAngle = (int) ((y + DIST_ARM_TO_PEN - ((currentPositionY * 56 * Math.PI) / (5 * 360))
				- (DIST_ARM_TO_PEN * (Math.cos(Math.toRadians(swivelAngle))))));

	}

	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public int getSwivelMotorAngle() {
		return swivelMotorAngle;
	}

	public int getWheelsMotorAngle() {
		return wheelsMotorAngle;
	}

	public int getSwivelAngle() {
		return swivelAngle;
	}

	public int getWheelsAngle() {
		return wheelsAngle;
	}
}
