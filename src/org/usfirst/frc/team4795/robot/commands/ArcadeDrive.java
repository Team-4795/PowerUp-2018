package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command
{

	boolean fastMode = false;

	public ArcadeDrive()
	{
		requires(Robot.drivebase);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		

		double throttle = !(Robot.oi.XBOX_JOY.getRawAxis(3) > 0.05) ? -1 : -0.5;

		double JoyXValue = Robot.oi.getXRightJoyX();
		double JoyYValue = Robot.oi.getXRightJoyY();

		double outputLeft = JoyYValue;
		double outputRight = JoyYValue;

		double difference = JoyXValue / 2;

		double correction = JoyYValue != 0 ? Math.abs(JoyYValue) / JoyYValue : -1;

		outputLeft += difference * correction;
		outputRight -= difference * correction;

		/*
		 * outputLeft = Math.max(outputLeft, -1); outputRight = Math.max(outputRight,
		 * -1); outputLeft = Math.min(1, outputRight); outputRight = Math.min(1,
		 * outputRight);
		 */

		if (outputLeft > 0)
			//green
			Robot.ledStripGreen.set(false);
		else if (outputLeft < 0)
		{
			//yellow
			Robot.ledStripGreen.set(false);
			Robot.ledStripRed.set(false);
		} else
		{
			Robot.ledStripGreen.set(true);
			Robot.ledStripRed.set(true);
			Robot.ledStripBlue.set(true);
		}

		Robot.drivebase.set(ControlMode.PercentOutput, outputLeft * throttle, outputRight * throttle);

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
