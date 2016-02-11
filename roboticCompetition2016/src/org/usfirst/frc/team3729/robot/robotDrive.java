package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;

public class robotDrive {
	CANTalon RightMotor1, LeftMotor1, RightMotor2, LeftMotor2;
	XboxController _xbox;
	AnalogGyro gyro;

	public robotDrive(XboxController xbox) {
		RightMotor1 = new CANTalon(2);
		RightMotor2 = new CANTalon(3);
		LeftMotor1 = new CANTalon(1);
		LeftMotor2 = new CANTalon(4);
		this._xbox = xbox;
		gyro = new AnalogGyro(0);
		gyro.initGyro();
		gyro.calibrate();
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

	public void autonomous() {
		RightMotor1.set(-.5);
		LeftMotor1.set(.5);
		RightMotor2.set(-.5);
		LeftMotor2.set(.5);
		Timer.delay(.25);

		RightMotor1.set(0);
		LeftMotor1.set(0);
		RightMotor2.set(0);
		LeftMotor2.set(0);
	}

	public void DriveStraight(double speed) {
		gyro.reset();
		double angle = gyro.getAngle();

		if (angle > .05)

		{
			RightMotor1.set(speed);
			RightMotor2.set(speed);
			LeftMotor1.set(speed * .75);
			LeftMotor2.set(speed * .75);
		}

		else if (angle < -.05) {
			LeftMotor1.set(speed);
			LeftMotor2.set(speed);
			RightMotor1.set(speed * .75);
			RightMotor2.set(speed * .75);
		} else {
			LeftMotor1.set(speed);
			LeftMotor2.set(speed);
			RightMotor1.set(speed);
			RightMotor2.set(speed);
		}

	}
	public void TurnAround()
	{
		gyro.reset();
		do 
		{
			LeftMotor1.set(.5); 
			LeftMotor2.set(.5);
			RightMotor1.set(-.5);
			RightMotor2.set(.5);
		}while (gyro.getAngle() <= 180);
		// leftMotorInput = turnInput;
		// rightMotorInput = -turnInput;
		// System.out.println("spin right");
		
	}
	
}
