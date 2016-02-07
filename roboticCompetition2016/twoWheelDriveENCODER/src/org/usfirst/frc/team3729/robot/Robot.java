
package org.usfirst.frc.team3729.robot;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    
	
	Input input;
    Encoder leftENC;
    Encoder rightENC;
    int encoderCountLEFT;
    Talon mcLeft;
    Talon mcRight;
    robotDrive drive;
    Servo camServo;
    stringPotentiometer pot;
    
    public Robot() {
        mcLeft = new Talon(0);
        mcRight = new Talon(1);
        drive = new robotDrive(mcLeft, mcRight);
        pot = new stringPotentiometer();
        //drive.setExpiration(0.1);
        input = new Input();
        leftENC = new Encoder(0,1,false,Encoder.EncodingType.k2X);
        leftENC.setMaxPeriod(1);//maximum time in which the device is still considered moving
        leftENC.setMinRate(10);//min rate b/4 considered stopped
        leftENC.setDistancePerPulse(5);//sets scale factor between pulse and distance
        leftENC.setReverseDirection(true);//depending on mounting of motor
        leftENC.setSamplesToAverage(7);//averaging reduces jittery movement
        camServo = new Servo(2);
        rightENC= new Encoder(2,3,false,Encoder.EncodingType.k2X);
        
        //parameters: (ports)a channel, b channel, reverse direction, encoding type
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    @SuppressWarnings("deprecation")
	public void autonomous() {
    	
    	leftENC.reset();//needed b/c encoder starts counting as soon as it is created
    	encoderCountLEFT = leftENC.get();
    	System.out.println("Encoder distance "+leftENC.getDistance());
    	System.out.println("Encoder period "+leftENC.getPeriod());
    	System.out.println("Encoder rate "+leftENC.getRate());
    	
    	
    	
    	
        drive.setSafetyEnabled(false);
        drive.tankDrive(-0.5, 0.5);	// drive forwards half speed
        Timer.delay(2.0);		//    for 2 seconds
        drive.tankDrive(0.0, 0.0);	// stop robot
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
       drive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	drive.tankDrive();
        	Timer.delay(0.005);		// wait for a motor update time
        
        	System.out.println(pot.ai.getVoltage());
        	
            
            camServo.set (input.left.getPOV());
            //System.out.println(stickL.getPOV());
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
