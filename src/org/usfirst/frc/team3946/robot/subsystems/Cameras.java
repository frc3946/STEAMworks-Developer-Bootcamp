package org.usfirst.frc.team3946.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


/**
 *
 */
public class Cameras extends Subsystem {
	private UsbCamera frontCamera;
	private UsbCamera rearCamera;
	
	private boolean initialized = false;
	private boolean frontCam = true;
	
	public Cameras() {
		
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public void handoffCameras(UsbCamera front, UsbCamera rear) {
		frontCamera = front;
		rearCamera = rear;
		
		frontCamera.setFPS(30);
		frontCamera.setResolution(640, 400);
		
		rearCamera.setFPS(30);
		rearCamera.setResolution(640, 400);
		
		initialized = true;
	}
	
	public boolean isFrontCamera() {
		return frontCam;
	}
	
	public void toggleCamera() {
		//TODO Find Faster Way to Change Cameras
		//Current Method takes about 3 seconds.
		if(initialized) {
			if(frontCam) {
				NetworkTable.getTable("").putString("CameraSelection", rearCamera.getName());
				frontCam = false;
			} else {
				NetworkTable.getTable("").putString("CameraSelection", frontCamera.getName());
				frontCam = true;
			}
		}
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}
