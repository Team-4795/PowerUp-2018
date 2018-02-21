package org.usfirst.frc.team4795.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI
{
	public static final double JOY_DEADZONE = 0.15;
	
	public final Joystick MAIN_CONTROLLER = new Joystick(RobotMap.XBOX_CONTROLLER.value);
	public final Joystick ARM_CONTROLLER = new Joystick(RobotMap.ARM_CONTROLLER.value);
	
	
	public double getMainRightJoyY()
	{
		double raw = MAIN_CONTROLLER.getRawAxis(5);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public double getMainRightJoyX()
	{
		double raw = MAIN_CONTROLLER.getRawAxis(4);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public double getMainLeftJoyY()
	{
		double raw = MAIN_CONTROLLER.getRawAxis(1);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public double getMainLeftJoyX()
	{
		double raw = MAIN_CONTROLLER.getRawAxis(0);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public double getArmRightJoyY()
	{
		double raw = ARM_CONTROLLER.getRawAxis(5);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public double getArmRightJoyX()
	{
		double raw = ARM_CONTROLLER.getRawAxis(4);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public double getArmLeftJoyY()
	{
		double raw = ARM_CONTROLLER.getRawAxis(1);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public double getArmLeftJoyX()
	{
		double raw = ARM_CONTROLLER.getRawAxis(0);
		return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;
	}
	
	public OI()
	{
	
	}

}
