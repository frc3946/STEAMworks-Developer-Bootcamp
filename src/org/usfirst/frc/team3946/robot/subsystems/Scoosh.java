package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.LoadHoldGear;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  The scoosh subsystem.  
 *  Methods:
 *  void setZeroAngle() synchronizes the encoder.  Call when the scoosh is all the way back.
 *  double getAngle()   gets the encoder angle in degrees forward from backstop
 *  void forward()      runs the scoosh motor forward (outboard) at full speed
 *  void forwardSlow()  runs the scoosh motor forward at slow speed 
 *  void reverse()      runs the scoosh motor inboard at full speed
 *  void reverseSlow()  runs the scoosh motor inboard at slow speed
 *  void stop()         stops the scoosh motor
 */
public class Scoosh extends Subsystem {
	Talon scooshMotor = new Talon(RobotMap.scooshMotorPort);
	Encoder scooshEncoder = new Encoder(RobotMap.scooshEncA,RobotMap.scooshEncB,true);
    DigitalInput lightA = new DigitalInput(RobotMap.gearSensorA);
    DigitalInput lightB = new DigitalInput(RobotMap.gearSensorB);
    DigitalInput lightC = new DigitalInput(RobotMap.gearSensorC);
    private boolean calibrated = false;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public boolean isCalibrated() {
    	return calibrated;
    }
    
	public void setZeroAngle () {
		calibrated = true;
	    scooshEncoder.setDistancePerPulse(360.0/RobotMap.scooshTicksPerRev); // degrees per pulse
	    scooshEncoder.reset();
	}
	public double getAngle() {
		double angle = scooshEncoder.getDistance();
		SmartDashboard.putNumber("ScooshAngle", angle);
		return angle;
	}
	public double getSpeed() {
		return scooshEncoder.getRate();
	}
	public boolean haveGear() {
		boolean a = !lightA.get();
		boolean b = !lightB.get();
		boolean c = !lightC.get();
		SmartDashboard.putBoolean("GearLoaded", (a||b||c));
		return (a || b || c); 
	}
	public void forward() {
		scooshMotor.setSpeed(1.0);
	}
	public void forwardSlow() {
		scooshMotor.setSpeed(0.5);
	}
	public void reverse() {
		scooshMotor.setSpeed(-1.0);
	}
	public void reverseSlow() {
		scooshMotor.setSpeed(-0.5);
	}
	public void stop() {
		scooshMotor.setSpeed(0.0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new LoadHoldGear());
    }
}

