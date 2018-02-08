package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualArmControl extends Command
{

	public double armSpeed = 0.3;

	private int encPosition;
	private final int fwdTarget = -4000;
	private final int revTarget = 1;
	private boolean hasRequestedUp;
	private boolean hasRequestedDown;

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

		// Variable Control
		/*
		 * if (isRequestingUp && !isRequestingDown) { Robot.arm.setRaw(armSpeed);
		 * lastEncoderPosition = Robot.arm.getEncoderTicks(); } else if (!isRequestingUp
		 * && isRequestingDown) { Robot.arm.setRaw(-armSpeed); lastEncoderPosition =
		 * Robot.arm.getEncoderTicks(); } else { Robot.arm.setPosition(800); }
		 * SmartDashboard.putNumber("Last Encoder Position", lastEncoderPosition);
		 */

		// bang bang WITH PURPOSE
		encPosition = Robot.arm.getEncoderTicks();

		if (isRequestingDown)
			hasRequestedUp = true;

		if (isRequestingUp)
			hasRequestedDown = true;

		if (hasRequestedUp && Robot.arm.getFwdLimitSwitch())
		{
			double speed = Math.abs(Math.pow((encPosition - fwdTarget) / fwdTarget, 7)) / 3;
			SmartDashboard.putNumber("Speed", speed);
			SmartDashboard.putNumber("Target", fwdTarget);
			Robot.arm.setRaw(speed);
		} else if (hasRequestedDown && Robot.arm.getRevLimitSwitch())
		{
			double speed = Math.abs(Math.pow((encPosition - revTarget) / revTarget, 7)) / 3;
			SmartDashboard.putNumber("Speed", -speed);
			SmartDashboard.putNumber("Target", revTarget);
			Robot.arm.setRaw(-speed);
		} else
			Robot.arm.setRaw(0);

		if (!Robot.arm.getFwdLimitSwitch())
			hasRequestedUp = false;
		if (!Robot.arm.getRevLimitSwitch())
			hasRequestedDown = false;
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
