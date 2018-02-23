package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistance extends Command {
	double Feet;

	double P;
	double I;
	double D;
	double F;

	boolean isFinished = false;

	public DriveDistance(double feet) {
		requires(Robot.drivebase);
		Feet = feet;
	}

	protected void initialize() {
	}

	protected void execute() {
		isFinished = Robot.drivebase.driveFeet(Feet);
	}

	protected boolean isFinished() {
		return isFinished;
	}
}
