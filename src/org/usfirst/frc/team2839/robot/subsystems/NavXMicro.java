package org.usfirst.frc.team2839.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI; // for navX-MXP
import edu.wpi.first.wpilibj.I2C; // for navX micro
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NavXMicro extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public  static AHRS ahrs = null;
	
	public NavXMicro(){
		try {
			ahrs = new AHRS(I2C.Port.kOnboard); //AHRS(I2C.Port.kOnboard); for navX micro;   AHRS(SPI.Port.kMXP); for navX-MXP
		} catch (RuntimeException ex) {
			DriverStation.reportError("NavX error" + ex.getMessage(), true);
		}
	}

	public double getYaw() {
		return ahrs.getYaw();
	}
	
	public void resetYaw() {
		// reset the yaw
		ahrs.reset();
		
		// wait for 1/4 seconds to allow the NavX to reset the yaw
		try {
			Thread.sleep(250);
		}
		catch (InterruptedException e) {
		}
		
		// make the reset yaw position be the zero yaw position
		ahrs.zeroYaw();
	}
	
	public double getPitch() {
		return ahrs.getPitch();
	}
	public double getRoll() {
		return ahrs.getRoll();
	}
	public boolean isCalibrating() {
		return ahrs.isCalibrating();
	}

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

