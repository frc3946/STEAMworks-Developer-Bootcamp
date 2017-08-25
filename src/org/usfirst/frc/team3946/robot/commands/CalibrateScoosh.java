package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *  Calibrate the Scoosh.  Run the motor in reverse for at least 0.2 seconds.
 *  Continue until it stops, then stop the motor and zero the encoder.
 */
public class CalibrateScoosh extends Command {

	public CalibrateScoosh() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.scoosh);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.scoosh.reverseSlow(); // start the motor going slowly in reverse
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.scoosh.reverseSlow();  // keep the motor going slowly in reverse
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (this.timeSinceInitialized() < 0.2) { // it's been less than a fifth of a second
			return false; // too soon
		} else {
			return(Math.abs(Robot.scoosh.getSpeed()) < 1.0); // that is, speed < 1 degree/second
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.scoosh.stop();
		Robot.scoosh.setZeroAngle();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.scoosh.stop();
	}
}
