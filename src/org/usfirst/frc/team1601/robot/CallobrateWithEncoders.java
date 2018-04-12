package org.usfirst.frc.team1601.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class CallobrateWithEncoders {

	WPI_TalonSRX leftFrontMotor, rightFrontMotor;
	
	private double leftMotorsVelocity, rightMotorsVelocity;
	
	CallobrateWithEncoders(WPI_TalonSRX leftFrontMotor, WPI_TalonSRX rightFrontMotor, AHRS navx){
		this.leftFrontMotor = leftFrontMotor;
		this.rightFrontMotor = rightFrontMotor;
	}
	
	public void run() {
		leftMotorsVelocity = leftFrontMotor.getSelectedSensorVelocity(0);
		rightMotorsVelocity = rightFrontMotor.getSelectedSensorVelocity(0);
		
		if(Math.abs(leftMotorsVelocity - rightMotorsVelocity) <= .05) {
			if(leftMotorsVelocity > rightMotorsVelocity) {
				
			}
			else if(leftMotorsVelocity < rightMotorsVelocity) {
				
			}
			else {
				return;
			}
		}
	}
	
	int leftSideCalaborate() {
		return 0;
		
	}
	
	int rightSideCalaborate() {
		return 0;
		
	}
	
}
