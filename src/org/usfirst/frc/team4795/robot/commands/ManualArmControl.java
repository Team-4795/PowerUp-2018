package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualArmControl extends Command
{

	public ManualArmControl()
	{
		requires(Robot.arm);
	}

	protected void initialize()
	{

	}

	protected void execute()
	{

		double speed = Robot.intake.hasBox() ? 0.7 : 0.5;

		double torque = Robot.intake.hasBox() ? Robot.arm.BOX_INITIAL_TORQUE : Robot.arm.NO_BOX_INITIAL_TORQUE;
		boolean isRequestingBattleMode = Robot.oi.ARM_CONTROLLER.getRawButton(RobotMap.BATTLE_MODE.value);

		if (Math.abs(Robot.arm.getEncoderVelocity()) < 150)
			Robot.arm.setRaw(Robot.oi.getArmLeftJoyY() * speed);
		else
		{
			Robot.arm.setRaw(0);
		}
	}

	protected boolean isFinished()
	{
		return false;
	}

	protected void interrupted()
	{
		end();
	}

}
