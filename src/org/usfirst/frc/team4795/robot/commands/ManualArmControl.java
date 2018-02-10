package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualArmControl extends Command
{

	private double armSpeed = 0.2;

	//private int lastEncoderPosition;
	
	private final int frontPos = 0;
	private final double frontSpeed = 0.3;
	
	private final int frontMidPos = -750;
	private final double frontMidSpeed = 0.15;
	
	private final int midPos = -1500;
	private final double midSpeed = 0.0;
	
	private final int backMidPos = -2250;
	private final double backMidSpeed = -0.1;
	
	private final int backPos = -3000;
	private final double backSpeed = 0.0;
	
	public ManualArmControl()
	{
		requires(Robot.arm);
	}

	protected void initialize()
	{

	}

	protected void execute()
	{
		boolean isRequestingUp = Robot.oi.XBOX_JOY.getPOV() == 0 ? true : false;
		boolean isRequestingDown = Robot.oi.XBOX_JOY.getPOV() == 180 ? true : false;
		
		double speed = armSpeed * Robot.oi.getXLeftJoyY();
		int position = Robot.arm.getEncoderTicks();
		
		double adjustedSpeed;
		if(speed > 0)
		{
			if(position > frontPos && position < frontMidPos)
				adjustedSpeed = frontSpeed;
			else if(position > frontMidPos && position < midPos)
				adjustedSpeed = frontMidSpeed;
			else if(position > midPos && position < backMidPos)
				adjustedSpeed = midSpeed;
			else if(position > backMidPos && position < backPos)
				adjustedSpeed = backMidSpeed;
			else if(position <= backPos)
				adjustedSpeed = backSpeed;
		}
		else if(speed < 0)
		{
			if(position > frontPos && position < frontMidPos)
				adjustedSpeed = backSpeed;
			else if(position > frontMidPos && position < midPos)
				adjustedSpeed = backMidSpeed;
			else if(position > midPos && position < backMidPos)
				adjustedSpeed = midSpeed;
			else if(position > backMidPos && position < backPos)
				adjustedSpeed = frontMidSpeed;
			else if(position <= backPos)
				adjustedSpeed = frontPos;
		}

		Robot.arm.setRaw(speed);
		
		/*
		 * // bang bang WITH PURPOSE encPosition = Robot.arm.getEncoderTicks();
		 * 
		 * if (isRequestingDown) hasRequestedUp = true;
		 * 
		 * if (isRequestingUp) hasRequestedDown = true;
		 * 
		 * if (hasRequestedUp && Robot.arm.getFwdLimitSwitch()) { double speed =
		 * Math.abs(Math.pow((encPosition - fwdTarget) / fwdTarget, 7)) / 3;
		 * SmartDashboard.putNumber("Speed", speed); SmartDashboard.putNumber("Target",
		 * fwdTarget); Robot.arm.setRaw(speed); } else if (hasRequestedDown &&
		 * Robot.arm.getRevLimitSwitch()) { double speed =
		 * Math.abs(Math.pow((encPosition - revTarget) / revTarget, 7)) / 3;
		 * SmartDashboard.putNumber("Speed", -speed); SmartDashboard.putNumber("Target",
		 * revTarget); Robot.arm.setRaw(-speed); } else Robot.arm.setRaw(0);
		 * 
		 * if (!Robot.arm.getFwdLimitSwitch()) hasRequestedUp = false; if
		 * (!Robot.arm.getRevLimitSwitch()) hasRequestedDown = false;
		 */
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
