package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.ManualIntakeControl;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	private final Spark leftIntake;
	private final Spark rightIntake;

	private final DigitalInput limitSwitch;
	private final double holdSpeed = 0.1;

	public boolean intaking;
	public boolean outtaking;

	public Intake() {
		leftIntake = new Spark(RobotMap.INTAKE_LEFT.value);
		rightIntake = new Spark(RobotMap.INTAKE_RIGHT.value);
		limitSwitch = new DigitalInput(RobotMap.INTAKE_LIMIT.value);
	}

	// Intake with variable speed/direction
	public void variableIntake(double speed, double speed2) {
		leftIntake.set(speed);
		rightIntake.set(-speed2);
	}

	// if we have a box, continue to spin the wheels enough to grip and hold that box
	public void holdBox() {
		if (hasBox()) {
			leftIntake.set(holdSpeed);
			rightIntake.set(-holdSpeed);
		} else {
			leftIntake.set(0);
			rightIntake.set(0);
		}
	}

	public boolean hasBox() {
		return !limitSwitch.get();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualIntakeControl());
	}

}
