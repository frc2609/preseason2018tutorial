package org.usfirst.frc.team2609.robot.subsystems;

import org.usfirst.frc.team2609.robot.OI;
import org.usfirst.frc.team2609.robot.RobotMap;

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
	
	public void setDriveState(DriveState desiredState){
		switch(desiredState){
		case TELEOP:
			double X = OI.driverStick.getRawAxis(1);
	        double Y = -OI.driverStick.getRawAxis(0);
	        double left = 0;
	        double right = 0;
	        double deadzone = 0.15;
	        
	        if(Math.abs(X)<deadzone && Math.abs(Y)<deadzone){
	            left=0;
	            right =0;
	        }
	        else{
	        	left	= Y+(X*(1-Math.abs(.2*Y)));
	            right	= Y-(X*(1-Math.abs(.2*Y)));
	        }

	        RobotMap.driveLeft1.set(left);
	        RobotMap.driveLeft2.set(left);
	        RobotMap.driveRight1.set(right);
	        RobotMap.driveRight2.set(right);
			break;
		case AUTON:
			RobotMap.driveLeft1.set(100);
	        RobotMap.driveLeft2.set(100);
	        RobotMap.driveRight1.set(100);
	        RobotMap.driveRight2.set(100);
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

