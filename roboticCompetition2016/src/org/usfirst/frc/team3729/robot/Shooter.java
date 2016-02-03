package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;

public class Shooter {
	CANTalon AcceleratorRight;
	CANTalon AcceleratorLeft;
	Relay feederRight, feederLeft, elevatorLeft, elevatorRight;

	public Shooter() {
		AcceleratorLeft = new CANTalon(1);
		AcceleratorRight = new CANTalon(0);
		feederRight = new Relay(0);
		feederLeft = new Relay(1);
		elevatorLeft = new Relay(2);
		elevatorRight = new Relay(3);
	}

	public void Feed(int feedDirection) {
		if (feedDirection == 1) {
			// set motors to intake
			feederRight.setDirection(Direction.kForward);
			feederLeft.setDirection(Direction.kReverse);
		} else if (feedDirection == 0) {
			// set motors to output
			feederRight.setDirection(Direction.kReverse);
			feederLeft.setDirection(Direction.kForward);
		} else {
			// set motors to 0
			feederRight.stopMotor();
			feederLeft.stopMotor();
		}
	}

	public void Shoot(boolean shouldShoot) {
		// rev then feed
		if (shouldShoot == true) {
			AcceleratorLeft.set(1);
			AcceleratorRight.set(-1);
			this.Feed(1);
		} else {
			AcceleratorLeft.set(0);
			AcceleratorRight.set(0);
		}
	}

	public void Elevate(int elevateDirection) {
		if (elevateDirection == 1) {
			elevatorLeft.setDirection(Direction.kForward);
			elevatorRight.setDirection(Direction.kForward);
		} else if (elevateDirection == -1) {
			elevatorLeft.setDirection(Direction.kReverse);
			elevatorRight.setDirection(Direction.kReverse);
		} else {
			elevatorLeft.stopMotor();
			elevatorRight.stopMotor();
		}
	}
}
