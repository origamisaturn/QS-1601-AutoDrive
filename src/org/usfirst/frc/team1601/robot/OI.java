/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1601.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//Drive Train Motors
	public WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.leftFrontMotor),
					leftRearMotor = new WPI_TalonSRX(RobotMap.leftRearMotor),
					rightFrontMotor = new WPI_TalonSRX(RobotMap.rightFrontMotor),
					rightRearMotor = new WPI_TalonSRX(RobotMap.rightRearMotor),
					middleWheelMotor = new WPI_TalonSRX(RobotMap.middleWheelMotor);
	//Other Motors
	public WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(RobotMap.elevatorMotor),
					leftClawMotor = new WPI_TalonSRX(RobotMap.leftClawMotor),
					rightClawMotor = new WPI_TalonSRX(RobotMap.rightClawMotor);
	//Joy Sticks
	public Joystick leftJoystick = new Joystick(RobotMap.leftJoystick),
				rightJoystick = new Joystick(RobotMap.rightJoystick);
	//Limit Switch
	public DigitalInput topLimitSwitch = new DigitalInput(RobotMap.topLimitSwitch),
					bottomLimitSwitch = new DigitalInput(RobotMap.bottomLimitSwitch);
	//DifferentialDrive
	public DifferentialDrive differentialDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
	//NavX
	public AHRS navX = new AHRS(SPI.Port.kMXP);
	
	//Variables that are important to be accessed quickly.
	public static double driveTrainMotorsMaxSpeed = .60,
							middleWheelSetSpeed = .40,
							elevatorMaxSpeed = .40,
							elevatorStallSpeed = 0;
	
	public static double maxClawSpeed = .30;
	
	public static double leftMotorAdjustConstant = 1;
	
	public static long threadSleepTime = 20;	//miliSecounds
	
	//Variables used for autonomous movement.
	public static int ticksPerInch = 200; //placeholder value, calculate actual value
    public static double autoAngleTolerance = 3; //in degrees
   	public static double autoDistanceTolerance = 2;//in inches
   	public static double autoTurnCoefficient = 0.03;
}
