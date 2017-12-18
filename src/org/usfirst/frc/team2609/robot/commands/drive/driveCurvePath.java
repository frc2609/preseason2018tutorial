package org.usfirst.frc.team2609.robot.commands.drive;

import org.usfirst.frc.team2609.robot.commands.clawGrabberState;
import org.usfirst.frc.team2609.robot.commands.clawPusherState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.ClawGrabberState;
import enums.ClawPusherState;

/**
 *
 */
public class driveCurvePath extends CommandGroup {

    public driveCurvePath() {
    	addSequential(new driveStraightSpecial(48,0));
    	addSequential(new driveTurn(0.5,45));
    	addSequential(new driveStraightSpecial(24,45));
    	addSequential(new clawGrabberState(ClawGrabberState.OPEN));
    	addSequential(new clawPusherState(ClawPusherState.PUSH));
    	addSequential(new driveStraightSpecial(-24,0));
    	addSequential(new driveTurn(0.5,0));
    	addSequential(new driveStraightSpecial(-48,0));
    	
    }
}
