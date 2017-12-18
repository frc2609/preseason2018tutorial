package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import enums.ClawPusherState;

/**
 *
 */
public class ClawPusher extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void setClawState(ClawPusherState desiredState){
		switch(desiredState){
		case PUSH:
			RobotMap.clawPusher.set(DoubleSolenoid.Value.kReverse);
			break;
		case RETRACT:
			RobotMap.clawPusher.set(DoubleSolenoid.Value.kForward);
			break;
		default:	//RETRACT
			RobotMap.clawPusher.set(DoubleSolenoid.Value.kForward);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

