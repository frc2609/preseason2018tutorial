package org.usfirst.frc.team2609.robot.commands.drive;

import org.usfirst.frc.team2609.robot.commands.clawState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import enums.ClawState;

/**
 *
 */
public class driveCurvePath extends CommandGroup {

    public driveCurvePath() {
    	addSequential(new driveStraightSpecial(67,67,0));
    	addSequential(new driveTurn(0.5,90));
    	addSequential(new driveStraight(49,49,90));
    	addSequential(new clawState(ClawState.OPEN));
    	addSequential(new clawState(ClawState.PUSH));
    	addSequential(new driveStraightSpecial(-67,-67,90));
    	addSequential(new driveTurn(0.5,0));
    	addSequential(new driveStraight(-49,-49,0));
    	
    }
}
