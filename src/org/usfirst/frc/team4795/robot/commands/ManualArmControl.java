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
		double torque = Robot.intake.hasBox() ? Robot.arm.BOX_INITIAL_TORQUE : Robot.arm.NO_BOX_INITIAL_TORQUE;
		boolean isRequestingBattleMode = Robot.oi.XBOX_JOY.getRawButton(RobotMap.BATTLE_MODE.value);
		
		Robot.arm.setAdjusted(Robot.oi.getXLeftJoyY(), 
				isRequestingBattleMode ? torque + Robot.arm.BATTLE_MODE_TORQUE_ADDITION : torque);
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
