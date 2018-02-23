package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {
	double Angle;

	boolean isFinished = false;
	boolean inErrorZone = false;
	int count;

	public TurnToAngle(double angle) {
		requires(Robot.drivebase);
		Angle = angle;
	}

	protected void initialize() {
		Robot.drivebase.rotateDegrees(Angle);
	}

	protected void execute() {
		double error = Robot.drivebase.turnController.getError();
		inErrorZone = Math.abs(error) < 3 ? true : false;

		if (inErrorZone) {
			count++;
			if (count >= 10) {
				isFinished = true;
			} else {
				isFinished = false;
			}
		} else {
			count = 0;
		}
	}

	protected boolean isFinished() {
		return isFinished;
	}

	protected void end() {
		Robot.drivebase.turnController.disable();
	}
}
