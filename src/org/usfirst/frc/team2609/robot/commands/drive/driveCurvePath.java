package org.usfirst.frc.team2609.robot.commands.drive;

import org.usfirst.frc.team2609.robot.commands.TimerDelay;
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
    	addSequential(new driveEncoderReset());
    	addSequential(new driveTurn(0.5,45));
    	addSequential(new driveEncoderReset());
    	addSequential(new driveStraightSpecial(24,45));
//    	addSequential(new clawGrabberState(ClawGrabberState.OPEN));
//    	addSequential(new clawPusherState(ClawPusherState.PUSH));
    	addSequential(new driveEncoderReset());
    	addSequential(new TimerDelay(0.2));
    	addSequential(new driveStraightSpecialReverse(-24,45));
    	addSequential(new driveEncoderReset());
    	addSequential(new driveTurnReverse(0.5,0));
    	addSequential(new driveEncoderReset());
    	addSequential(new driveStraightSpecialReverse(-48,0));
    	
    }
}
