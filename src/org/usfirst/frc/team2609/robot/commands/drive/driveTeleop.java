package org.usfirst.frc.team2609.robot.commands.drive;

import org.usfirst.frc.team2609.robot.OI;
import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import enums.DriveState;

/**
 *
 */
public class driveTeleop extends Command {

	private double leftPower;
	private double rightPower;

	public driveTeleop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        RobotMap.driveLeft1.changeControlMode(TalonControlMode.PercentVbus);
        RobotMap.driveLeft2.changeControlMode(TalonControlMode.PercentVbus);
        RobotMap.driveRight1.changeControlMode(TalonControlMode.PercentVbus);
        RobotMap.driveRight2.changeControlMode(TalonControlMode.PercentVbus);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double X = OI.driverStick.getRawAxis(1);
        double Y = -OI.driverStick.getRawAxis(0);
        double leftPower = 0;
        double rightPower = 0;
        double deadzone = 0.15;
        
        if(Math.abs(X)<deadzone && Math.abs(Y)<deadzone){
        	leftPower=0;
        	rightPower =0;
        }
        else{
        	leftPower	= Y+(X*(1-Math.abs(.2*Y)));
        	rightPower	= Y-(X*(1-Math.abs(.2*Y)));
        }

    	Robot.drivetrain.setDriveState(DriveState.TELEOP,leftPower,rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
