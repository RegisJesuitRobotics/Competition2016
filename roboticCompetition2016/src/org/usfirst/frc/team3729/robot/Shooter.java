package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {
	Victor AcceleratorRight;
	Victor AcceleratorLeft;
	Relay feederRight, feederLeft, elevatorLeft, elevatorRight;
	DigitalInput Fore, Aft;

	public Shooter() {
		AcceleratorLeft = new Victor(3);
		AcceleratorRight = new Victor(2);
		feederRight = new Relay(1);
		feederLeft = new Relay(0);
		elevatorLeft = new Relay(2);
		elevatorRight = new Relay(3);
		Fore = new DigitalInput(4);
		Aft = new DigitalInput(5);
	}

	public void Feed(int feedDirection) {
		if (feedDirection == 1) {
			// set motors to intake
			System.out.println("kforward");

			feederRight.setDirection(Direction.kForward);
			feederLeft.setDirection(Direction.kReverse);
		} else if (feedDirection == 0) {
			// set motors to output
			System.out.println("kreverse");
			feederRight.setDirection(Direction.kReverse);
			feederLeft.setDirection(Direction.kForward);
		} else {
			System.out.println("stop");
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
		if (elevateDirection == 1 && Fore.get() == true) {
			elevatorLeft.setDirection(Direction.kForward);
			elevatorRight.setDirection(Direction.kForward);
		} else if (elevateDirection == -1 && Aft.get() == true) {
			elevatorLeft.setDirection(Direction.kReverse);
			elevatorRight.setDirection(Direction.kReverse);
		} else {
			elevatorLeft.stopMotor();
			elevatorRight.stopMotor();
		}
	}
}
