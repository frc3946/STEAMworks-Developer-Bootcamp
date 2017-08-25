package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3946.robot.Robot;
import org.usfirst.frc.team3946.robot.RobotMap;

/**
 * LoadHoldGear - default scoosh command. 
 * It puts the scoosh in the most inboard position when no gear is detected
 * It puts the scoosh in the upright position when a gear is detected.
 */
public class LoadHoldGear extends Command {

	public LoadHoldGear() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.scoosh);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double angle=Robot.scoosh.getAngle();
		if (angle>RobotMap.scooshHoldAngle+10.0) { // behind here, will run at slow speeds
			Robot.scoosh.reverse();
		} else {
			double target = Robot.scoosh.haveGear() ? RobotMap.scooshHoldAngle : RobotMap.scooshBackAngle;
			if (angle-target > 2) {
				Robot.scoosh.reverseSlow();
			} else if (angle-target < -2) {
				Robot.scoosh.forwardSlow();
			} else {
				Robot.scoosh.stop();
			}
			
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;   // Never ends, until interrupted by another command
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
