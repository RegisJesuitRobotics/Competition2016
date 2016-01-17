package org.usfirst.frc.team3729.robot;

import java.util.List;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.hal.CanTalonSRX;

/**
 * Contains a set of methods for driving the Robot
 * 
 * @author Regis Jesuit FIRST Team 3729
 *
 */
public class RobotDrive {

	/**
	 * Some controllers will send a value even when no input is being given. A
	 * deadzone allows these non-inputs to be ignored. This variable allows the
	 * configuration of this value.
	 */
	private double _deadZone;

	/**
	 * When turning around a point (vs spinning on a point), the inner motor
	 * group needs to spin slower than the outer motor group. This variable
	 * allows the configuration of this value.
	 */
	private double _innerTurnDampener;

	public RobotDrive(double deadZone, double innerTurnDampener) {
		if (deadZone == 0.0) {
			// Provide a default dead zone
			_deadZone = 2.0;
		} else {
			_deadZone = deadZone;
		}

		if (innerTurnDampener == 0.0) {
			// Provide a default dead zone
			_innerTurnDampener = 0.25;
		} else {
			_innerTurnDampener = innerTurnDampener;
		}
	}

	/**
	 * Sends input into the drive motors on the robot based on forward and turn
	 * input from the controller.
	 * 
	 * @param forwardInput
	 *            A value from 1 to -1 representing forwards and backwards,
	 *            respectively.
	 * @param turnInput
	 *            A value from 1 to -1 representing right and left,
	 *            respectively.
	 * @param leftMotorGroup
	 *            A list of CanTalonSRXs, which control motors on the left side
	 *            of the robot.
	 * @param rightMotorGroup
	 *            A list of CanTalonSRXs, which control motors on the right side
	 *            of the robot.
	 * @param motorMounting
	 *            An enumeration used to determine if either group of motors is
	 *            mounted backwards, so that the input to those motors can be
	 *            reversed. For example, if moving forward, and the left motors
	 *            are mounted in reverse, their input must be reversed so that
	 *            the robot does not spin on a point instead of moving forward.
	 */
	public void Drive(double forwardInput, double turnInput, List<CanTalonSRX> leftMotorGroup,
			List<CanTalonSRX> rightMotorGroup, MotorMounting motorMounting) {
		MotorInput motorInput = DetermineMotorInputFromControllerOutput(forwardInput, turnInput, motorMounting);

		// Now loop through all motors and set their values.
		for (CanTalonSRX leftTalon : leftMotorGroup) {
			leftTalon.Set(motorInput.getLeftMotorInput());
		}

		for (CanTalonSRX rightTalon : rightMotorGroup) {
			rightTalon.Set(motorInput.getRightMotorInput());
		}
	}

	public void DriveOldTalons(double forwardInput, double turnInput, List<Talon> leftMotorGroup,
			List<Talon> rightMotorGroup, MotorMounting motorMounting) {

		MotorInput motorInput = DetermineMotorInputFromControllerOutput(forwardInput, turnInput, motorMounting);

		for (Talon leftTalon : leftMotorGroup) {
			leftTalon.set(motorInput.getLeftMotorInput());
		}

		for (Talon rightTalon : rightMotorGroup) {
			rightTalon.set(motorInput.getRightMotorInput());
		}

	}

	private MotorInput DetermineMotorInputFromControllerOutput(double forwardInput, double turnInput,
			MotorMounting motorMounting) {
		MotorInput motorInput = new MotorInput();

		if (forwardInput > _deadZone && turnInput > _deadZone) {
			// Turn Forward Right
			motorInput.setLeftMotorInput(forwardInput);
			motorInput.setRightMotorInput(turnInput * _innerTurnDampener);
		} else if (forwardInput <= _deadZone && forwardInput >= -_deadZone && turnInput > _deadZone) {
			// Spin Right
			motorInput.setLeftMotorInput(-turnInput);
			motorInput.setRightMotorInput(turnInput);
		} else if (forwardInput > _deadZone && turnInput <= _deadZone && turnInput >= -_deadZone) {
			// Move Forward
			motorInput.setLeftMotorInput(forwardInput);
			motorInput.setRightMotorInput(forwardInput);
			//
		} else if (forwardInput > _deadZone && turnInput < _deadZone) {
			// Turn Forwards Left
			motorInput.setLeftMotorInput(forwardInput);
			motorInput.setRightMotorInput(-turnInput * _innerTurnDampener);
		} else if (forwardInput <= _deadZone && forwardInput >= -_deadZone && turnInput < -_deadZone) {
			// Spin Left
			motorInput.setLeftMotorInput(turnInput);
			motorInput.setRightMotorInput(-turnInput);
		} else if (forwardInput < -_deadZone && turnInput < _deadZone) {
			// Turn Backwards Left
			motorInput.setLeftMotorInput(forwardInput);
			motorInput.setRightMotorInput(turnInput * _innerTurnDampener);
		} else if (forwardInput < -_deadZone && turnInput > -_deadZone) {
			// Turn Backwards Left
			motorInput.setLeftMotorInput(forwardInput);
			motorInput.setRightMotorInput(-turnInput * _innerTurnDampener);
		} else {
			// Move Backwards
			motorInput.setLeftMotorInput(forwardInput);
			motorInput.setRightMotorInput(forwardInput);
		}

		if (motorMounting == MotorMounting.LeftMotorsReversed) {
			// Left motors get the opposite input value because they are
			// mounted in a reverse direction.
			motorInput.setLeftMotorInput(-motorInput.getLeftMotorInput());
		} else if (motorMounting == MotorMounting.RightMotorsReversed) {
			// Right motors get the opposite input value because they are
			// mounted in a reverse direction.
			motorInput.setRightMotorInput(-motorInput.getRightMotorInput());
		}

		return motorInput;
	}
}
