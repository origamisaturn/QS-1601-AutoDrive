package org.usfirst.frc.team1601.threads;

import org.usfirst.frc.team1601.robot.OI;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Thread{

	Joystick leftJoystick, rightJoystick;
	WPI_TalonSRX leftFrontMotor, rightFrontMotor, middleWheelMotor;
	DifferentialDrive differentialDrive;
	
	private boolean killThread = false;
	
	DriveTrain(Joystick leftJoystick, Joystick rightJoystick, WPI_TalonSRX leftFrontMotor, WPI_TalonSRX rightFrontMotor, WPI_TalonSRX middleWheelMotor, DifferentialDrive differentialDrive) {
		this.leftJoystick = leftJoystick;
		this.rightJoystick = rightJoystick;
		this.leftFrontMotor = leftFrontMotor;
		this.rightFrontMotor = rightFrontMotor;
		this.middleWheelMotor = middleWheelMotor;
		this.differentialDrive = differentialDrive;
	}
	
	public void run() {
		while(!killThread) {
			//LeftSide and RightSide tankDrive
			if(Math.abs(leftJoystick.getRawAxis(1) - rightJoystick.getRawAxis(1)) <= .05) {
				differentialDrive.tankDrive(leftJoystick.getRawAxis(1) * OI.leftMotorAdjustConstant * OI.driveTrainMotorsMaxSpeed, rightJoystick.getRawAxis(1) * OI.driveTrainMotorsMaxSpeed * OI.driveTrainMotorsMaxSpeed, true); //if statement is correct, what follows doesn't seem right
			}
			else {
				differentialDrive.tankDrive(leftJoystick.getRawAxis(1) * OI.driveTrainMotorsMaxSpeed, rightJoystick.getRawAxis(1) * OI.driveTrainMotorsMaxSpeed, true);
			}
			//MiddleWheel Drive
			if(leftJoystick.getTrigger()) {
				middleWheelMotor.set(OI.middleWheelSetSpeed);
			}
			else if(rightJoystick.getTrigger()) {
				middleWheelMotor.set(-OI.middleWheelSetSpeed);
			}
			else {
				middleWheelMotor.set(0);
			}
			//Sleep for threadSleepTime miliSecounds
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
		}
	}
	
}
