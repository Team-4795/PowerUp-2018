package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {

	boolean fastMode = false;

	public ArcadeDrive() {
		requires(Robot.drivebase);
	}

	protected void initialize() {}

	protected void execute() {

		double throttle = !(Robot.oi.MAIN_CONTROLLER.getRawAxis(3) > 0.05) ? -1 : -0.5;

		double JoyXValue = Robot.oi.getMainLeftJoyX();
		double JoyYValue = Robot.oi.getMainRightJoyY();

		double outputLeft = JoyYValue;
		double outputRight = JoyYValue;

		double difference = JoyXValue / 2;

		double correction = JoyYValue != 0 ? Math.abs(JoyYValue) / JoyYValue : -1;

		outputLeft += difference * correction;
		outputRight -= difference * correction;

		if (outputLeft > 0)
			Robot.drivebase.isDrivingForward = true;
		else if (outputLeft < 0)
			Robot.drivebase.isDrivingBackwords = true;
		else {
			Robot.drivebase.isDrivingBackwords = false;
			Robot.drivebase.isDrivingForward = false;
		}

		Robot.drivebase.set(ControlMode.PercentOutput, outputLeft * throttle,
				outputRight * throttle);

	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		end();
	}

}
