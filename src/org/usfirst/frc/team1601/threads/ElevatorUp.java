package org.usfirst.frc.team1601.threads;

import org.usfirst.frc.team1601.robot.OI;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class ElevatorUp extends Thread {

	WPI_TalonSRX elevatorMotor;
	DigitalInput topLimitSwitch, bottomLimitSwitch;
	
	private boolean killThread = false;
	
	ElevatorUp(OI m_oi){
		this.elevatorMotor = elevatorMotor;
		this.topLimitSwitch = topLimitSwitch;
		this.bottomLimitSwitch = bottomLimitSwitch;
	}
	
	public void run() {
		while(!killThread) {
			while(!topLimitSwitch.get())
			{
				elevatorMotor.set(OI.elevatorMaxSpeed);
			}
			elevatorMotor.set(OI.elevatorStallSpeed);
			//Sleep for threadSleepTime miliSecounds
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
		}
	}
}
