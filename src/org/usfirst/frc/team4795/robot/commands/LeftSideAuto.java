package org.usfirst.frc.team4795.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftSideAuto extends CommandGroup {

	public LeftSideAuto() {
		double speed = 0.8;
		addSequential(new ArmToPos(false, false));
		addSequential(new DriveFeet(-5, speed));
		addSequential(new TurnToAngle(90));
		addSequential(new DriveFeet(-15, speed));
		addSequential(new TurnToAngle(-90));
		addSequential(new DriveFeet(-5, speed));
	}
}
