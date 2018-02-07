package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.ManualIntakeControl;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem
{
	Spark leftIntake;
	Spark rightIntake;
	
	public Intake()
	{
		leftIntake = new Spark(RobotMap.INTAKE_LEFT.value);
		rightIntake = new Spark(RobotMap.INTAKE_RIGHT.value);
	}
	
	//Intake with variable speed/direction
	public void variableIntake(double speed)
	{
		leftIntake.set(speed);
		rightIntake.set(-speed);
	}
	
	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new ManualIntakeControl());
	}

}
