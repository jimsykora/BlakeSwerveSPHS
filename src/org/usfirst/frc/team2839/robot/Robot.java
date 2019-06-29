
package org.usfirst.frc.team2839.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2839.robot.commands.AutonomousCommand;
import org.usfirst.frc.team2839.robot.subsystems.Mmotor;
import org.usfirst.frc.team2839.robot.subsystems.MmotorPID;
import org.usfirst.frc.team2839.robot.subsystems.NavXMicro;
import org.usfirst.frc.team2839.robot.subsystems.Smotor;
import org.usfirst.frc.team2839.robot.subsystems.SmotorPID;
import org.usfirst.frc.team2839.robot.subsystems.Telemetry;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static Mmotor mMotor;
	public static MmotorPID mMotorPID;
	public static Smotor sMotor;
	public static SmotorPID sMotorPID;
	public static  NavXMicro navXMicro;
	
	public static Telemetry telemetry; //Smart Dashboard & OI must be at the end
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		mMotor = new Mmotor();
		mMotorPID = new MmotorPID();
		sMotor = new Smotor();
		sMotorPID = new SmotorPID();
		navXMicro = new NavXMicro();


		SmartDashboard.putData("Auto mode", chooser);
		telemetry = new Telemetry();
		oi = new OI(); //Smart Dashboard & OI must be at the end
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
		telemetry.update();  //makes SmartDashboard live when in disabled mode
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
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		telemetry.update();  //makes SmartDashboard live when in disabled mode
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
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		telemetry.update();  //makes SmartDashboard live when in disabled mode
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
