package org.usfirst.frc.team3729.robot;



	
	 

	import edu.wpi.first.wpilibj.AnalogInput;
	import edu.wpi.first.wpilibj.AnalogPotentiometer;
	import edu.wpi.first.wpilibj.interfaces.Potentiometer;

	public class stringPotentiometer {
		
		Potentiometer pot;
		AnalogInput ai;
		
		public stringPotentiometer(){
		
		
		//pot = new AnalogPotentiometer(0, 360, 30);
		 ai = new AnalogInput(0);
		pot = new AnalogPotentiometer(ai, 360, 30);
		
		}
		
	 
		
		
	}


