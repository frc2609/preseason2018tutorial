package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.OI;
import org.usfirst.frc.team2609.robot.RobotMap;
import org.usfirst.frc.team2609.robot.commands.drive.driveTeleop;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void setDriveState(DriveState desiredState,double leftPower, double rightPower){
		switch(desiredState){
		case TELEOP:
	        RobotMap.driveLeft1.set(leftPower);
	        RobotMap.driveLeft2.set(leftPower);
	        RobotMap.driveRight1.set(rightPower);
	        RobotMap.driveRight2.set(rightPower);
			break;
		case AUTON:
			RobotMap.driveLeft1.set(leftPower);
	        RobotMap.driveLeft2.set(leftPower);
	        RobotMap.driveRight1.set(rightPower);
	        RobotMap.driveRight2.set(rightPower);
		case DISABLE:
	        RobotMap.driveLeft1.set(0);
	        RobotMap.driveLeft2.set(0);
	        RobotMap.driveRight1.set(0);
	        RobotMap.driveRight2.set(0);
			new driveTeleop().start();
		default:
	        RobotMap.driveLeft1.set(0);
	        RobotMap.driveLeft2.set(0);
	        RobotMap.driveRight1.set(0);
	        RobotMap.driveRight2.set(0);
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

