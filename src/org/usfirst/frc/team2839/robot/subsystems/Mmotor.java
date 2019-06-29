package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.commands.DriveStart;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.Talon;   ////modified or added

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Mmotor extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//private WPI_TalonSRX Mmotor = null;  //for Peanut base which uses CAN   ////modified or added
	private Talon Mmotor = null;   ////modified or added
	
	Encoder mEncoder = null;
	
	public Mmotor(){//added this constructor
		
		//Mmotor = new WPI_TalonSRX(RobotMap.M_MOTOR);    //for CAN   ////modified or added
		Mmotor = new Talon(RobotMap.M_MOTOR);//must create a constant in RobotMap   ////modified or added
		
		//mMotor.enableBrakeMode(true);//
		
		//Mmotor.setNeutralMode(NeutralMode.Brake);    //for CAN   ////modified or added
		Mmotor.setSafetyEnabled(false);//to allow a motor to run continuously without continuous repeated commands   ////modified or added
		
		Mmotor.setSafetyEnabled(false);//to allow a motor to run continuously without continuous repeated commands
		mEncoder = new Encoder(RobotMap.M_ENCODER_CH_A,RobotMap.M_ENCODER_CH_B);
	}

	public void setSpeed(double speed){
		Mmotor.set(speed);    //for CAN
	}
	public void resetEncoderCount(){
		mEncoder.reset();
	}
	public double getEncoderRate(){//this method returns something so we define it as double, if void it would not return anything
		return mEncoder.getRate();
	}
	public double getEncoderRPS(){//rps is getERate/(100 counts/rev) //getERate is counts/sec
		
		double cpr = 100.0*5.5; //cpr of shooter wheel (encoder counts/rev)*(gear reduction)
		return mEncoder.getRate()/cpr;  //returns rps (rev/sec) of shooter wheel
	}
    public void initDefaultCommand() {
        double rps = 0.0;
		// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveStart(rps));
    }
}

