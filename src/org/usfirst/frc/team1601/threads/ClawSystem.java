package org.usfirst.frc.team1601.threads;

import org.usfirst.frc.team1601.robot.OI;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class ClawSystem extends Thread {

	Joystick leftJoystick;
	WPI_TalonSRX leftClawMotor, rightClawMotor;

	private boolean killThread = false;

	ClawSystem(Joystick leftJoystick, WPI_TalonSRX leftClawMotor, WPI_TalonSRX rightClawMotor) {
		this.leftJoystick = leftJoystick;
		this.leftClawMotor = leftClawMotor;
		this.rightClawMotor = rightClawMotor;
	}

	public void run() {
		while (!killThread) {
			if (leftJoystick.getPOV() == 0) {
				leftClawMotor.set(OI.maxClawSpeed);
				rightClawMotor.set(OI.maxClawSpeed);
			} else if (leftJoystick.getPOV() == 180) {
				leftClawMotor.set(-OI.maxClawSpeed);
				rightClawMotor.set(-OI.maxClawSpeed);
			} else {
				leftClawMotor.set(0);
				rightClawMotor.set(0);
			}
			
			//Sleep for threadSleepTime miliSecounds
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
		}
	}

}
