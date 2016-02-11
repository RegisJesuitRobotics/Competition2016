package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Arm {
	Victor LeftArmMotor,RightArmMotor;
	
	
	
	public Arm ()
	{ 
		LeftArmMotor = new Victor(0);
		RightArmMotor = new Victor(1);
		
	}
	
	public void RotateForward()
	{
		LeftArmMotor.set(.5);
		RightArmMotor.set(-.5);
		
	}

	public void RotateBackward ()
	{
		LeftArmMotor.set(-.5);
		RightArmMotor.set(.5);
		
	}
	
	
	
}

