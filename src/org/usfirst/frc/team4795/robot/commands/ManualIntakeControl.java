package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ManualIntakeControl extends Command
{
	
	public ManualIntakeControl()
	{
		requires(Robot.intake);
	}
	
	protected void initialize()
	{

	}

	protected void execute()
	{
		boolean isRequestingIn = Robot.oi.XBOX_JOY.getRawButton(RobotMap.INTAKE_IN.value);
		boolean isRequestingOut = Robot.oi.XBOX_JOY.getRawButton(RobotMap.INTAKE_OUT.value);
		
		if(isRequestingIn)
			Robot.intake.variableIntake(0.5);
		else if(isRequestingOut)
			Robot.intake.variableIntake(-0.5);
		else
			Robot.intake.variableIntake(0);
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
