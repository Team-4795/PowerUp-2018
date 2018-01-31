package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.command.Command;

public class ManualArmControl extends Command
{
	
	private final double armSpeed = 0.3;
	
	protected void initialize()
	{
		requires(Robot.arm);
	}

	protected void execute()
	{
		boolean isRequestingUp = Robot.oi.XBOX_JOY.getPOV() == 0 ? true : false;
		boolean isRequestingDown = Robot.oi.XBOX_JOY.getPOV() == 180 ? true : false;

		if (isRequestingUp && !isRequestingDown)
		{
			Robot.arm.setRaw(armSpeed);
		} else if (!isRequestingUp && !isRequestingDown)
		{
			Robot.arm.setRaw(-armSpeed);
		} else
		{
			Robot.arm.setRaw(0);
		}
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
