
package org.usfirst.frc.team5941.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
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
    
    VictorSP rightSide = new VictorSP(1), leftSide = new VictorSP(0);
    
    //RobotDrive chassis = new RobotDrive(rightSide, leftSide);
    
    Joystick xbox = new Joystick(0), solo = new Joystick(1); 
double leftYAxis = xbox.getRawAxis(4), rightYAxis = xbox.getRawAxis(5); 
	
VictorSP pneumatic = new VictorSP(4);



DoubleSolenoid sol = new DoubleSolenoid(1, 0);

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
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
			// chassis.arcadeDrive(solo); //<---- If the code for tank driving
												// doesn't work
			leftYAxis = xbox.getRawAxis(1);
			rightYAxis = xbox.getRawAxis(5);
			//chassis.tankDrive(leftYAxis, rightYAxis);
			leftSide.set(-(leftYAxis/3.0));
			rightSide.set((rightYAxis/3.0));
			
			
			
			if (solo.getRawAxis(1) > 0.3) {
				pneumatic.set(solo.getRawAxis(1));
			} else if (solo.getRawAxis(1) < -0.3) {
				pneumatic.set(solo.getRawAxis(1));
			} else {
				pneumatic.set(0.0);
			}
			
			if (solo.getRawButton(1) && solo.getRawButton(7)) {
				sol.set(DoubleSolenoid.Value.kReverse);

			} else if (solo.getRawButton(8)) {
				sol.set(DoubleSolenoid.Value.kForward);
				//leftSide.set(0);
				//rightSide.set(0);
			} else {
				sol.set(DoubleSolenoid.Value.kOff);
			}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
