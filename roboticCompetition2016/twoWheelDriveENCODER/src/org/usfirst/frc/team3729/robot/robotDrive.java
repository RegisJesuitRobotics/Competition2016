package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Talon;

public class robotDrive {

	Talon mcLeft, mcRight;
	Input input;
	public robotDrive(Talon mcLeft, Talon mcRight){
		
		this.mcLeft=mcLeft;
		this.mcRight = mcRight;
		input = new Input();
		
	}
	public void setSafetyEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void tankDrive() {
		mcLeft.set(input.left.getRawAxis(1));
		mcRight.set(input.right.getRawAxis(1));
		
		
	}

	public void tankDrive(double x, double y) {
		mcLeft.set(x);
		mcRight.set(y);
		
		
	}
}
