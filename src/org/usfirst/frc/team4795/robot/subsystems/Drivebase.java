package org.usfirst.frc.team4795.robot.subsystems;

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
		leftMotor1 = new TalonSRX();
		leftMotor2 = new TalonSRX();
		rightMotor1 = new TalonSRX();
		rightMotor2 = new TalonSRX();
	}
	
	@Override
	protected void initDefaultCommand()
	{

	}

}
