package org.usfirst.frc.team4795.robot.subsystems;

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
		
		leftMotor2.follow(leftMotor1);
		rightMotor2.follow(rightMotor1);
		
		leftMotor1.setNeutralMode(NeutralMode.Brake);
		leftMotor2.setNeutralMode(NeutralMode.Brake);
		rightMotor1.setNeutralMode(NeutralMode.Brake);
		rightMotor2.setNeutralMode(NeutralMode.Brake);
	}
	
	public void set(ControlMode mode, double leftValue, double rightValue)
	{
		leftMotor1.set(mode, leftValue, rightValue);
		rightMotor1.set(mode, leftValue, rightValue);
	}
	
	public void set(ControlMode mode, double value)
	{
		leftMotor1.set(mode, value);
		rightMotor2.set(mode, value);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new TankDrive());
	}

}
