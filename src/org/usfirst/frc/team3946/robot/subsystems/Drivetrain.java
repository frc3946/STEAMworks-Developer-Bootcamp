package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.JoystickTankDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon fRight = new CANTalon(RobotMap.fRightDriveTalon);
	private CANTalon fLeft = new CANTalon(RobotMap.fLeftDriveTalon);
	private CANTalon bRight = new CANTalon(RobotMap.bRightDriveTalon);
	private CANTalon bLeft = new CANTalon(RobotMap.bLeftDriveTalon);
	private RobotDrive robotDrive = new RobotDrive(fLeft, bLeft, fRight, bRight);
    
    public Drivetrain() {
    	robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
    	robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    	robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    }
    
    public void Drive(double speedLeft, double speedRight){
    	robotDrive.tankDrive(speedLeft,  speedRight);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new JoystickTankDrive());
    }
}