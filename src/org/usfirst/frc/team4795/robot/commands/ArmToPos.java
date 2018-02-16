package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmToPos extends Command
{

	boolean isGoingForward;
	boolean hasBox;

	public ArmToPos(boolean isForward, boolean hasBox)
	{
		requires(Robot.arm);
		isGoingForward = isForward;
		this.hasBox = hasBox;
	}

	protected void initialize()
	{

		double dir = isGoingForward ? 1 : -1;
		Robot.arm.setAdjusted(0.7 * dir, hasBox ? Robot.arm.BOX_INITIAL_TORQUE : Robot.arm.NO_BOX_INITIAL_TORQUE);
	}

	protected void execute()
	{
		if (Math.abs(Robot.arm.getEncoderVelocity()) > 200)
			Robot.arm.setAdjusted(0, hasBox ? Robot.arm.BOX_INITIAL_TORQUE : Robot.arm.NO_BOX_INITIAL_TORQUE);
		SmartDashboard.putNumber("Velocity", Robot.arm.getEncoderVelocity());

	}

	protected boolean isFinished()
	{
		if (timeSinceInitialized() > 0.5)
			return Math.abs(Robot.arm.getEncoderVelocity()) < 4;
		return false;
	}

	protected void interrupted()
	{
		end();
	}

}
