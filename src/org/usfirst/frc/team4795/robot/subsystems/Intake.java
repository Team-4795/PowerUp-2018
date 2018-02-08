package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.ManualIntakeControl;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem
{
	private final Spark leftIntake;
	private final Spark rightIntake;
	private final DigitalInput limitSwitch;

	private final double holdSpeed = 0.1;

	public Intake()
	{
		leftIntake = new Spark(RobotMap.INTAKE_LEFT.value);
		rightIntake = new Spark(RobotMap.INTAKE_RIGHT.value);
		limitSwitch = new DigitalInput(RobotMap.INTAKE_LIMIT.value);
	}

	// Intake with variable speed/direction
	public void variableIntake(double speed)
	{
		leftIntake.set(speed);
		rightIntake.set(-speed);
	}

	public void holdBox()
	{
		if (limitSwitch.get())
		{
			leftIntake.set(holdSpeed);
			rightIntake.set(-holdSpeed);
		}
	}

	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new ManualIntakeControl());
	}

}
