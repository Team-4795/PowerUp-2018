package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LightLED extends Command
{
	
	protected void initialize()
	{
		requires(Robot.ledSystem);
	}

	protected void execute()
	{
		if(Robot.intake.intaking)
		{
			Robot.ledSystem.ledStripRed.set(true);
			Robot.ledSystem.ledStripBlue.set(true);
		}
		else if(Robot.intake.outtaking)
		{
			Robot.ledSystem.ledStripRed.set(true);
		}
		else if(Robot.drivebase.isDrivingForward)
		{
			Robot.ledSystem.ledStripGreen.set(true);
		}
		else if(Robot.drivebase.isDrivingBackwords)
		{
			Robot.ledSystem.ledStripGreen.set(true);
			Robot.ledSystem.ledStripBlue.set(true);
		}
		else
		{
			Robot.ledSystem.ledStripRed.set(false);
			Robot.ledSystem.ledStripGreen.set(false);
			Robot.ledSystem.ledStripBlue.set(false);
		}
	}
	
	@Override
	protected boolean isFinished()
	{
		return false;
	}

}
