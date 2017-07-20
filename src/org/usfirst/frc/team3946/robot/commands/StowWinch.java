package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3946.robot.Robot;

/**
 *
 */
public class StowWinch extends Command {
	public StowWinch() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.winch);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(Robot.winch.isUp()) {
			Robot.winch.setSpeed(0);
		}else {
			Robot.winch.setSpeed(0.25);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.winch.setSpeed(0);
	}
}
