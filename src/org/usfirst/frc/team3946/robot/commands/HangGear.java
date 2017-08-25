package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3946.robot.Robot;
import org.usfirst.frc.team3946.robot.RobotMap;

/**
 *  Hang a gear.  Drive the scoosh forward until fully extended, or 1 second elapses.
 *  The default scoosh command LoadHoldGear() will return the scoosh to the upright position.
 */
public class HangGear extends Command {

	public HangGear() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.scoosh);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(1.0);
		Robot.scoosh.forward();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.scoosh.forward();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (isTimedOut()) {
			return true;
		} else {
			return (Robot.scoosh.getAngle()>=RobotMap.scooshDownAngle);
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.scoosh.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.scoosh.stop();
	}
}
