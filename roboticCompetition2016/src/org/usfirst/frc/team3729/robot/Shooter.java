package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Shooter {
	Victor AcceleratorRight;
	Victor AcceleratorLeft;
	Relay feederRight, feederLeft, elevator;
	DigitalInput Fore, Aft, Intake;

	public Shooter() {
		AcceleratorLeft = new Victor(3);
		AcceleratorRight = new Victor(2);
		feederRight = new Relay(0, Direction.kBoth);
		feederLeft = new Relay(1,Direction.kBoth);
		elevator = new Relay(2);
		Fore = new DigitalInput(9);
		Aft = new DigitalInput(0);
		Intake = new DigitalInput(8);

	}

	public void Feed(int feed) {
		if (feed == 1){// && Intake.get() == false) {
			// set motors to intake
			//System.out.println("kforward");

			feederRight.set(Relay.Value.kForward);
			feederLeft.set(Relay.Value.kReverse);
		} else if (feed == 0) {
			// set motors to output
			//System.out.println("kreverse");
			feederRight.set(Relay.Value.kReverse);
			feederLeft.set(Relay.Value.kForward);

		} /*else if (feed == 3) {
			feederRight.set(Relay.Value.kForward);
			feederLeft.set(Relay.Value.kReverse);
		}else if ((feed!=1||feed!=2) &&Intake.get()==true){
			feederRight.set(Relay.Value.kReverse);
			feederLeft.set(Relay.Value.kForward);
		}*/
		else {
			System.out.println("stop");
			// set motors to 0
			feederRight.stopMotor();
			feederLeft.stopMotor();
		}
	}

	public void Shoot(boolean shouldShoot) {
		// rev then feed
		if (shouldShoot == true) {
			AcceleratorLeft.set(-1);
			AcceleratorRight.set(1);
			this.Feed(1);
		} else {
			AcceleratorLeft.set(0);
			AcceleratorRight.set(0);
		}
	}

	public void Elevate(int elevate) {
		if (elevate == 1 ){ //&& Fore.get() == true{
			elevator.set(Relay.Value.kForward);
		} else if (elevate == -1 ){ //&& Aft.get() == true{
			elevator.set(Relay.Value.kReverse);
		} else {
			elevator.stopMotor();
			System.out.println(Fore.get() + " fore");
			System.out.println(Aft.get() + "aft");
		}
	}

	public void Shootautonomous() {
		this.Elevate(1);
		AcceleratorLeft.set(-1);
		AcceleratorRight.set(1);
		Timer.delay(1);
		this.Feed(1);
		Timer.delay(2);
		AcceleratorLeft.set(0);
		AcceleratorRight.set(0);
		this.Feed(0);
		this.Elevate(0);
	}
}
