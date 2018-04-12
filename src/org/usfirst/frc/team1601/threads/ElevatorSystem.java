package org.usfirst.frc.team1601.threads;

import org.usfirst.frc.team1601.robot.OI;

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
		
			if(rightJoystick.getPOV() == 0) {	
				if(topLimitSwitch.get()) {	
					elevatorMotor.set(Math.min(-OI.elevatorMaxSpeed, 0));	
				}
				else {
					elevatorMotor.set(OI.elevatorMaxSpeed);
				}
			}
			else if(rightJoystick.getPOV() == 180) {
				if(bottomLimitSwitch.get()) {
					elevatorMotor.set(Math.max(OI.elevatorMaxSpeed, 0));
				}
				elevatorMotor.set(-OI.elevatorMaxSpeed);
			}
			else {
				elevatorMotor.set(OI.elevatorStallSpeed);
			}
			
			//Sleep for threadSleepTime miliSecounds
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
		}
	}
}
