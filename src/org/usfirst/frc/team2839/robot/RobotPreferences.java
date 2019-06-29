package org.usfirst.frc.team2839.robot;

import edu.wpi.first.wpilibj.Preferences;

public class RobotPreferences {

	//drive inputs
		public static double driveP() {
			return Preferences.getInstance().getDouble("driveP", 0.05); 
		}
		public static double driveI() {
			return Preferences.getInstance().getDouble("driveI", 0.00);
		}
		public static double driveD() {
			return Preferences.getInstance().getDouble("driveD", 0.005);
		}
		public static double driveF() {
			return Preferences.getInstance().getDouble("driveF", 0.0); //works best at 0.0
		}
		public static double driveMaxSpeed() {
			return Preferences.getInstance().getDouble("driveMaxSpeed", 0.8); //must be less than max steer speed so that steering can be done in both directions
		}
		public static double driveTolerance() {
			return Preferences.getInstance().getDouble("driveTolerance", 1.0);
		}
		public static double driveTargetRate() {
			return Preferences.getInstance().getDouble("driveTargetRate", 5.0);
		}
	//steer inputs
		public static double steerP() {
			return Preferences.getInstance().getDouble("steerP", -0.005); 
		}
		public static double steerI() {
			return Preferences.getInstance().getDouble("steerI", 0.004);
		}
		public static double steerD() {
			return Preferences.getInstance().getDouble("steerD", 0.001);
		}
		public static double steerTolerance() {
			return Preferences.getInstance().getDouble("steerTolerance", 2.0);
		}
		public static double steerTargetAngle() {
			return Preferences.getInstance().getDouble("steerTargetRate", 1.0);
		}
		public static double steerMaxSpeed() {
			return Preferences.getInstance().getDouble("steerMaxSpeed", 1.0);
		}
		public static double potOffset() { //used to adjust pot reading to make robot go straight
			return Preferences.getInstance().getDouble("potOffset", 45);
		}
		public static double steer2SpeedDivisor() {
			return Preferences.getInstance().getDouble("steer2SpeedDivisor", 1.5);  // was 4170//used to match speeds after steer command is completed
		}
	}
