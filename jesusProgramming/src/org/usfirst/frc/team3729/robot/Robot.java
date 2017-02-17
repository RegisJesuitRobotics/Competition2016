
package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
   // final String defaultAuto = "Default";
   // final String customAuto = "My Auto";
   // String autoSelected;
   // SendableChooser chooser;
    Joystick joy = new Joystick(0);
    RobotDrive mydrive;
    AnalogInput leftsense, rightsense;
    double rightsensedis;
	double valuetoinches = 0.125;
	double leftsensedis = 0;
	//Talon leftmotor, rightmotor;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
       // chooser = new SendableChooser();
       // chooser.addDefault("Default Auto", defaultAuto);
        //chooser.addObject("My Auto", customAuto);
        //SmartDashboard.putData("Auto choices", chooser);
        mydrive = new RobotDrive(0,1);
        mydrive.arcadeDrive(joy);
        
      //  leftmotor = new Talon(0);
      //  rightmotor = new Talon(1);
    }

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	//autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		//System.out.println("Auto selected: " + autoSelected);
		leftsense = new AnalogInput(1);
		
		rightsense = new AnalogInput(0);
		
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	//switch(autoSelected) {
    	//case customAuto:
        //Put custom auto code here   
       //     break;
    	//case defaultAuto:
    	//default:
    	//Put default auto code here
           // break;
    
    	leftsensedis = leftsense.getVoltage()/valuetoinches;
    	System.out.println(leftsensedis);
    	}
   // }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	 mydrive.arcadeDrive(joy);
        //leftmotor.set(joy.getAxis(y));
        //leftmotor.set(joy.getAxis(-y));
    }
}