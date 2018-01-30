package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistance extends Command
{
	double Feet;

	double P;
	double I;
	double D;
	double F;

	boolean isFinished = false;

	public DriveDistance(double feet)
	{
		requires(Robot.drivebase);
		Feet = feet;
	}

	protected void initialize()
	{
		System.out.println("Drive Distance Init");
	}

	protected void execute()
	{
		isFinished = Robot.drivebase.driveFeet(Feet);

		SmartDashboard.putNumber("Right Encoder", Robot.drivebase.getrightEncoder());
		SmartDashboard.putNumber("Left Encoder", Robot.drivebase.getleftEncoder());
	}

	protected boolean isFinished()
	{
		return isFinished;
	}
}
