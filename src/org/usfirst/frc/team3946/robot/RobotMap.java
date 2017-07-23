package org.usfirst.frc.team3946.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
		
	// Joysticks
	public static int leftJoystick = 0; //Drive Station
	public static int RightJoystick = 1; //Driver Station
	
	// Cameras
	public static int frontCameraPort = 0; //USB Port
	public static int rearCameraPort = 1; //USB Port
	
	// Drivetrain
	public static int fRightDriveTalon = 1; //CAN
	public static int fLeftDriveTalon = 2; //CAN
	public static int bRightDriveTalon = 3; //CAN
	public static int bLeftDriveTalon = 4; //CAN
	
	// Scoosh
	public static int scooshMotorPort=1; //PWM Talon SR
	public static int gearSensorA=3; //DIO 
	public static int gearSensorB=4; //DIO
	public static int gearSensorC=5; //DIO

	public static int scooshEncA=6; // DIO
	public static int scooshEncB=7; // DIO
	
	// Winch
	public static int winchMotorPort = 0; //PWM Talon SR
	public static int winchLimitSwitchPort = 0; //DIO
	public static int winchFingertips = 1; //AIn
	
	// Settings
	public static double scooshTicksPerRev = 7.0*60.0; // quadrature cycles - 7 on motor, 60 for gearbox
	public static double scooshBackAngle = 0.0;  // Place to wait for gears
	// TODO: These next two need to be calibrated!
	public static double scooshHoldAngle = 30.0; // Place to hold gears when detected
	public static double scooshDownAngle = 100.0; // Place to put scoosh to hang gears 

}
