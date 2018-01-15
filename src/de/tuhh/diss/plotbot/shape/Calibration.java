package de.tuhh.diss.plotbot.shape;

import javax.microedition.sensor.NXTSensorConnection;
import javax.microedition.sensor.SensorURL;

import de.tuhh.diss.plotbot.PlotbotControl;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.RegulatedMotor;

public class Calibration implements Plottable {

	private LightSensor lightSensor = new LightSensor(SensorPort.S3);
	private TouchSensor swivelSensor = new TouchSensor(SensorPort.S1);
	private TouchSensor penSensor = new TouchSensor(SensorPort.S2);
	private NXTRegulatedMotor wheelsMotor, swivelMotor, penMotor;

	// public void plot(PlotbotControl pc) {
	public void plot() {
		swivelMotor = new NXTRegulatedMotor(MotorPort.A);
		penMotor = new NXTRegulatedMotor(MotorPort.B);
		wheelsMotor = new NXTRegulatedMotor(MotorPort.C);
		lightSensor.setFloodlight(false);
		wheelsMotor.setSpeed(DEFAULT_SPEED);
		penMotor.setSpeed(DEFAULT_SPEED);
		swivelMotor.setSpeed(DEFAULT_SPEED);
		swivelMotor.backward();
		penMotor.backward();
		wheelsMotor.backward();

		while (swivelMotor.isMoving() == true || penMotor.isMoving() == true || wheelsMotor.isMoving() == true) {
			// Wheels of the robot
			if (lightSensor.getLightValue() < 20) {
				wheelsMotor.stop();
			}
			// Pen
			if (penSensor.isPressed() == true) {
				penMotor.stop();

			}
			// Swivel arm
			if (swivelSensor.isPressed() == true) {
				swivelMotor.stop();
				swivelMotor.resetTachoCount();
			}

		}
		//Need to test this
		wheelsMotor.rotate((int) ((DIST_ARM_TO_SENSOR - DIST_ARM_TO_PEN) * WHEEL_GEAR_RATIO / (WHEEL_DIAMETER * Math.PI)));
		wheelsMotor.resetTachoCount();
		swivelMotor.rotate(5000);

	}
}
