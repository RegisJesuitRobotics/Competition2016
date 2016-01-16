
package org.usfirst.frc.team3729.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.hal.CanTalonSRX;
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
	public void autonomousInit() {
		autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			CanTalonSRX frontRight, frontLeft, backRight, backLeft;
			frontLeft = new CanTalonSRX(0);
			backRight = new CanTalonSRX(1);
			frontRight = new CanTalonSRX(2);
			backLeft = new CanTalonSRX(3);

			frontRight.Set(.5);
			backRight.Set(.5);
			backLeft.Set(.5);
			frontLeft.Set(.5);
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		CanTalonSRX frontRight, frontLeft, backRight, backLeft;
		frontLeft = new CanTalonSRX(0);
		backRight = new CanTalonSRX(1);
		frontRight = new CanTalonSRX(2);
		backLeft = new CanTalonSRX(3);

		XboxController xbox = new XboxController(0);

		double forwardInput = xbox.GetForwardInput();
		double turnInput = xbox.GetTurnInput();
		double leftMotorInput = 0;
		double rightMotorInput = 0;
		double deadZone = 0.2;
		double innerTurnDampener = 0.25;

		// System.out.println(forwardInput);
		// System.out.println(turnInput + "turn");

		// OLD WAY

		// if (forwardInput > deadZone && turnInput > deadZone) {
		// // Turn Forward Right
		// leftMotorInput = forwardInput;
		// rightMotorInput = turnInput * innerTurnDampener;
		// } else if (forwardInput <= deadZone && forwardInput >= -deadZone &&
		// turnInput > deadZone) {
		// // Spin Right
		// leftMotorInput = -turnInput;
		// rightMotorInput = turnInput;
		// } else if (forwardInput > deadZone && turnInput <= deadZone &&
		// turnInput >= -deadZone) {
		// // Move Forward
		// leftMotorInput = forwardInput;
		// rightMotorInput = forwardInput;
		// } else if (forwardInput > deadZone && turnInput < deadZone) {//TODO
		// Should this be negative?
		// // Turn Forwards Left
		// rightMotorInput = forwardInput;
		// leftMotorInput = -turnInput * innerTurnDampener;
		// } else if (forwardInput <= deadZone && forwardInput >= -deadZone &&
		// turnInput < -deadZone) {
		// // Spin Left
		// rightMotorInput = turnInput;
		// leftMotorInput = -turnInput;
		// } else if (forwardInput < -deadZone && turnInput < deadZone) {//TODO
		// Should this be negative?
		// // Turn Backwards Left
		// rightMotorInput = forwardInput;
		// leftMotorInput = turnInput * innerTurnDampener;
		// } else if (forwardInput < -deadZone && turnInput > -deadZone) {
		// // Turn Backwards Left
		// leftMotorInput = forwardInput;
		// rightMotorInput = -turnInput * innerTurnDampener;
		// } else {
		// // Move Backwards
		// rightMotorInput = forwardInput;
		// leftMotorInput = forwardInput;
		// }
		//
		// frontRight.Set(rightMotorInput);
		// backRight.Set(rightMotorInput);
		//
		// // Left motors get the opposite input value because they are
		// // mounted in a reverse direction.
		// backLeft.Set(-leftMotorInput);
		// frontLeft.Set(-leftMotorInput);

		// System.out.println(leftMotorInput + "left");
		// System.out.println(rightMotorInput + "right");

		// NEW WAY - Let's implement this once we have
		RobotDrive drive = new RobotDrive(deadZone, innerTurnDampener);
		
		List<CanTalonSRX> leftMotorGroup=new ArrayList<>();
		List<CanTalonSRX> rightMotorGroup=new ArrayList<>();
		
		leftMotorGroup.add(frontLeft);
		leftMotorGroup.add(backLeft);
		
		rightMotorGroup.add(frontRight);
		rightMotorGroup.add(backRight);
		
		drive.Drive(forwardInput, turnInput, leftMotorGroup, rightMotorGroup, MotorMounting.LeftMotorsReversed);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
