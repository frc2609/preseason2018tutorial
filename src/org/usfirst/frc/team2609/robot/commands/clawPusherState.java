package org.usfirst.frc.team2609.robot.commands;

import org.usfirst.frc.team2609.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import enums.ClawPusherState;

/**
 *
 */
public class clawPusherState extends Command {

    private ClawPusherState desiredState;
    
    public clawPusherState(ClawPusherState desiredState) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.desiredState = desiredState;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.clawPusher.setClawState(desiredState);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
