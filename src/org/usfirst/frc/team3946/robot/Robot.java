package org.usfirst.frc.team3946.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3946.robot.commands.AutonomousDrive;
import org.usfirst.frc.team3946.robot.commands.CalibrateDrivetrain;
import org.usfirst.frc.team3946.robot.commands.CalibrateScoosh;

import org.usfirst.frc.team3946.robot.subsystems.Scoosh;
import org.usfirst.frc.team3946.robot.subsystems.Winch;
import org.usfirst.frc.team3946.robot.subsystems.Cameras;
import org.usfirst.frc.team3946.robot.subsystems.Drivetrain;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final Cameras cameras = new Cameras();
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Scoosh scoosh = new Scoosh();
	public static final Winch winch = new Winch();
	
	public static OI oi;

	Command autonomousCommand;
	Command calibrateSchooshCommand;
	Command calibrateDrivetrainCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//Init Cameras here because of
		//weirdness with NetworkTables
		UsbCamera frontCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.frontCameraPort);
		UsbCamera rearCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.rearCameraPort);
		cameras.handoffCameras(frontCamera, rearCamera);

		oi = new OI();

		chooser.addObject("Auto Drive", new AutonomousDrive());
		SmartDashboard.putData("Auto mode", chooser);
		
		SmartDashboard.putData(cameras);
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(scoosh);
		SmartDashboard.putData(winch);
		
		calibrateSchooshCommand = new CalibrateScoosh();
		calibrateDrivetrainCommand = new CalibrateDrivetrain();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
		
		if(!scoosh.isCalibrated()) {
			calibrateSchooshCommand.start();
		}
		if(!drivetrain.isCalibrated()) {
			calibrateSchooshCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		if(!scoosh.isCalibrated()) {
			calibrateSchooshCommand.start();
		}
		if(!drivetrain.isCalibrated()) {
			calibrateSchooshCommand.start();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
