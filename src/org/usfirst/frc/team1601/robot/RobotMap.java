/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1601.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//Drive Train Motors
	public static int leftFrontMotor = 1,
						leftRearMotor = 2,
						rightFrontMotor = 3,
						rightRearMotor = 4,
						middleWheelMotor = 5;
	//Other Motors
	public static int elevatorMotor = 6,
						leftClawMotor = 7,
						rightClawMotor = 8;
	//Joy Sticks
	public static int leftJoystick = 0,
						rightJoystick = 1;
	//Limit Switches
	public static int topLimitSwitch = 1,
						bottomLimitSwitch = 2;
}
