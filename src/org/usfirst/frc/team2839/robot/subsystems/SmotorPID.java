package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class SmotorPID extends PIDSubsystem {
	double output = 0.0;
	boolean outputValid = false;
//	int targetRate = 0;  //for speed PID loop, remove later if/when PID loop gets tuned properly. its used to delay turning off PID loop while in motion
	double targetAngle = RobotPreferences.steerTargetAngle();  //for position PID loop, remove later if/when PID loop gets tuned properly. its used to delay turning off PID loop while in motion
	double tolerance = RobotPreferences.steerTolerance();
	
    // Initialize your subsystem here
    public SmotorPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("SmotorPID",0,0,0);  //for speed PID loop
    	this.setSetpoint(0.0);
    }

    public void enable()  {
    	this.getPIDController().setPID(RobotPreferences.steerP(), RobotPreferences.steerI(), RobotPreferences.steerD());
    	double maxSpeed = RobotPreferences.steerMaxSpeed(); //set to <1.0 to limit max motor speed
    	this.setOutputRange(-maxSpeed, maxSpeed);
    	this.setInputRange(-180.0, 180.0);  //for position PID loop
    	getPIDController().setContinuous(true); //allows PID loop to handle abrupt change from 5 to 0 volts as encoder turns
    	outputValid = false;
    	super.enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	//return Robot.sMotor.getEncoderRPS(); //for speed PID loop
    	return Robot.sMotor.getPotAngle();  //for position PID loop 
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	this.output = output;
    	outputValid = true;
    }
    public double getOutput() {
    	if(this.getPIDController().isEnabled() == false || outputValid == false) { // == meams "is equal to", || means "or"
    		return 0.0;
    	}
    	//return output;
    	return output - RobotPreferences.steer2SpeedDivisor()/10;  // just a test to verify that a constant could be added to feedback
    	//return output + (Robot.oi.joystick.getThrottle()-1)/2 /RobotPreferences.steer2SpeedDivisor();
    	//return output - Robot.mMotor.getEncoderRate()/3333/RobotPreferences.steer2SpeedDivisor();  //to keep motors spinning at same rate after steer angle error is zero

    }
    public void setRawTolerance(double tolerance) {
    	this.tolerance = tolerance;
    }
   /* public boolean onRawTargrt() {
    	if(Math.abs(getPIDController().getSetpoint() - Robot.rrDrive.getEncoderRPS()) < tolerance) {
    		targetRate = targetRate +1;
    	}
    	else {
    		targetRate = 0;
    	}
    	return (targetRate >= RobotPreferences.driveTargetRate());
    }*/
}