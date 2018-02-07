package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ArmToPos extends Command
{
	public ArmToPos()
	{
		requires(Robot.arm);
	}
	
	protected void initialize()
	{
		Robot.arm.setPosition(2000);
	}

	protected void execute()
	{
		
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
