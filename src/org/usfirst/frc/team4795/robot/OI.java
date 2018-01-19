package org.usfirst.frc.team4795.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI
{
	public static final double JOY_DEADZONE = 0.1;

	public final Joystick LEFT_JOY = new Joystick(RobotMap.LEFT_JOY.value);
	public final Joystick RIGHT_JOY = new Joystick(RobotMap.RIGHT_JOY.value);
	public final Joystick XBOX_JOY = new Joystick(RobotMap.XBOX_CONTROLLER.value);
	
	public double getLeftJoyX()
	{
		double raw = LEFT_JOY.getX();
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}

	public double getLeftJoyY()
	{
		double raw = LEFT_JOY.getY();
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}

	public double getRightJoyX()
	{
		double raw = RIGHT_JOY.getX();
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}

	public double getRightJoyY()
	{
		double raw = RIGHT_JOY.getY();
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}

	public double getXRightJoyY()
	{
		double raw = XBOX_JOY.getRawAxis(5);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public double getXLeftJoyY()
	{
		double raw = XBOX_JOY.getRawAxis(1);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public OI()
	{
	
	}

}
