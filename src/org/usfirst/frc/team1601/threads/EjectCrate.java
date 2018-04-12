package org.usfirst.frc.team1601.threads;

import org.usfirst.frc.team1601.robot.OI;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class EjectCrate extends Thread{

	Timer time;
	WPI_TalonSRX leftClawMotor, rightClawMotor;
	boolean setUp = false;
	
	private boolean killThread = false;
	
	EjectCrate(OI m_oi){
			this.leftClawMotor = m_oi.leftClawMotor;
			this.rightClawMotor = m_oi.rightClawMotor;
	}
	
	public void run() {
		while(!killThread) {
			//Sleep for threadSleepTime miliSecounds
			if (setUp == false)
			{
			leftClawMotor.set(-OI.maxClawSpeed);
			rightClawMotor.set(-OI.maxClawSpeed); //have no idea whether this ejects or not
			time.start(); //multiplied by 1000 to convert seconds to milliseconds
			setUp = true;
			}
			else if(time.get() >= OI.autoEjectTime)
				{
					leftClawMotor.set(0);
					rightClawMotor.set(0);
					killThread = true;
				}
			try {
				Thread.sleep(OI.threadSleepTime);
			}catch(InterruptedException e) {};
		}
	}
}
