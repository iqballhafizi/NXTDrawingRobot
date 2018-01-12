package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.PlotbotControl;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class Calibration implements Plottable {

	public void plot(PlotbotControl pc) {
		SensorPort.S2.activate();
		SensorPort.S1.activate();
		SensorPort.S3.activate();
		Motor.C.setSpeed(DEFAULT_SPEED);
		Motor.B.setSpeed(DEFAULT_SPEED);
		Motor.A.setSpeed(DEFAULT_SPEED);
		Motor.A.forward();
		Motor.B.backward();
		Motor.C.backward();

		while (Motor.A.isMoving() == true || Motor.B.isMoving() == true || Motor.C.isMoving() == true) {
			// Wheels of the robot
			if (SensorPort.S3.readValue() < 30) {
				Motor.C.stop();
			}
			// Pen
			if (SensorPort.S2.readBooleanValue() == true) {
				Motor.B.stop();
				Motor.B.resetTachoCount();
			}
			// Swivel arm
			if (SensorPort.S1.readBooleanValue() == true) {
				Motor.A.stop();
			}

		}
		Motor.C.rotate((int) (-DIST_ARM_TO_SENSOR * WHEEL_GEAR_RATIO / (WHEEL_DIAMETER * Math.PI)));
		Motor.C.resetTachoCount();
		Motor.A.rotate(-Motor.A.getTachoCount());
		SensorPort.S3.passivate();

	}
}
