package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualArmControl extends Command
{

	private double armSpeed = 0.5;
	
	public ManualArmControl()
	{
		requires(Robot.arm);
	}

	protected void initialize()
	{

	}

	protected void execute()
	{
		double speed = armSpeed * Robot.oi.getXLeftJoyY();
		int position = Robot.arm.getEncoderTicks();
		
		double adjustedSpeed = 0.45 * Math.cos((position / 16384.0) * 2 * Math.PI) + Robot.oi.getXLeftJoyY();
		
		SmartDashboard.putNumber("Adjusted Speed", adjustedSpeed);
		Robot.arm.setRaw(speed);
		
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void interrupted()
	{
		end();
	}

}
