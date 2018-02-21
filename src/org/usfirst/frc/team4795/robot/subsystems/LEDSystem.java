package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.LightLED;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LEDSystem extends Subsystem
{
	
	public DigitalOutput ledStripRed;
	public DigitalOutput ledStripBlue;
	public DigitalOutput ledStripGreen;
	
	public LEDSystem()
	{
		ledStripRed = new DigitalOutput(RobotMap.LED_RED.value);
		ledStripGreen = new DigitalOutput(RobotMap.LED_GREEN.value);
		ledStripBlue = new DigitalOutput(RobotMap.LED_BLUE.value);
	}

	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new LightLED());
	}

}
