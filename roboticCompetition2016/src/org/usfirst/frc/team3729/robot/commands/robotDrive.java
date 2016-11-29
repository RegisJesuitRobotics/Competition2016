package org.usfirst.frc.team3729.robot.commands;

import edu.wpi.first.wpilibj.Talon;

public class robotDrive {
	Talon RightMotor1, LeftMotor1, RightMotor2, LeftMotor2;
	XboxControler _xbox;

	public robotDrive(XboxControler xbox) {
		RightMotor1 = new Talon(2);
		RightMotor2 = new Talon(3);
		LeftMotor1 = new Talon(1);
		LeftMotor2 = new Talon(0);
		this._xbox = xbox;
	}

	public void arcadeDrive() {

		// This limits the power of the motor, it is a percentage
		// This SHOULD NOT go above 1.0, not should it be negative
		double motorLimiterRatioinital = 1; // change to
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
		// Speed Switch
		if (_xbox.GetRightTrigger() > deadZone) {
			motorLimiterRatio = (_xbox.GetRightTrigger() * 0.5);
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

	public void mechenumDrive() {
		boolean leftInput = _xbox.GetLeftBumper();
		boolean rightInput = _xbox.GetRightBumper();

		// Maybe lower this
		double motorLimiterRatioinital = 0.8;

		double motorLimiterRatio = motorLimiterRatioinital;
		double deadZone = 0.2;
		double leftMotorInput = 1;
		double rightMotorInput = 1;

		// speed button
		if (_xbox.GetRightTrigger() > deadZone) {
			motorLimiterRatio = 0.4;
		} else {
			motorLimiterRatio = motorLimiterRatioinital;
		}

		if (leftInput == true) {
			RightMotor1.set(rightMotorInput * motorLimiterRatio);
			LeftMotor1.set(leftMotorInput * motorLimiterRatio);
			RightMotor2.set(-rightMotorInput * motorLimiterRatio);
			LeftMotor2.set(-leftMotorInput * motorLimiterRatio);

		} else if (rightInput == true) {

			RightMotor1.set(-rightMotorInput * motorLimiterRatio);
			LeftMotor1.set(-leftMotorInput * motorLimiterRatio);
			RightMotor2.set(rightMotorInput * motorLimiterRatio);
			LeftMotor2.set(leftMotorInput * motorLimiterRatio);
		}

	}
}
