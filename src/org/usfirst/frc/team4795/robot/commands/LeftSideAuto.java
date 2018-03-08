package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftSideAuto extends CommandGroup {

	public LeftSideAuto() {
		double gameDataMultiplier = Robot.gameData.charAt(0) == 'L' ? 1 : -1;
		double speed = 0.8;		
		addSequential(new ArmToPos(false, true));
		addSequential(new DriveFeet(-3.5, speed));
		addSequential(new TurnToAngle(90));
		if(gameDataMultiplier == -1)
			addSequential(new DriveFeet(-14.5, speed));
		else
			addSequential(new DriveFeet(-4.3, speed));
		addSequential(new TurnToAngle(-90));
		addSequential(new DriveFeet(-5, speed));
		addSequential(new AutoIntakeControl(0.5));
	}
}
