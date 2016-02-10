package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Talon;

public class Arm {
	Talon LeftArmMotor,RightArmMotor;
	
	
	
	public Arm ()
	{ 
		LeftArmMotor = new Talon(0);
		RightArmMotor = new Talon(1);
		
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

