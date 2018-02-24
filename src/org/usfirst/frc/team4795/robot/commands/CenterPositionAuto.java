package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPositionAuto extends CommandGroup {
	public CenterPositionAuto() {
		// this var changes the direction the robot will turn based on the game data
		// provided by driverstation
		double gameDataMultiplier = Robot.gameData.charAt(0) == 'L' ? 1 : -1;
		double speed = 0.8;

		addSequential(new ArmToPos(false, true));
		addSequential(new DriveFeet(-5, speed));
		addSequential(new TurnToAngle(-90 * gameDataMultiplier));
		addSequential(new DriveFeet(-5, speed));
		addSequential(new TurnToAngle(90 * gameDataMultiplier));
		addSequential(new DriveFeet(-3.5, speed));
		addSequential(new AutoIntakeControl(-0.5));
	}
}
