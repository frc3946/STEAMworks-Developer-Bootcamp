package org.usfirst.frc.team3946.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3946.robot.commands.Climb;
import org.usfirst.frc.team3946.robot.commands.HangGear;
import org.usfirst.frc.team3946.robot.commands.ToggleCameraView;
import org.usfirst.frc.team3946.robot.commands.ToggleDriveMode;

import edu.wpi.first.wpilibj.Joystick;


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
	public Joystick leftstick = new Joystick(RobotMap.leftJoystick);
	public Joystick rightstick = new Joystick(RobotMap.RightJoystick);
	
	Button toggleCameraButton = new JoystickButton(leftstick, 3);

	Button hangGearButton = new JoystickButton(rightstick, 1);

	public Button climbButton = new JoystickButton(rightstick, 2);
	Button cancelClimbButton = new JoystickButton(rightstick, 10);
	Command climbCommand;
	
	Button demoDriveButton = new JoystickButton(leftstick, 10);
	
	public OI() {
		hangGearButton.whenPressed(new HangGear());

		toggleCameraButton.whenPressed(new ToggleCameraView());
		
		climbCommand = new Climb();
		climbButton.whenPressed(climbCommand);
		cancelClimbButton.cancelWhenPressed(climbCommand);
		
		demoDriveButton.whenPressed(new ToggleDriveMode());
	}
	
}
