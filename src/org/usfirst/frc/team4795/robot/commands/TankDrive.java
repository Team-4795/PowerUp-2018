package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {
	boolean fastMode = false;

	public TankDrive() {
		requires(Robot.drivebase);
	}

	protected void initialize() {}

	protected void execute() {
		if (Robot.oi.MAIN_CONTROLLER.getRawAxis(3) > 0.05) {
			fastMode = true;
		} else {
			fastMode = false;
		}

		double throttle = !fastMode ? -1 : -0.7;
		Robot.drivebase.set(ControlMode.PercentOutput,
				Math.pow(Robot.oi.getMainLeftJoyY() * throttle, 3),
				Math.pow(Robot.oi.getMainRightJoyY() * throttle, 3));

		if (Robot.oi.getMainRightJoyY() > 0)
			Robot.drivebase.isDrivingForward = true;
		else if (Robot.oi.getMainRightJoyY() < 0)
			Robot.drivebase.isDrivingBackwords = true;
		else {
			Robot.drivebase.isDrivingBackwords = false;
			Robot.drivebase.isDrivingForward = false;
		}

	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		end();
	}

}
