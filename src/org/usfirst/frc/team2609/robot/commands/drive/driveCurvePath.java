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
    	addSequential(new driveStraightSpecial(67,67,0));
    	addSequential(new driveTurn(0.5,90));
    	addSequential(new driveStraight(49,49,90));
    	addSequential(new clawGrabberState(ClawGrabberState.OPEN));
    	addSequential(new clawPusherState(ClawPusherState.PUSH));
    	addSequential(new driveStraightSpecial(-67,-67,90));
    	addSequential(new driveTurn(0.5,0));
    	addSequential(new driveStraight(-49,-49,0));
    	
    }
}
