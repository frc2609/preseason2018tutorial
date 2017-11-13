package org.usfirst.frc.team2609.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	//drivetrain
	public static CANTalon driveLeft1;
	public static CANTalon driveLeft2;
	public static CANTalon driveRight1;
	public static CANTalon driveRight2;
	public static Encoder driveEncoderLeft;
	public static Encoder driveEncoderRight;
	public static DoubleSolenoid driveShifter;
	
	//claw
	public static DoubleSolenoid clawGrab;		//open close
	public static DoubleSolenoid clawDeploy;	//up down
	public static DoubleSolenoid clawPusher;
	public static CANTalon clawRoller;
	public static DigitalInput clawOpenSensor;
	public static DigitalInput clawCloseSensor;		//without gear
	public static DigitalInput clawCloseGearSensor;	//with gear
	public static DigitalInput clawUpSensor;
	public static DigitalInput clawDownSensor;
	public static DigitalInput clawGearSensor;
	
	//climber
	public static CANTalon climber;
	
	//misc
	public static Compressor compressor;
	public static AHRS ahrs;
	
	public static void init(){
		
		//drivetrain
		driveLeft1 = new CANTalon(1);
		driveLeft2 = new CANTalon(2);
		driveRight1 = new CANTalon(3);
		driveRight2 = new CANTalon(4);
		driveShifter = new DoubleSolenoid(0,1,0);
		
		driveLeft1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		driveLeft1.configEncoderCodesPerRev(51);
		driveLeft1.setInverted(true);
		driveLeft1.reverseSensor(true);
		driveLeft2.setInverted(true);
		driveLeft2.reverseSensor(true);
		driveRight1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		driveRight1.configEncoderCodesPerRev(51);
		driveRight1.setInverted(false);
		driveRight1.reverseSensor(false);
		driveRight2.setInverted(false);
		driveRight2.reverseSensor(false);
		
		//claw
		clawGrab = new DoubleSolenoid(0,7,6);
		clawDeploy = new DoubleSolenoid(0,4,5);
		clawPusher = new DoubleSolenoid(0,2,3);
		clawRoller = new CANTalon(7);
		clawOpenSensor = new DigitalInput(5);
		clawCloseGearSensor = new DigitalInput(0);
		clawCloseSensor = new DigitalInput(4);
		clawUpSensor = new DigitalInput(1);
		clawDownSensor = new DigitalInput(2);
		
		//climber
		climber = new CANTalon(6);
		
		//gyro error handling
		try {
			ahrs = new AHRS(SPI.Port.kMXP);
			LiveWindow.addSensor("Drivetrain", "AHRS", ahrs);
		} catch (RuntimeException ex){
			DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
		}
		
		
	}
	
	
}
