package org.usfirst.frc.team2609.robot.commands;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import enums.DriveState;

/**
 *
 */
public class driveState extends Command {

    public static DriveState desiredState;
	private double leftPower;
	private double rightPower;

	public driveState(DriveState desiredState,double leftPower,double rightPower) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveState.desiredState = desiredState;
    	this.leftPower = leftPower;
    	this.rightPower = rightPower;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	switch(driveState.desiredState){
    	case TELEOP:
        	RobotMap.driveLeft1.changeControlMode(TalonControlMode.PercentVbus);
        	RobotMap.driveLeft2.changeControlMode(TalonControlMode.PercentVbus);
        	RobotMap.driveRight1.changeControlMode(TalonControlMode.PercentVbus);
        	RobotMap.driveRight2.changeControlMode(TalonControlMode.PercentVbus);
        	break;
    	case AUTON:
        	RobotMap.driveLeft1.changeControlMode(TalonControlMode.PercentVbus);
        	RobotMap.driveLeft2.changeControlMode(TalonControlMode.PercentVbus);
        	RobotMap.driveRight1.changeControlMode(TalonControlMode.PercentVbus);
        	RobotMap.driveRight2.changeControlMode(TalonControlMode.PercentVbus);
        	break;    		
    	case DISABLE:
        	RobotMap.driveLeft1.changeControlMode(TalonControlMode.PercentVbus);
        	RobotMap.driveLeft2.changeControlMode(TalonControlMode.PercentVbus);
        	RobotMap.driveRight1.changeControlMode(TalonControlMode.PercentVbus);
        	RobotMap.driveRight2.changeControlMode(TalonControlMode.PercentVbus);
    		break;
		default:
			RobotMap.driveLeft1.changeControlMode(TalonControlMode.PercentVbus);
			RobotMap.driveLeft2.changeControlMode(TalonControlMode.PercentVbus);
			RobotMap.driveRight1.changeControlMode(TalonControlMode.PercentVbus);
			RobotMap.driveRight2.changeControlMode(TalonControlMode.PercentVbus);
			break;
    	}
    	
    	Robot.drivetrain.setDriveState(desiredState,leftPower,rightPower);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.setDriveState(desiredState,leftPower,rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	switch(driveState.desiredState){
    	case TELEOP:
            return false;
    	case AUTON:
    		return false;
    	case DISABLE:
    		return true;
    	default:
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
