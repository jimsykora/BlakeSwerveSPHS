package org.usfirst.frc.team2839.robot.commands;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SteerStart extends Command {
	double angle = 0.0;
	
    public SteerStart() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.sMotor);
    	requires (Robot.sMotorPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//Robot.sMotorPID.setSetpoint(angle);
    	Robot.sMotorPID.setRawTolerance(10.0);//tolerance in degrees
    	Robot.sMotorPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	angle=Robot.oi.joystick.getDirectionDegrees();// if using the handle on a joystick
    	Robot.sMotorPID.setSetpoint(angle);
    	Robot.sMotor.setAngle(Robot.sMotorPID.getOutput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.sMotorPID.disable();	//will temporarily disable PID loop if eg over ridden by a button location command
    	//Robot.sMotor.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}