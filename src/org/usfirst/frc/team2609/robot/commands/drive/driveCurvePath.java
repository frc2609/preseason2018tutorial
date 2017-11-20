package org.usfirst.frc.team2609.robot.commands.drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class driveCurvePath extends CommandGroup {

    public driveCurvePath() {
    	addSequential(new driveStraightSpecialwrong(0.5,20,20,0));
    	addSequential(new driveTurn(0.5,90));
    	addSequential(new driveStraightSpecialwrong(0.5,20,20,0));
    	
    }
}
