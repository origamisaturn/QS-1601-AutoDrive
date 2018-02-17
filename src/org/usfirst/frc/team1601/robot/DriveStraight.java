package org.usfirst.frc.team1601.robot;

import org.usfirst.frc.team1601.robot.Robot;

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
public class DriveStraight extends Thread {
	WPI_TalonSRX leftFrontMotor, rightFrontMotor, middleWheelMotor;
	DifferentialDrive differentialDrive;
	Encoder leftEncoder, rightEncoder;
	AHRS ahrs;
	OI m_oi;
	
	double distanceInInches, targetAngle;

	private boolean killThread = false;

	public DriveStraight(double distanceInInches, OI m_oi) //initializes all sensors and motors used. arguments are target distance and OI object.
    {
    	this.leftFrontMotor = m_oi.leftFrontMotor;
    	this.rightFrontMotor = m_oi.rightFrontMotor;
    	this.middleWheelMotor = m_oi.middleWheelMotor;
    	this.differentialDrive = m_oi.differentialDrive;
    	this.ahrs = m_oi.ahrs;
    	this.m_oi = m_oi;
    	this.distanceInInches=distanceInInches;
    	this.leftFrontMotor.setSelectedSensorPosition(0,0,10);
    	this.rightFrontMotor.setSelectedSensorPosition(0,0,10);
    	this.targetAngle = ahrs.getYaw();
    }
	
public void run() {							//while running drive(), it runs adjustAngle() if angle is out of tolerance.
		drive(distanceInInches);
		while(!killThread) {
			//LeftSide and RightSide tankDrive
			
			if(!withinAngleTolerance())
			{
				while(!within67PercentAngleTolerance())
				{
					adjustAngle();
					if(withinTargetDistance()) break;
				}
				drive(distanceInInches);
			}
			
			if(withinTargetDistance())	//kills thread when target distance is reached within autoDistanceTolerance
			{
				leftFrontMotor.set(ControlMode.PercentOutput, 0.0);
				rightFrontMotor.set(ControlMode.PercentOutput, 0.0);
		    	leftFrontMotor.setSelectedSensorPosition(0,0,10);
		    	rightFrontMotor.setSelectedSensorPosition(0,0,10);
				killThread = true;
				break;
			}
			/*
			//Sleep for threadSleepTime miliSecounds
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
			*/
		}
	}
	public void drive(double distanceInInches) //returns true if within distance tolerance. Only call in the beginning, or after adjustAngle() finishes.
	{
		double distanceTraveled = (leftFrontMotor.getSelectedSensorPosition(0)/m_oi.ticksPerInch);
		leftFrontMotor.set(ControlMode.Position, OI.ticksPerInch*distanceInInches);
		rightFrontMotor.set(ControlMode.Position, m_oi.ticksPerInch*distanceInInches);
	}
	public void adjustAngle()	//tries to change direction closer to target angle. Must check if it turns fast enough.
	{
		double originalOutputPercent = leftFrontMotor.getMotorOutputPercent();
		leftFrontMotor.set(ControlMode.PercentOutput, originalOutputPercent+((ahrs.getYaw()-targetAngle)/100)); //Test to see if multiplying by proportion
		rightFrontMotor.set(ControlMode.PercentOutput, originalOutputPercent-((ahrs.getYaw()-targetAngle)/100));
	}
	
	public boolean withinTargetDistance()	//returns true if the distance left to its target is less than autoDistanceTolerance
	{
		double distanceTraveled = (leftFrontMotor.getSelectedSensorPosition(0)/m_oi.ticksPerInch);
		if((distanceInInches-distanceTraveled) <= m_oi.autoDistanceTolerance)return true;
		else return false;
	}
	
	public boolean withinAngleTolerance()	//returns true if current angle is within autoAngleTolerance
	{
		if(Math.abs(targetAngle-ahrs.getYaw())<m_oi.autoAngleTolerance) return true;
		else return false;
	}
	
	public boolean within67PercentAngleTolerance() //returns true if current angle is within (.67)*autoAngleTolerance
	{
		if(Math.abs(targetAngle-ahrs.getYaw())<((2/3)*m_oi.autoAngleTolerance)) return true;
		else return false;
	}
	
	
}
