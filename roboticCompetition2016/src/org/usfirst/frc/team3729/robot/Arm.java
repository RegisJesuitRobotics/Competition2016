package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
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
		RightArmMotor.set(.5);
		Timer.delay(.25);
		LeftArmMotor.set(0);
		RightArmMotor.set(0);
		System.out.println("forward");
		
	}

	public void RotateBackward ()
	{
		LeftArmMotor.set(-.5);
		RightArmMotor.set(-.5);
		Timer.delay(.25);
		LeftArmMotor.set(0);
		RightArmMotor.set(0);
		System.out.println("backward");
	}
	
	
	
}

