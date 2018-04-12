package org.usfirst.frc.team1601.threads;

import org.usfirst.frc.team1601.robot.OI;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *TODO
 *Add the following constants:
 *	ticksPerInch = 200, //placeholder value, calculate actual value
 *	autoAngleTolerance = 3, //in degrees
 * 	autoDistanceTolerance = 2;//in inches
 * 
 * Constants are accessed from an OI object in this code. Change this if necessary.
 * 
 * Make sure that adjustAngle actually reaches target angle in timely fashion
 * 
 * Functions are untested. Make sure they function as intended.
 * 	public void drive(double distanceInInches);
 * 	public void adjustAngle();
 * 	public boolean withinTargetDistance();
 * 	public boolean withinAngleTolerance();
 *	public boolean within67PercentAngleTolerance();
 */

public class Rotate extends Thread {
	WPI_TalonSRX leftFrontMotor, rightFrontMotor, middleWheelMotor;
	DifferentialDrive differentialDrive;
	Encoder leftEncoder, rightEncoder;
	AHRS ahrs;
	OI m_oi;
	
	double currentAngle, targetAngle;

	private boolean killThread = false;

	public Rotate(double angle, OI m_oi) //initializes all sensors and motors used. arguments are angle (Positive is clockwise) and OI object.
    {
    	this.leftFrontMotor = m_oi.leftFrontMotor;
    	this.rightFrontMotor = m_oi.rightFrontMotor;
    	this.middleWheelMotor = m_oi.middleWheelMotor;
    	this.differentialDrive = m_oi.differentialDrive;
    	this.ahrs = m_oi.navX;
    	this.m_oi = m_oi;
    	this.currentAngle = ahrs.getAngle();
    	//this.leftFrontMotor.setSelectedSensorPosition(0,0,10);
    	//this.rightFrontMotor.setSelectedSensorPosition(0,0,10);
    	this.targetAngle = ahrs.getAngle();
    }
	
public void run() {							//while running drive(), it runs adjustAngle() if angle is out of tolerance.
		while(!killThread) {
			try 
			{
				setTurnRate();
				if(withinAngleTolerance()) 
				{
					leftFrontMotor.set(ControlMode.PercentOutput, 0.0);
					rightFrontMotor.set(ControlMode.PercentOutput, 0.0);
					killThread = true;
				}
				Thread.sleep(OI.threadSleepTime);
			}
			catch(InterruptedException e) {};
		}
	}

	public void setTurnRate()
	{
		currentAngle = ahrs.getAngle();
		leftFrontMotor.set(ControlMode.PercentOutput, ((ahrs.getAngle()-targetAngle)*OI.autoTurnCoefficient)); //Test to see if multiplying by proportion
		rightFrontMotor.set(ControlMode.PercentOutput, ((ahrs.getAngle()-targetAngle)*OI.autoTurnCoefficient));
	}
	public void adjustAngle()	//tries to change direction closer to target angle. Must check if it turns fast enough.
	{
		leftFrontMotor.set(ControlMode.PercentOutput,((ahrs.getAngle()-targetAngle)*OI.autoTurnCoefficient)); //Test to see if multiplying by proportion
		rightFrontMotor.set(ControlMode.PercentOutput, ((ahrs.getAngle()-targetAngle)*OI.autoTurnCoefficient));
	}
	
	public boolean withinAngleTolerance()	//returns true if current angle is within autoAngleTolerance
	{
		if(Math.abs(targetAngle-ahrs.getAngle())<OI.autoAngleTolerance) return true;
		else return false;
	}
	
	public boolean within67PercentAngleTolerance() //returns true if current angle is within (.67)*autoAngleTolerance
	{
		if(Math.abs(targetAngle-ahrs.getAngle())<((2/3)*OI.autoAngleTolerance)) return true;
		else return false;
	}
	
	
}
