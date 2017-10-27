package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import enums.ClawState;
import enums.ShifterState;

/**
 *
 */
public class Claw extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void setClawState(ClawState desiredState){
		switch(desiredState){
		case UP:
			RobotMap.clawDeploy.set(DoubleSolenoid.Value.kReverse);
			break;
		case DOWN:
			RobotMap.clawDeploy.set(DoubleSolenoid.Value.kForward);
			break;
		case OPEN:
			RobotMap.clawGrab.set(DoubleSolenoid.Value.kForward);
			break;
		case CLOSE:
			RobotMap.clawGrab.set(DoubleSolenoid.Value.kReverse);
			break;
		case PUSH:
			RobotMap.clawPusher.set(DoubleSolenoid.Value.kReverse);
			break;
		case RETRACT:
			RobotMap.clawPusher.set(DoubleSolenoid.Value.kForward);
			break;
		default:	//UPCLOSE
			RobotMap.clawGrab.set(DoubleSolenoid.Value.kReverse);
			RobotMap.clawDeploy.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

