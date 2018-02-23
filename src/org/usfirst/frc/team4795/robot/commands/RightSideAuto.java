package org.usfirst.frc.team4795.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightSideAuto extends CommandGroup {
	public RightSideAuto() {
		addSequential(new ArmToPos(false, false));
		addSequential(new DriveDistance(-5));
		addSequential(new TurnToAngle(-90));
		addSequential(new DriveDistance(-15));
		addSequential(new TurnToAngle(90));
		addSequential(new DriveDistance(-5));
	}

}
