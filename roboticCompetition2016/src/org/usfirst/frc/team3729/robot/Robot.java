
package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			CANTalon RightMotor,LeftMotor;

			RightMotor = new CANTalon(1);
			LeftMotor = new CANTalon(2);

			RightMotor.set(.5);
			LeftMotor.set(.5);
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		CANTalon RightMotor,LeftMotor;

		RightMotor = new CANTalon(1);
		LeftMotor = new CANTalon(2);

		XboxController xbox = new XboxController(0);

		double forwardInput = xbox.GetForwardInput();
		double turnInput = xbox.GetTurnInput();
		double leftMotorInput = 0;
		double rightMotorInput = 0;
		double deadZone = 0.2;

		// System.out.println(forwardInput);
		// System.out.println(turnInput + "turn");

		if (forwardInput > deadZone && turnInput > deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = turnInput * .25;
			// Turn Forward Right
		} else if (forwardInput <= deadZone && forwardInput >= -deadZone && turnInput > deadZone) {
			leftMotorInput = -turnInput;
			rightMotorInput = turnInput;
			// Spin Right
		} else if (forwardInput > deadZone && turnInput <= deadZone && turnInput >= -deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = forwardInput;
			// Move Forward
		} else if (forwardInput > deadZone && turnInput < deadZone) {
			rightMotorInput = forwardInput;
			leftMotorInput = -turnInput * .25;
			// Turn Forwards Left
		} else if (forwardInput <= deadZone && forwardInput >= -deadZone && turnInput < -deadZone) {
			rightMotorInput = turnInput;
			leftMotorInput = -turnInput;
			// Spin Left
		} else if (forwardInput < -deadZone && turnInput < deadZone) {
			rightMotorInput = forwardInput;
			leftMotorInput = turnInput * .25;
			// Turn Backwards Left
		} else if (forwardInput < -deadZone && turnInput > -deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = -turnInput * .25;
			// Turn Backwards Left
		} else {
			rightMotorInput = forwardInput;
			leftMotorInput = forwardInput;
			// Move Backwards
		}
		RightMotor.set(-rightMotorInput);
		LeftMotor.set(leftMotorInput);
		// System.out.println(leftMotorInput + "left");
		// System.out.println(rightMotorInput + "right");
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}

}
