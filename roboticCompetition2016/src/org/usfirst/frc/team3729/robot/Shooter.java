package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class Shooter {
	XboxController _xbox;
	CANTalon AcceleratorRight;
	CANTalon AcceleratorLeft;

	public Shooter(XboxController xbox) {
		AcceleratorLeft = new CANTalon(1);
		AcceleratorRight = new CANTalon(0);
		this._xbox = xbox;
	}

	public void Feed() {
		if (_xbox.GetPOV() == 1) {
			// set motors to intake
		} else if (_xbox.GetPOV() == 0) {
			// set motors to output
		} else {
			// set motors to 0 
		}
	}

	public void Shoot() {
		// rev then feed
		if (_xbox.GetLeftBumper() == true) {
			AcceleratorLeft.set(1);
			AcceleratorRight.set(-1);
		} else {
			AcceleratorLeft.set(0);
			AcceleratorRight.set(0);
		}
	}

}
