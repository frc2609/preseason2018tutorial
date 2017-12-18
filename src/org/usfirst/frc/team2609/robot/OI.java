package org.usfirst.frc.team2609.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import enums.ClawDeployState;
import enums.ClawGrabberState;
import enums.ClawPusherState;
import enums.DriveState;
import enums.ShifterState;
import org.usfirst.frc.team2609.robot.commands.*;

import org.usfirst.frc.team2609.robot.commands.drive.driveStraight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public static Joystick driverStick;
	public static JoystickButton driverButton1;
	public static JoystickButton driverButton2;
	public static JoystickButton driverButton3;
	public static JoystickButton driverButton4;
	public static JoystickButton driverButton5;
	public static JoystickButton driverButton6;
	public static JoystickButton driverButton7;
	public static JoystickButton driverButton8;
	public static JoystickButton driverButton9;
	public static JoystickButton driverButton10;
	
	public static Joystick operatorStick;
	public static JoystickButton operatorButton1;
	
	public OI(){
		driverStick = new Joystick(0);
		
		driverButton1 = new JoystickButton(driverStick, 1);
		driverButton1.whenPressed(new shifterState(ShifterState.HIGH));

		driverButton2 = new JoystickButton(driverStick, 2);
		driverButton2.whenPressed(new shifterState(ShifterState.LOW));
		
		driverButton3 = new JoystickButton(driverStick, 3);
		driverButton3.whenPressed(new clawGrabberState(ClawGrabberState.OPEN));
		
		driverButton4 = new JoystickButton(driverStick, 4);
		driverButton4.whenPressed(new clawGrabberState(ClawGrabberState.CLOSE));
		
		driverButton5 = new JoystickButton(driverStick, 5);
		driverButton5.whenPressed(new clawDeployState(ClawDeployState.UP));
		
		driverButton6 = new JoystickButton(driverStick, 6);
		driverButton6.whenPressed(new clawDeployState(ClawDeployState.DOWN));
		
		driverButton6 = new JoystickButton(driverStick, 7);
		driverButton6.whenPressed(new clawPusherState(ClawPusherState.PUSH));
		
		driverButton6 = new JoystickButton(driverStick, 8);
		driverButton6.whenPressed(new clawPusherState(ClawPusherState.RETRACT));
		
		driverButton10 = new JoystickButton(driverStick, 10);
		driverButton10.whenPressed(new driveStraight(20,0));
		
		
	}
	
	
	
	
}
