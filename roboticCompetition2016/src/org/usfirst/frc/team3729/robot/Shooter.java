package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {
	Victor AcceleratorRight;
	Victor AcceleratorLeft;
	Relay feederRight, feederLeft, elevator;
	DigitalInput Fore, Aft;

	public Shooter() {
		AcceleratorLeft = new Victor(3);
		AcceleratorRight = new Victor(2);
		feederRight = new Relay(1);
		feederLeft = new Relay(2);
		elevator= new Relay(0);
		Fore = new DigitalInput(4);
		Aft = new DigitalInput(5);
	}

	public void Feed(int feed) {
		if (feed == 1) {
			// set motors to intake
			System.out.println("kforward");

			feederRight.set(Relay.Value.kForward);
			feederLeft.set(Relay.Value.kReverse);
		} else if (feed == 0) {
			// set motors to output
			System.out.println("kreverse");
			feederRight.set(Relay.Value.kReverse);
			feederLeft.set(Relay.Value.kForward);
			
			
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

	public void Elevate(int elevate) {
		if (elevate == 1 && Fore.get() == true) {
			elevator.set(Relay.Value.kForward);
		} else if (elevate == -1 && Aft.get() == true) {
			elevator.set(Relay.Value.kReverse);
		} else {
			elevator.stopMotor();
		}
	}
}
