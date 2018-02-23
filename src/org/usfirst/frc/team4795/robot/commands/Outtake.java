package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Outtake extends Command {

	public Outtake() {
		requires(Robot.intake);
	}

	protected void initialize() {

	}

	protected void execute() {
		Robot.intake.variableIntake(-0.5, -0.5);
	}

	protected boolean isFinished() {
		if (timeSinceInitialized() > 0.2)
			return true;

		return false;
	}

	protected void interrupted() {
		end();
	}

}
