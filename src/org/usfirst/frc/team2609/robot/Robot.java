
package org.usfirst.frc.team2609.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.ClawDeployState;
import enums.ShifterState;

import org.usfirst.frc.team2609.robot.commands.clawDeployState;
import org.usfirst.frc.team2609.robot.commands.drive.driveCurvePath;
import org.usfirst.frc.team2609.robot.commands.drive.driveStraight;
import org.usfirst.frc.team2609.robot.commands.drive.driveTeleop;
import org.usfirst.frc.team2609.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	//subsystem initialization
	public static Shifter shifter;
	public static ClawDeploy clawDeploy;
	public static ClawGrabber clawGrabber;
	public static ClawPusher clawPusher;
	public static Climber climber;
	public static Drivetrain drivetrain;
	
	public static OI oi;
	

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();			//imports all of RobotMap, required to not crash on start up
		
		shifter = new Shifter();
		clawDeploy = new ClawDeploy();
		clawGrabber = new ClawGrabber();
		clawPusher = new ClawPusher();
		climber = new Climber();
		drivetrain = new Drivetrain();
		
		oi = new OI();
		
		//SmartDashboard Values initialization
		//Left drive PID
		SmartDashboard.putNumber("DriveLeft P: ", 0.02);
    	SmartDashboard.putNumber("DriveLeft I: ", 0.0005);
    	SmartDashboard.putNumber("DriveLeft D: ", 0.0);
    	SmartDashboard.putNumber("DriveLeft Max: ", 0.8);
    	SmartDashboard.putNumber("DriveLeft Eps: ", 1.0);
    	SmartDashboard.putNumber("DriveLeft DR: ", 1);
    	SmartDashboard.putNumber("DriveLeft DC: ", 5);
    	//Right drive PID
		SmartDashboard.putNumber("DriveRight P: ", 0.02);
    	SmartDashboard.putNumber("DriveRight I: ", 0.0005);
    	SmartDashboard.putNumber("DriveRight D: ", 0.0);
    	SmartDashboard.putNumber("DriveRight Max: ", 0.8);
    	SmartDashboard.putNumber("DriveRight Eps: ", 1.0);
    	SmartDashboard.putNumber("DriveRight DR: ", 1);
    	SmartDashboard.putNumber("DriveRight DC: ", 5);
    	//Steering heading correction PID
		SmartDashboard.putNumber("Steering P: ", 0.03);
    	SmartDashboard.putNumber("Steering I: ", 0.0001);
    	SmartDashboard.putNumber("Steering D: ", 0.0);
    	SmartDashboard.putNumber("Steering Max: ", 0.2);

		//sensor values
		SmartDashboard.putNumber("Gyro getYaw", RobotMap.ahrs.getYaw());
    	SmartDashboard.putNumber("driveLeft1.getPosition()", RobotMap.driveLeft1.getPosition());
    	SmartDashboard.putNumber("driveRight1.getPosition()", RobotMap.driveRight1.getPosition());
		
		chooser.addDefault("drive straight 10", new driveStraight(10,10,0));
        chooser.addObject("drive straight 50", new driveStraight(50,50,0));
        chooser.addObject("drive straight 100", new driveStraight(100,100,0));
        chooser.addObject("drive curve", new driveCurvePath());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		shifter.setShifterState(ShifterState.HIGH); //makes it easier to push the robot when its disabled
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		//sensor values
		SmartDashboard.putNumber("Gyro getYaw", RobotMap.ahrs.getYaw());
    	SmartDashboard.putNumber("driveLeft1.getPosition()", RobotMap.driveLeft1.getPosition());
    	SmartDashboard.putNumber("driveRight1.getPosition()", RobotMap.driveRight1.getPosition());
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Robot.drivetrain.resetDriveEncoders();
		Robot.drivetrain.resetGyro();
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
    	
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		//sensor values
		SmartDashboard.putNumber("Gyro getYaw", RobotMap.ahrs.getYaw());
    	SmartDashboard.putNumber("driveLeft1.getPosition()", RobotMap.driveLeft1.getPosition());
    	SmartDashboard.putNumber("driveRight1.getPosition()", RobotMap.driveRight1.getPosition());
    	SmartDashboard.putNumber("driveLeft1.getOutputVoltage()", RobotMap.driveLeft1.getOutputVoltage());
    	SmartDashboard.putNumber("driveRight1.getOutputVoltage()", RobotMap.driveRight1.getOutputVoltage());
		
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Robot.drivetrain.resetDriveEncoders();
		Robot.drivetrain.resetGyro();
		new driveTeleop().start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		//sensor values
		SmartDashboard.putNumber("Gyro getYaw", RobotMap.ahrs.getYaw());
    	SmartDashboard.putNumber("driveLeft1.getPosition()", RobotMap.driveLeft1.getPosition());
    	SmartDashboard.putNumber("driveRight1.getPosition()", RobotMap.driveRight1.getPosition());
    	SmartDashboard.putNumber("driveLeft1.getOutputVoltage()", RobotMap.driveLeft1.getOutputVoltage());
    	SmartDashboard.putNumber("driveRight1.getOutputVoltage()", RobotMap.driveRight1.getOutputVoltage());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
