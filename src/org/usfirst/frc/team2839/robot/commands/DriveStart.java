package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStart extends Command {
	double speed = 0.0;  //for speed PID loop
	double angle = 0.0;  //for position PID loop

    public DriveStart(double rps) {  //rps is rev/sec
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	speed = rps;

    	requires(Robot.mMotorPID);
    	requires(Robot.mMotor);
    	requires(Robot.sMotorPID);
    	requires(Robot.sMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	Robot.mMotorPID.setSetpoint(speed);
    	Robot.mMotorPID.setRawTolerance(1.0);  //(RobotPreferences.driveTolerance());
    	Robot.mMotorPID.enable();
    	Robot.sMotorPID.setSetpoint(angle);
    	Robot.sMotorPID.setRawTolerance(1.0);  //(RobotPreferences.steerTolerance());
    	Robot.sMotorPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//speed = (-Robot.oi.joystick.getThrottle()+1)/2*200.0/5.5; 
    	speed = Math.pow(Robot.oi.joystick.getMagnitude(), 3)*200.0/5.5*1.414; // *(encoder counts/rev)/(gear reduction)*1.414 (max magnitude is at corners of joystick Magnitude is cubed for better control at low speeds
    	angle=Robot.oi.joystick.getDirectionDegrees(); 
    	
    	Robot.mMotorPID.setSetpoint(speed);
    	Robot.mMotor.setSpeed(Robot.mMotorPID.getOutput());

    	Robot.sMotorPID.setSetpoint(angle);//.setSetpoint(90);
    	Robot.sMotor.setAngle(Robot.sMotorPID.getOutput());
//
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mMotorPID.disable();
    	Robot.mMotor.setSpeed(0);
    	Robot.sMotorPID.disable();
    	Robot.sMotor.setAngle(0);  ////
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
