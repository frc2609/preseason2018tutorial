package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import enums.ShifterState;

/**
 *
 */
public class Shifter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void setShifterState(ShifterState desiredState){
		switch(desiredState){
		case HIGH:
			RobotMap.driveShifter.set(DoubleSolenoid.Value.kReverse);
			break;
		case LOW:
			RobotMap.driveShifter.set(DoubleSolenoid.Value.kForward);
			break;
		case NEUTRAL:
			RobotMap.driveShifter.set(DoubleSolenoid.Value.kOff);
			break;
		default:
			RobotMap.driveShifter.set(DoubleSolenoid.Value.kOff);
			break;
		}
	}

    public void initDefaultCommand() {
		RobotMap.driveShifter.set(DoubleSolenoid.Value.kOff);
    }
}