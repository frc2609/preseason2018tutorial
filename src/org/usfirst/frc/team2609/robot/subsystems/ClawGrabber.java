package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import enums.ClawGrabberState;
import enums.ShifterState;

/**
 *
 */
public class ClawGrabber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void setClawState(ClawGrabberState desiredState){
		switch(desiredState){
		case OPEN:
			RobotMap.clawGrab.set(DoubleSolenoid.Value.kForward);
			break;
		case CLOSE:
			RobotMap.clawGrab.set(DoubleSolenoid.Value.kReverse);
			break;
		default:	//CLOSE
			RobotMap.clawDeploy.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

