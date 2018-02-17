package org.usfirst.frc.team1601.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

public class ElevatorSystem extends Thread{

	Joystick rightJoystick;
	WPI_TalonSRX elevatorMotor;
	DigitalInput topLimitSwitch, bottomLimitSwitch;
	
	private boolean killThread = false;
	
	ElevatorSystem(Joystick rightJoystick, WPI_TalonSRX elevatorMotor, DigitalInput topLimitSwitch, DigitalInput bottomLimitSwitch){
		this.rightJoystick = rightJoystick;
		this.elevatorMotor = elevatorMotor;
		this.topLimitSwitch = topLimitSwitch;
		this.bottomLimitSwitch = bottomLimitSwitch;
	}
	
	public void run() {
		while(!killThread) {
			//Check if limit switches are pressed
			if(topLimitSwitch.get()) {
				 double output = Math.max(-OI.elevatorMaxSpeed, 0);
			}
			
			if(bottomLimitSwitch.get()) {
				double output = Math.min(OI.elevatorMaxSpeed, 0);
			}
			else {
				
			}
			
			if(rightJoystick.getPOV() == 0) {
				elevatorMotor.set(OI.elevatorMaxSpeed);
			}
			else if(rightJoystick.getPOV() == 180) {
				elevatorMotor.set(-OI.elevatorMaxSpeed);
			}
			else {
				
			}
		}
	}
}
