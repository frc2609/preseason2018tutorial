package org.usfirst.frc.team2609.robot.commands.drive;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;
import org.usfirst.frc.team2609.robot.subsystems.SimPID;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;
import enums.ShifterState;

/**
 *
 */
public class driveStraightSpecialwrong extends Command {

	private double leftPower;
	private double rightPower;
	private double driveLeftTarget;
	private double driveRightTarget;
	private double drivePower;
	SimPID steering;
	
	double steeringP;
	double steeringI;
	double steeringD;
	double steeringTarget;
	double steeringMax;
	double steeringEps;
	double steeringOutput;
	
	public driveStraightSpecialwrong(double drivePower,double driveLeftTarget,double driveRightTarget,double steeringTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drivetrain);
    	this.steeringTarget = steeringTarget;
    	this.driveLeftTarget = driveLeftTarget;
    	this.driveRightTarget = driveRightTarget;
    	this.drivePower = drivePower;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.steering = new SimPID();
        
    	steering.resetPreviousVal();
        this.steering.setDesiredValue(steeringTarget);
        
        steeringP = (double)SmartDashboard.getNumber("Steering P: ",0);
        steeringI = (double)SmartDashboard.getNumber("Steering I: ",0);
        steeringD = (double)SmartDashboard.getNumber("Steering D: ",0);
        steeringMax = (double)SmartDashboard.getNumber("Steering Max: ",0);
        this.steering.setConstants(steeringP, steeringI, steeringD);
        this.steering.setMaxOutput(steeringMax);
        
    	RobotMap.driveLeft1.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveLeft2.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveRight1.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveRight2.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveLeft1.setVoltageRampRate(24);
    	RobotMap.driveLeft2.setVoltageRampRate(24);
    	RobotMap.driveRight1.setVoltageRampRate(24);
    	RobotMap.driveRight2.setVoltageRampRate(24);
    	
    	Robot.shifter.setShifterState(ShifterState.LOW);
    	Robot.drivetrain.resetDriveEncoders();
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	steeringOutput = -steering.calcPID(RobotMap.ahrs.getYaw());
    	
    	leftPower = drivePower - steeringOutput;
    	rightPower = drivePower + steeringOutput;
    	Robot.drivetrain.setDriveState(DriveState.AUTON,leftPower,rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((RobotMap.driveLeft1.getPosition()>(driveLeftTarget-1))&&(RobotMap.driveRight1.getPosition()>(driveRightTarget-1)));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setDriveState(DriveState.DISABLE,0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setDriveState(DriveState.DISABLE,0,0);
    }
}
