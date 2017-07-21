package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleCameraView extends Command {
	boolean startState;
	
    public ToggleCameraView() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cameras);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startState = Robot.cameras.isFrontCamera();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cameras.toggleCamera();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (startState != Robot.cameras.isFrontCamera());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
