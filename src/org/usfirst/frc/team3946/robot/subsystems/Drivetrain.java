package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.JoystickTankDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon bLeft = new CANTalon(RobotMap.bLeftDriveTalon);
    public CANTalon bRight = new CANTalon(RobotMap.bRightDriveTalon);
    public CANTalon fLeft = new CANTalon(RobotMap.fLeftDriveTalon);
    public CANTalon fRight = new CANTalon(RobotMap.fRightDriveTalon);
    public RobotDrive robotDrive = new RobotDrive(bLeft, bRight);
    
    boolean calibrated = false;
    double ticksCal = 19.099; // 360 / (wheel diameter * 3.14)
    
    public Drivetrain() {
    	fLeft.changeControlMode(TalonControlMode.Follower);
    	fLeft.set(bLeft.getDeviceID());
    	fRight.changeControlMode(TalonControlMode.Follower);
    	fRight.set(bRight.getDeviceID());
    	
    	bLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	bRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	
    	bLeft.configEncoderCodesPerRev(360);
    	bRight.configEncoderCodesPerRev(360);
    	
    	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }
    
    public void Drive(double speedLeft, double speedRight){
    	robotDrive.tankDrive(speedLeft,  speedRight);
    }
    
    public double getRightDistance()
    {
    	double rawData = bRight.getEncPosition();
    	return - rawData/ticksCal;
    }
    	
    public double getLeftDistance()
    {
    	double rawLeftData = bLeft.getEncPosition();
    	return rawLeftData/ticksCal;
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