package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import enums.ClawDeployState;
import enums.ShifterState;

/**
 *
 */
public class ClawDeploy extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void setClawState(ClawDeployState desiredState){
		switch(desiredState){
		case UP:
			RobotMap.clawDeploy.set(DoubleSolenoid.Value.kReverse);
			break;
		case DOWN:
			RobotMap.clawDeploy.set(DoubleSolenoid.Value.kForward);
			break;
		default:	//UP
			RobotMap.clawDeploy.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

