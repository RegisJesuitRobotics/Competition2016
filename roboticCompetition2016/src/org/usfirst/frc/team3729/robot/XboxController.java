package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Joystick;

public class XboxController extends Joystick {
	public XboxController(int port) {
		super(port);
	}

	public double GetForwardInput() {
		return -super.getRawAxis(1);
	}

	/**
	 * Returns left or right. Left is negative, right is positive.
	 * 
	 * @return
	 */
	public double GetTurnInput() {
		return super.getRawAxis(4);
	}

	public double GetRightTrigger() {

		return super.getRawAxis(3);
	}

	public boolean GetA() {
		return super.getRawButton(0);
	}

	public boolean GetX() {
		return super.getRawButton(3);
	}

	public boolean GetB() {
		return super.getRawButton(2);
	}

	public boolean GetY() {
		return super.getRawButton(1);
	}

	public boolean GetLeftBumper() {
		return super.getRawButton(5);
	}

	public boolean GetRightBumper() {
		return super.getRawButton(6);
	}

	public boolean GetBack() {
		return super.getRawButton(7);
	}

	public boolean GetStart() {
		return super.getRawButton(8);
	}

	public double GetLeftTrigger() {
		return super.getRawAxis(2);
	}
}
