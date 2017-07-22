package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.StowWinch;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Winch extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Talon winchMotor = new Talon(RobotMap.winchMotorPort);
	private DigitalInput winchLimitSwitch = new DigitalInput(RobotMap.winchLimitSwitchPort);
	private AnalogInput winchFingertips = new AnalogInput(RobotMap.winchFingertips);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new StowWinch());
	}
	
	public void setSpeed(double newSpeed) {
		winchMotor.set(newSpeed);
	}
	
	public boolean isUp() {
		boolean state = !winchLimitSwitch.get();
		SmartDashboard.putBoolean("winchLimitSwitch", state);
		return state;
	}
	
	public int isTouching() {
		int state = winchFingertips.getValue();
		SmartDashboard.putNumber("winchFingertips", state);
		return state;
	}
	
}
