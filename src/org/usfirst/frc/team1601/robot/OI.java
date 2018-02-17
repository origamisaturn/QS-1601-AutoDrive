/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1601.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.usfirst.frc.team1601.robot.RobotMap;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//Drive Train Motors
	WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.leftFrontMotor),
					leftRearMotor = new WPI_TalonSRX(RobotMap.leftRearMotor),
					rightFrontMotor = new WPI_TalonSRX(RobotMap.rightFrontMotor),
					rightRearMotor = new WPI_TalonSRX(RobotMap.rightRearMotor),
					middleWheelMotor = new WPI_TalonSRX(RobotMap.middleWheelMotor);
	
	DifferentialDrive differentialDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
	
	//Other Motors
	WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(RobotMap.elevatorMotor),
					leftClawMotor = new WPI_TalonSRX(RobotMap.leftClawMotor),
					rightClawMotor = new WPI_TalonSRX(RobotMap.rightClawMotor);
	//Joy Sticks
	Joystick leftJoystick = new Joystick(RobotMap.leftJoystick),
				rightJoystick = new Joystick(RobotMap.rightJoystick);
	//Limit Switch
	DigitalInput topLimitSwitch = new DigitalInput(RobotMap.topLimitSwitch),
					bottomLimitSwitch = new DigitalInput(RobotMap.bottomLimitSwitch);
	
	//Variables that are important to be accessed quickly.
	public static double driveTrainMotorsMaxSpeed = .60,
							middleWheelSetSpeed = .40,
							elevatorMaxSpeed = .40,
							ticksPerInch = 200, //test this
							autoAngleTolerance = 3, //in degrees
							autoDistanceTolerance = 2;//in inches
	
	//Encoders
	//Encoder leftDriveEncoder = new Encoder(RobotMap.leftDriveEncoderA,RobotMap.leftDriveEncoderB, false, Encoder.EncodingType.k4X);
	//Encoder rightDriveEncoder = new Encoder(RobotMap.rightDriveEncoderA,RobotMap.rightDriveEncoderB, false, Encoder.EncodingType.k4X);
	
	//NavX
	AHRS ahrs = new AHRS(SPI.Port.kMXP);
	
	public static double leftMotorAdjustConstant = 1;
	
	public static long threadSleepTime = 20;	//miliSecounds
}
