package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.JoystickTankDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private CANTalon bLeft = new CANTalon(RobotMap.bLeftDriveTalon);
	private CANTalon bRight = new CANTalon(RobotMap.bRightDriveTalon);
	private CANTalon fLeft = new CANTalon(RobotMap.fLeftDriveTalon);
	private CANTalon fRight = new CANTalon(RobotMap.fRightDriveTalon);
	private RobotDrive robotDrive = new RobotDrive(bLeft, bRight);
	private boolean demoDrive = false;
	
	private boolean calibrated = false;
	private double codesPerInch = RobotMap.drivetrainTicksPerRev;
	
	public Drivetrain() {
		fLeft.changeControlMode(TalonControlMode.Follower);
		fRight.changeControlMode(TalonControlMode.Follower);
		setDemoDrive(false);
		
		bLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		bRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		bRight.reverseSensor(true);
		
		bLeft.configEncoderCodesPerRev(360);
		bRight.configEncoderCodesPerRev(360);
		
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
	}
	
	public void tankDrive(double speedLeft, double speedRight){
		getLeftDistance();
		getRightDistance();
		robotDrive.tankDrive(speedLeft,  speedRight);
	}
	
	public void setDemoDrive(boolean isDemoDrive) {
		demoDrive = isDemoDrive;
		if (demoDrive) {
			fLeft.set(fLeft.getDeviceID());
			fRight.set(fRight.getDeviceID());
		} else {
			fLeft.set(bLeft.getDeviceID());
			fRight.set(bRight.getDeviceID());
		}
		SmartDashboard.putBoolean("DemoDrive", demoDrive);
	}
	
	public boolean isDemoDrive() {
		return demoDrive;
	}
	
	/**
	 * Get Distance of Right wheels since last zeroEncoders()
	 * @return distance in inches
	 */
	public double getRightDistance()
	{
		int numCodes = bRight.getEncPosition();
		double inches = numCodes/codesPerInch;
		SmartDashboard.putNumber("RightDriveDistance", inches);
		return inches;
	}
	
	/**
	 * Get Distance of Left wheels since last zeroEncoders()
	 * @return distance in inches
	 */
	public double getLeftDistance()
	{
		int numCodes = bLeft.getEncPosition();
		double inches = numCodes/codesPerInch;
		SmartDashboard.putNumber("LeftDriveDistance", inches);
		return inches;
	}
	
	public void zeroEncoders()
	{
		bRight.setEncPosition(0);
		bLeft.setEncPosition(0);
		calibrated = true;
	}
	
	public boolean isCalibrated() {
		return calibrated;
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new JoystickTankDrive());
	}
}