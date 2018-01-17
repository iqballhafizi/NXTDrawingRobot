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
	public int s;

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
	public void xyToAngle(double x, int y, int currentPosition) {
		swivelMotorAngle = (int) -(SWIVEL_GEAR_RATIO * Math.toDegrees((Math.asin((x / DIST_ARM_TO_PEN)))));
//		s = (int) (y + DIST_ARM_TO_PEN - ((currentPosition * 56 * Math.PI) / (5 * 360))
//				- (DIST_ARM_TO_PEN * (Math.cos(Math.toRadians(-swivelMotorAngle)))));
		
		s = (int) (y +DIST_ARM_TO_PEN- ((currentPosition * 56 * Math.PI) / (5 * 360))
				- (DIST_ARM_TO_PEN * (Math.cos(Math.toRadians(-swivelMotorAngle/SWIVEL_GEAR_RATIO)))));
		wheelsMotorAngle = (int) ((s * 360 * 5) / (56 * Math.PI));
		// wheelsMotorAngle = (int) ((y+105 - (DIST_ARM_TO_PEN *
		// Math.cos(Math.toRadians(swivelMotorAngle))))
		// * (360 / (WHEEL_DIAMETER * Math.PI)));

		// return new Coord(swivelMotorAngle, wheelsMotorAngle);
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
}
