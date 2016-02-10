
package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

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
	robotDrive drive;
	XboxController xbox;
	Shooter shooter;
	Arm arm;
	// USBCamera cam;
	CANTalon RightMotor1, LeftMotor1, RightMotor2, LeftMotor2;
	boolean automove;

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
		xbox = new XboxController(0);
		drive = new robotDrive(xbox);
		shooter = new Shooter();
		arm = new Arm();
		// cam = new USBCamera();

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
		RightMotor1 = new CANTalon(2);
		RightMotor2 = new CANTalon(3);
		LeftMotor1 = new CANTalon(1);
		LeftMotor2 = new CANTalon(4);
		automove = true;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:

			break;
		case defaultAuto:
		default:
			if (automove == true) {
				RightMotor1.set(-.5);
				LeftMotor1.set(.5);
				RightMotor2.set(-.5);
				LeftMotor2.set(.5);
				Timer.delay(1.48);
				RightMotor1.set(.5);
				LeftMotor1.set(-.5);
				RightMotor2.set(.5);
				LeftMotor2.set(-.5);
				Timer.delay(.2);
				RightMotor1.set(0);
				LeftMotor1.set(0);
				RightMotor2.set(0);
				LeftMotor2.set(0);
				RightMotor1.set(.5);
				LeftMotor1.set(.5);
				RightMotor2.set(.5);
				LeftMotor2.set(.5);
				Timer.delay(.41);
				RightMotor1.set(0);
				LeftMotor1.set(0);
				RightMotor2.set(0);
				LeftMotor2.set(0);
				Timer.delay(.3);
				RightMotor1.set(-.5);
				LeftMotor1.set(.5);
				RightMotor2.set(-.5);
				LeftMotor2.set(.5);
				Timer.delay(1.8);
				RightMotor1.set(.5);
				LeftMotor1.set(-.5);
				RightMotor2.set(.5);
				LeftMotor2.set(-.5);
				Timer.delay(.2);
				RightMotor1.set(0);
				LeftMotor1.set(0);
				RightMotor2.set(0);
				LeftMotor2.set(0);
				Timer.delay(.31);
				RightMotor1.set(-.25);
				LeftMotor1.set(-.25);
				RightMotor2.set(-.25);
				LeftMotor2.set(-.25);
				Timer.delay(1.05);
				RightMotor1.set(0);
				LeftMotor1.set(0);
				RightMotor2.set(0);
				LeftMotor2.set(0);
				automove = false;
				// 20 in. in front of low bar
				// 2 seconds of spin = 180
				// 8 1/2 in. from inside corner of low bar
			}
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		drive.arcadeDrive();
		// Listen for shoot
		if (xbox.GetLeftBumper() == true) {
			this.shooter.Shoot(true);
		} else {
			this.shooter.Shoot(false);
		}
		// Listen for feed
		if (xbox.getPOV() == 1) {
			this.shooter.Feed(1);
		} else if (xbox.getPOV() == 0) {
			this.shooter.Feed(0);
		} else {
			this.shooter.Feed(42069);
			// non 1 non 0 number stops feeder
		}
		if (xbox.GetA() == true) {
			arm.RotateForward();

		}
		if (xbox.GetY() == true) {
			arm.RotateBackward();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}

}
