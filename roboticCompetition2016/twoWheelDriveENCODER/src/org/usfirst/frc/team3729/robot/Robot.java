
package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * 
 * The VM is configured to automatically run this class, and to call the
 * 
 * functions corresponding to each mode, as described in the IterativeRobot
 * 
 * documentation. If you change the name of this class or the package after
 * 
 * creating this project, you must also update the manifest file in the resource
 * 
 * directory.
 * 
 */

public class Robot extends IterativeRobot {

	/**
	 * 
	 * This function is run when the robot is first started up and should be
	 * 
	 * used for any initialization code.
	 * 
	 */
	AnalogGyro gyro; // must be on analog 1 or 0
	Talon leftMotor = new Talon(0);
	Talon rightMotor = new Talon(1);
	Ultrasonic front = new Ultrasonic(0, 1);
	Ultrasonic right = new Ultrasonic(6, 7);
	Ultrasonic left = new Ultrasonic(4, 5);
	boolean isAutoProceduralRunning = false;

	// creates the ultra object andassigns ultra to be an ultrasonic sensor
	// which uses DigitalOutput 1 for

	// the echo pulse and DigitalInput 1 for the trigger pulse

	public void robotInit() {

		front.setAutomaticMode(true);
		right.setAutomaticMode(true);
		left.setAutomaticMode(true);// turns on automatic mode
		gyro = new AnalogGyro(0);
		gyro.initGyro();
		gyro.calibrate();

	}

	public void autonomousInit() {
		gyro.reset();
	}

	/**
	 * 
	 * This function is called periodically during autonomous
	 * 
	 */

	public void autonomousPeriodic() {

		// double rangeFront = front.getRangeInches(); // reads the range on the
		// // ultrasonic sensor
		// double rangeLeft = left.getRangeInches();
		// double rangeRight = right.getRangeInches();
		//
		// if ((rangeFront > 20) && (rangeLeft > 20) && (rangeRight > 20)) {
		// leftMotor.set(-0.4);
		// rightMotor.set(0.4);
		// }
		//
		// else if ((rangeFront > 20) && (rangeLeft > 20) && (rangeRight < 20))
		// {
		// leftMotor.set(0.3);
		// rightMotor.set(0.3);
		// } else if ((rangeFront > 20) && (rangeLeft < 20) && (rangeRight >
		// 20)) {
		// leftMotor.set(-0.3);
		// rightMotor.set(-0.3);
		// } else if ((rangeFront < 20) && (rangeLeft > 20) && (rangeRight >
		// 20)) {
		// leftMotor.set(0.2);
		// rightMotor.set(-0.2);
		// }
		//
		// else {
		// leftMotor.set(0.0);
		// rightMotor.set(-0.0);
		//
		// }
		//
		// System.out.println("Front: " + rangeFront + " Left: " + rangeLeft + "
		// Right: " + rangeRight);

		// Gyro Keep Centerline Test Code
		// double angle = gyro.getAngle();
		//
		// if (angle < -0.03) {
		// // Turn right to return to center
		// leftMotor.set(.3);
		// rightMotor.set(-.4);
		// } else if (angle > 0.03) {
		// // Turn left to return to center
		// leftMotor.set(.6);
		// rightMotor.set(-.3);
		// } else {
		// // Do nothing, robot is centered enough.
		// }

		// Gyro 90 degree test code
		if (!isAutoProceduralRunning) {
			// Effectively stops autonomous from running in a periodic loop.
			isAutoProceduralRunning = true;

			while (true) {
				// Move forward for 3 seconds.
				leftMotor.set(.3);
				rightMotor.set(-.3);
				Timer.delay(3);

				// Turn 90
				double angle = gyro.getAngle();
				double newAngle = angle - 90;

				System.out.println("Angle" + angle);
				System.out.println("NewAngle" + newAngle);

				// Want to turn until angle reads angle+90
				while (gyro.getAngle() > newAngle) {
					// Run the motors in a turn until we reach out new angle.
					leftMotor.set(.3);
					rightMotor.set(.7);
				}
			}
		}
	}

	public void teleopInit() {
		System.out.println("test teleop gyro reset");

		gyro.reset();
		Timer.delay(2);
	}

	/**
	 * 
	 * This function is called periodically during operator control
	 * 
	 */

	public void teleopPeriodic() {
		System.out.println("test");
		System.out.println("Center: " + gyro.getCenter());
		System.out.println("Angle: " + gyro.getAngle());
		System.out.println("Rotation: " + gyro.getRate());
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * This function is called periodically during test mode
	 * 
	 */

	public void testPeriodic() {

	}

}
