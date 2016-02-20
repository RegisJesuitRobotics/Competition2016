
package org.usfirst.frc.team3729.robot;

import javax.naming.AuthenticationNotSupportedException;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
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
	final String autonomousPath1 = "Autonomous Path High Center Goal";
	final String autonomousPath2 = "Autonomous Path High Left Goal";
	final String autonomousPath3 = "Autonomous Path Defense Driveover";
	String autoSelected;
	SendableChooser chooser;
	robotDrive drive;
	XboxController xbox;
	Shooter shooter;
	Arm arm;
	AnalogGyro gyro;
	USBCamera cam;
	NIVision.Image frame;
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
		chooser.addObject("Autonomous Path High Center Goal", autonomousPath1);
		chooser.addObject("Autonomous Path High Left Goal", autonomousPath2);
		chooser.addObject("Autonomous Path Defense Driveover", autonomousPath3);
		SmartDashboard.putData("Auto choices", chooser);
		xbox = new XboxController(0);
		gyro = new AnalogGyro(0);
		drive = new robotDrive(xbox, gyro);
		shooter = new Shooter();
		arm = new Arm();

		cam = new USBCamera("cam0");
		cam.startCapture();
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
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
		gyro.initGyro();
		gyro.calibrate();
		gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case autonomousPath1:
			if (automove == true) {
				drive.Drive(8, .5);
				drive.Stop();
				drive.Turn(90, true);
				drive.Stop();
				drive.Drive(8.5, .5);
				drive.Stop();
				drive.Turn(90, false);
				drive.Stop();
				shooter.Shootautonomous();
				automove = false;
			}
			break;

		case autonomousPath2:
			if (automove == true) {
				drive.Drive(13.5, .5);
				drive.Stop();
				drive.Turn(45, true);
				drive.Stop();
				shooter.Shootautonomous();
				automove = false;
			}
			break;

		case autonomousPath3:
			if (automove == true) {

				drive.Drive(10, .7);
				drive.Stop();
				drive.TurnAround();
				drive.Drive(2, .7);
				drive.Stop();
				automove = false;
			}
			break;

		case defaultAuto:
		default:
			if (automove == true) {

				drive.Drive(13.5, .5);
				drive.Stop();
				drive.Turn(45, true);
				drive.Stop();

				automove = false;
			}
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		cam.getImage(frame);
		CameraServer.getInstance().setImage(frame); // This sends it to the
													// SmartDashboard
		drive.arcadeDrive();
		// Listen for shoot
		if (xbox.GetLeftTrigger() > .2) {
			this.shooter.Shoot(true);
		} else {
			this.shooter.Shoot(false);
		}

		// System.out.println("RawPOV: " + xbox.GetRawPOV());
		// Listen for feed
		if (xbox.GetRightBumper() == true) {
			System.out.println("Feed 1: ");
			this.shooter.Feed(1);
		} else if (xbox.GetLeftBumper() == true) {
			System.out.println("Feed 0: ");
			this.shooter.Feed(0);
		} else {
			this.shooter.Feed(42069);
			// non 1 non 0 number stops feeder
		}
		if (xbox.GetA() == true) {
			arm.RotateForward();
			System.out.println("A");

		}
		if (xbox.GetY() == true) {
			arm.RotateBackward();
			System.out.println("Y");
		}
		if (xbox.GetStart() == true) {
			drive.TurnAround();
		}

		if (xbox.GetB() == true) {
			this.shooter.Elevate(1);
		} else if (xbox.GetX() == true) {
			this.shooter.Elevate(-1);
		} else {
			this.shooter.Elevate(0);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}

}
