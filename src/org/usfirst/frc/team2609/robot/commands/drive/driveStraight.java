package org.usfirst.frc.team2609.robot.commands.drive;

import org.usfirst.frc.team2609.robot.Robot;
import org.usfirst.frc.team2609.robot.RobotMap;
import org.usfirst.frc.team2609.robot.subsystems.SimPID;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import enums.DriveState;

/**
 *
 */
public class driveStraight extends Command {

	private double leftPower;
	private double rightPower;
	private double leftTarget;
	private double rightTarget;
	SimPID driveLeft;
	SimPID driveRight;
	SimPID steering;
	
	double steeringP = 0;
	double steeringI = 0;
	double steeringD = 0;
	double steeringTarget = 0;
	double steeringMax = 0;
	double steeringEps = 0;
	double steeringOutput = 0;
	
	double driveLeftP = 0;
	double driveLeftI = 0;
	double driveLeftD = 0;
	double driveLeftTarget = 0;
	double driveLeftMax = 0;
	double driveLeftEps = 0;
	double driveLeftDR = 0;
	int driveLeftDC = 0;
	double driveLeftOutput = 0;
	
	double driveRightP = 0;
	double driveRightI = 0;
	double driveRightD = 0;
	double driveRightTarget = 0;
	double driveRightMax = 0;
	double driveRightEps = 0;
	double driveRightDR = 0;
	int driveRightDC = 0;
	double driveRightOutput = 0;

	public driveStraight(DriveState desiredState,double driveLeftTarget,double driveRightTarget,double steeringTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.drivetrain);
    	this.driveLeftTarget = driveLeftTarget;
    	this.driveRightTarget = driveRightTarget;
    	this.steeringTarget = steeringTarget;
    	
        this.driveLeft = new SimPID();
        this.driveRight = new SimPID();
        this.steering = new SimPID();
        
    	steering.resetPreviousVal();
    	driveLeft.resetPreviousVal();
    	driveRight.resetPreviousVal();
        
        steeringP = (double)SmartDashboard.getNumber("Steering P: ",0);
        steeringI = (double)SmartDashboard.getNumber("Steering I: ",0);
        steeringD = (double)SmartDashboard.getNumber("Steering D: ",0);
        steeringMax = (double)SmartDashboard.getNumber("Steering Max: ",0);
        this.steering.setConstants(steeringP, steeringI, steeringD);
        this.steering.setMaxOutput(steeringMax);
        
        driveLeftP = (double)SmartDashboard.getNumber("DriveLeft P: ",0);
        driveLeftI = (double)SmartDashboard.getNumber("DriveLeft I: ",0);
        driveLeftD = (double)SmartDashboard.getNumber("DriveLeft D: ",0);
        driveLeftMax = (double)SmartDashboard.getNumber("DriveLeft Max: ",0);
        driveLeftDC = (int)SmartDashboard.getNumber("DriveLeft DC: ",0);
        driveLeftDR = SmartDashboard.getNumber("DriveLeft DR: ",0);
        driveLeftEps = SmartDashboard.getNumber("DriveLeft Eps: ",0);
        
        driveRightP = (double)SmartDashboard.getNumber("DriveRight P: ",0);
        driveRightI = (double)SmartDashboard.getNumber("DriveRight I: ",0);
        driveRightD = (double)SmartDashboard.getNumber("DriveRight D: ",0);
        driveRightMax = (double)SmartDashboard.getNumber("DriveRight Max: ",0);
        driveRightDC = (int)SmartDashboard.getNumber("DriveRight DC: ",0);
        driveRightDR = SmartDashboard.getNumber("DriveRight DR: ",0);
        driveRightEps = SmartDashboard.getNumber("DriveRight Eps: ",0);
        
        this.driveLeft.setConstants(driveLeftP, driveLeftI, driveLeftD);
        this.driveLeft.setMaxOutput(driveLeftMax);
        this.driveLeft.setDoneRange(driveLeftDR);
        this.driveLeft.setMinDoneCycles(driveLeftDC);
        this.driveLeft.setErrorEpsilon(driveLeftEps);
        
        this.driveRight.setConstants(driveRightP, driveRightI, driveRightD);
        this.driveRight.setMaxOutput(driveRightMax);
        this.driveRight.setDoneRange(driveRightDR);
        this.driveRight.setMinDoneCycles(driveRightDC);
        this.driveRight.setErrorEpsilon(driveRightEps);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveLeft1.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveRight1.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.driveLeft1.setVoltageRampRate(24);
    	RobotMap.driveLeft2.setVoltageRampRate(24);
    	RobotMap.driveRight1.setVoltageRampRate(24);
    	RobotMap.driveRight2.setVoltageRampRate(24);
        this.steering.setDesiredValue(steeringTarget);
        this.driveLeft.setDesiredValue(leftTarget);
        this.driveRight.setDesiredValue(rightTarget);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	steeringOutput = -steering.calcPID(RobotMap.ahrs.getYaw());
    	driveLeftOutput = driveLeft.calcPID(RobotMap.driveLeft1.getPosition());
    	driveRightOutput = driveRight.calcPID(RobotMap.driveRight1.getPosition());
    	
    	leftPower = driveLeftOutput - steeringOutput;
    	rightPower = driveRightOutput + steeringOutput;
    	Robot.drivetrain.setDriveState(DriveState.AUTON,leftPower,rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return driveLeft.isDone()&&driveRight.isDone();
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
