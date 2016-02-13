package org.usfirst.frc.team3729.robot;

import java.lang.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;

public class robotDrive {
	CANTalon RightMotor1, LeftMotor1, RightMotor2, LeftMotor2;
	XboxController _xbox;
	AnalogGyro gyro;
	double circumference = 7.5 * 3.14159 / 12;
	double motorspeed = 18.52;
	double acceleration;
	Calendar cal;

	public robotDrive(XboxController xbox, AnalogGyro gyro_) {
		RightMotor1 = new CANTalon(2);
		RightMotor2 = new CANTalon(3);
		LeftMotor1 = new CANTalon(1);
		LeftMotor2 = new CANTalon(4);
		this._xbox = xbox;
		this.gyro = gyro_;

	}

	public void arcadeDrive() {

		// This limits the power of the motor, it is a percentage
		// This SHOULD NOT go above 1.0, not should it be negative
		double motorLimiterRatioinital = .5;
		double motorLimiterRatio = motorLimiterRatioinital;
		double forwardInput = _xbox.GetForwardInput();
		double turnInput = _xbox.GetTurnInput();
		double leftMotorInput = 0;
		double rightMotorInput = 0;
		double deadZone = 0.2;

		// System.out.println(forwardInput);
		// System.out.println(turnInput + "turn");

		if (forwardInput > deadZone && turnInput > deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = turnInput * .25;
			System.out.println("turn forward right");
			// Turn Forward Right
		} else if (forwardInput <= deadZone && forwardInput >= -deadZone && turnInput > deadZone) {
			leftMotorInput = turnInput;
			rightMotorInput = -turnInput;
			System.out.println("spin right");
			// Spin Right
		} else if (forwardInput > deadZone && turnInput <= deadZone && turnInput >= -deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = forwardInput;
			System.out.println("Forward");
			// Move Forward
		} else if (forwardInput > deadZone && turnInput < deadZone) {
			// Left input is negative, so it must be negated to move forward.
			leftMotorInput = -turnInput * .25;
			rightMotorInput = forwardInput;
			System.out.println("turn forward left");
			// Turn Forwards Left
		} else if (forwardInput <= deadZone && forwardInput >= -deadZone && turnInput < -deadZone) {
			// Left motor should move in reverse, right should move forward.
			// Left turn is a negative input already, so we don't need to negate
			// it again.
			leftMotorInput = turnInput;
			rightMotorInput = -turnInput;
			System.out.println("spin left");
			// Spin Left
		} else if (forwardInput < -deadZone && turnInput < -deadZone) {
			leftMotorInput = turnInput * .25;
			rightMotorInput = forwardInput;
			System.out.println("turn backwards left");
			// Turn Backwards Left
		} else if (forwardInput < -deadZone && turnInput > deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = -turnInput * .25;
			System.out.println("turn backwards right");
			// Turn Backwards Right
		} else if (forwardInput < -deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = forwardInput;
			System.out.println("move backwards");
			// Move Backwards
		}
		if (_xbox.GetRightTrigger() > deadZone) {
			motorLimiterRatio += (_xbox.GetRightTrigger() * .5);
		} else {
			motorLimiterRatio = motorLimiterRatioinital;
		}
		RightMotor1.set(-rightMotorInput * motorLimiterRatio);
		LeftMotor1.set(leftMotorInput * motorLimiterRatio);
		RightMotor2.set(-rightMotorInput * motorLimiterRatio);
		LeftMotor2.set(leftMotorInput * motorLimiterRatio);
		// System.out.println(leftMotorInput + "left");
		// System.out.println(rightMotorInput + "right");
	}

	private void DriveStraight(double speed, double currentheading) {

		double angle = gyro.getAngle();

		if (angle >= currentheading + .05) {
			LeftMotor1.set(speed * .75);
			LeftMotor2.set(speed * .75);
			RightMotor1.set(-speed);
			RightMotor2.set(-speed);
			System.out.println("right");
		} else if (angle <= currentheading - .05) {
			System.out.println("left");
			LeftMotor1.set(speed);
			LeftMotor2.set(speed);
			RightMotor1.set(-speed * .75);
			RightMotor2.set(-speed * .75);
		} else {
			System.out.println("straight");
			LeftMotor1.set(speed);
			LeftMotor2.set(speed);
			RightMotor1.set(-speed);
			RightMotor2.set(-speed);
		}

		// Timer.delay(circumference * (distance - .25) / motorspeed);
		/*
		 * double time = 0; double speedf = speed; acceleration = 2 * speed;
		 * while ((speedf > 0)) { speedf = speed - (acceleration * time);
		 * LeftMotor1.set(speedf); RightMotor1.set(speedf);
		 * LeftMotor2.set(speedf); RightMotor2.set(speedf); Timer.delay(0.005);
		 * time = time + .005; if (speedf < 0.05 || speedf > -0.05) {
		 * LeftMotor1.set(0); RightMotor1.set(0); LeftMotor2.set(0);
		 * RightMotor2.set(0); } } while ((speedf < 0)) { speedf = speed +
		 * (acceleration * time); LeftMotor1.set(speedf);
		 * RightMotor1.set(speedf); LeftMotor2.set(speedf);
		 * RightMotor2.set(speedf); Timer.delay(0.005); time = time + .005; if
		 * (speedf < 0.05 || speedf > -0.05) { LeftMotor1.set(0);
		 * RightMotor1.set(0); LeftMotor2.set(0); RightMotor2.set(0); } }
		 */

	}

	public void Drive(double distanceinitial, double speed) {
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		double time = Math.round(circumference * 1000 * distanceinitial / (motorspeed * speed));
		cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND, Integer.parseInt(df.format(time)));
		Date future = cal.getTime();
		Date dat = new Date();
		double currentheading = gyro.getAngle();
		while (dat.compareTo(future) != 1) {
			DriveStraight(speed, currentheading);
			dat = new Date();
			System.out.println(dat.compareTo(future));
		}
		DriveStraight(0,currentheading);

	}

	public void TurnAround() {
		Turn(180, true);
	}

	public void Turn(double angle, boolean isClockwise) {
		double currentheading = gyro.getAngle();
		if (isClockwise == true) {
			do {
				LeftMotor1.set(.5);
				LeftMotor2.set(.5);
				RightMotor1.set(.5);
				RightMotor2.set(.5);
				System.out.println(gyro.getAngle());
			} while (gyro.getAngle() <= currentheading + angle - 21);
			LeftMotor1.set(0);
			LeftMotor2.set(0);
			RightMotor1.set(0);
			RightMotor2.set(0);
		}
		if (isClockwise == false) {
			do {
				LeftMotor1.set(-.5);
				LeftMotor2.set(-.5);
				RightMotor1.set(-.5);
				RightMotor2.set(-.5);
				System.out.println(gyro.getAngle());
			} while (gyro.getAngle() >= -angle - currentheading + 21);
			LeftMotor1.set(0);
			LeftMotor2.set(0);
			RightMotor1.set(0);
			RightMotor2.set(0);
		}
	}

	public void Stop() {
		LeftMotor1.set(-.2);
		LeftMotor2.set(-.2);
		RightMotor1.set(-.2);
		RightMotor2.set(-.2);
		Timer.delay(.1);
		LeftMotor1.set(0);
		LeftMotor2.set(0);
		RightMotor1.set(0);
		RightMotor2.set(0);
	}

	public void turnToHeading(double heading) {

	}
}
