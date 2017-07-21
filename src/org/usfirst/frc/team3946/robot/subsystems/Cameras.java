package org.usfirst.frc.team3946.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3946.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;

/**
 *
 */
public class Cameras extends Subsystem {
	private UsbCamera frontCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.frontCameraPort);
	private UsbCamera rearCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.rearCameraPort);
	
	// Attaching Cameras to CvSink objects will keep the streams open
	// even when not actively being viewed
	private CvSink frontCvSink = new CvSink("frontCamCv");
	private CvSink rearCvSink = new CvSink("rearCamCv");
	
	private VideoSink server = CameraServer.getInstance().getServer();
	
	private boolean frontCam = true;
	
	public Cameras() {
		frontCamera.setFPS(60);
		frontCamera.setResolution(640, 400);
		frontCvSink.setSource(frontCamera);
		frontCvSink.setEnabled(true);
		
		rearCamera.setFPS(60);
		rearCamera.setResolution(640, 400);
		frontCvSink.setSource(rearCamera);
		rearCvSink.setEnabled(true);
		
		server.setSource(frontCamera);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean isFrontCamera() {
    	return frontCam;
    }
    
    public void toggleCamera() {
    	if(frontCam) {
    		server.setSource(rearCamera);
    		frontCam = false;
    	} else {
    		server.setSource(frontCamera);
    		frontCam = true;
    	}
    }
}
