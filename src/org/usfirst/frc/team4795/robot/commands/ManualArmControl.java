package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualArmControl extends Command
{

	private final double armSpeed = 0.3;
	private int lastEncoderPosition;

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

		if (isRequestingUp && !isRequestingDown)
		{
			Robot.arm.setRaw(armSpeed);
			lastEncoderPosition = Robot.arm.getEncoderTicks();
		} else if (!isRequestingUp && isRequestingDown)
		{
			Robot.arm.setRaw(-armSpeed);
			lastEncoderPosition = Robot.arm.getEncoderTicks();
		} else
		{
			Robot.arm.setPosition(800);
		}
		SmartDashboard.putNumber("Last Encoder Position", lastEncoderPosition);

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
