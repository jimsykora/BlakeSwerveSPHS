package org.usfirst.frc.team2839.robot;

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
	
	//PWM-avoid duplicate values
	public static final int M_MOTOR = 1;
	public static final int S_MOTOR = 2;
	
//joystick
	public static final int OI_JOYSTICK = 1;
	
//Digital Inputs (DIO)-avoid duplicate values
	public static final int M_ENCODER_CH_A = 0;
	public static final int M_ENCODER_CH_B = 1;
	public static final int S_ENCODER_CH_A = 2;
	public static final int S_ENCODER_CH_B = 3;
	
//Analog inputs-avoid duplicate values

	public static final int STEER_POT = 0;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
