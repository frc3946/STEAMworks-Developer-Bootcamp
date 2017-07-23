package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.Robot;
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
    private double ticksCal = 19.099; // 360 / (wheel diameter * 3.14)
    
    public Drivetrain() {
    	fLeft.changeControlMode(TalonControlMode.Follower);
    	fRight.changeControlMode(TalonControlMode.Follower);
    	setDemoDrive(false);
    	
    	bLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	bRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	
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
    
    public double getRightDistance()
    {
    	double rawData = bRight.getEncPosition();
    	double calcRightData = - rawData/ticksCal;
    	SmartDashboard.putNumber("RightDriveDistance", calcRightData);
    	return calcRightData;
    }
    	
    public double getLeftDistance()
    {
    	double rawLeftData = bLeft.getEncPosition();
    	double calcLeftData = rawLeftData/ticksCal;
    	SmartDashboard.putNumber("LeftDriveDistance", calcLeftData);
    	return calcLeftData;
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