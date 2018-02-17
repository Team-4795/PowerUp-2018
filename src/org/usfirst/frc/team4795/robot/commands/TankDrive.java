package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command
{
	boolean fastMode = false;
	
	public TankDrive()
	{
		requires(Robot.drivebase);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		if(Robot.oi.MAIN_CONTROLLER.getRawAxis(3) > 0.05)
		{
			fastMode = true;
		}
		else
		{
			fastMode = false;
		}
		
		
		double throttle = !fastMode ?  -1 : -0.5;
		Robot.drivebase.set(ControlMode.PercentOutput, Robot.oi.getMainLeftJoyY() * throttle,
				Robot.oi.getMainRightJoyY() * throttle);
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
