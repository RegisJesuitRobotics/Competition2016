package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.Joystick;

public class XboxController extends Joystick{
	public XboxController(int port) {
		super(port);}
	
public double GetForwardInput(){
	return super.getRawAxis(1);}
	
public double GetTurnInput(){
	return super.getRawAxis(4);} 

public boolean GetRightTrigger(){
	
	double TriggerInput = super.getRawAxis(3);
	if (TriggerInput <= 0.0){
		return false;
	}
	else {
		return true;
	}
}
public boolean GetA(){
	return super.getRawButton(0);
}
public boolean GetX(){
	return super.getRawButton(3);
}
public boolean GetB(){
	return super.getRawButton(2);
}
public boolean GetY(){
	return super.getRawButton(1);
}
public boolean GetLeftBumper(){
	return super.getRawButton(5);
}
public boolean GetRightBumper(){
	return super.getRawButton(6);
}
public boolean GetBack(){
	return super.getRawButton(7);
}
public boolean GetStart(){
	return super.getRawButton(8);
}
public boolean GetLeftTrigger(){
	double TriggerInput = super.getRawAxis(2);
	if (TriggerInput <= 0.0){
		return false;
	}
	else {
		return true;
	}
}
}
