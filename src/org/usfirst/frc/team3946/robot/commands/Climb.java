package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {
	boolean isRunning = true;
	boolean prevButton = true;
	
    public Climb() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if( Robot.oi.climbButton.get() && prevButton == false) {
    			isRunning = !isRunning;
    			prevButton = true;
    	}
    	if(isRunning && Robot.winch.isTouching() < 800) {
    		Robot.winch.setSpeed(1);
    	}else {
    		Robot.winch.setSpeed(0);
    	}
    	prevButton = Robot.oi.climbButton.get();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winch.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.winch.setSpeed(0);
    	isRunning = true; // Reset Variables so motor starts
    	prevButton = true; // If command is restarted
    }
}
