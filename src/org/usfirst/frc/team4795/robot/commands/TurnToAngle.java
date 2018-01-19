package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngle extends Command
{
	double Angle;

	double P;
	double I;
	double D;
	double F;

	public TurnToAngle(double angle)
	{
		requires(Robot.drivebase);
		Angle = angle;
	}

	protected void initialize()
	{
		Robot.drivebase.rotateDegrees(Angle);
	}

	protected void execute()
	{

	}

	protected boolean isFinished()
	{
		return false;
	}

}
