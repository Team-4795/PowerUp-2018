package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivebase extends Subsystem
{
	TalonSRX leftMotor1;
	TalonSRX leftMotor2;
	TalonSRX rightMotor1;
	TalonSRX rightMotor2;
	
	public Drivebase()
	{
		leftMotor1 = new TalonSRX(RobotMap.LEFT_MOTOR_1.value);
		leftMotor2 = new TalonSRX(RobotMap.LEFT_MOTOR_2.value);
		rightMotor1 = new TalonSRX(RobotMap.RIGHT_MOTOR_1.value);
		rightMotor2 = new TalonSRX(RobotMap.RIGHT_MOTOR_2.value);
		
		Robot.initTalon(leftMotor1);
		Robot.initTalon(leftMotor2);
		Robot.initTalon(rightMotor1);
		Robot.initTalon(rightMotor2);
		
		leftMotor2.follow(leftMotor1);
		rightMotor2.follow(rightMotor1);
		
		rightMotor1.setInverted(true);
		rightMotor2.setInverted(true);
	}
	
	public void set(ControlMode mode, double leftValue, double rightValue)
	{
		System.out.printf("Left Joystick: %.2f, Right Joystick: %.2f \n", leftValue, rightValue);
		leftMotor1.set(mode, leftValue);
		rightMotor1.set(mode, rightValue);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new TankDrive());
	}

}
