package org.usfirst.frc.team4795.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CVJesus extends CommandGroup
{
	public CVJesus()
	{
		
		addSequential(new DriveDistance(5));
		addSequential(new TurnToAngle(-90));
		addSequential(new DriveDistance(5));
		addSequential(new TurnToAngle(90));
		addSequential(new DriveDistance(5));
	}
}
