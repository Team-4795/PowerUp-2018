package org.usfirst.frc.team4795.robot;

import org.usfirst.frc.team4795.robot.subsystems.Drivebase;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot
{
	public static OI oi;
	public static Drivebase drivebase;
	
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	@Override
	public void robotInit()
	{
		drivebase = new Drivebase();
		
		oi = new OI();
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	@Override
	public void disabledInit()
	{

	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit()
	{
		
	}

	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit()
	{
		
	}

	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic()
	{
		
	}
	
	public static void initTalon(TalonSRX motor)
	{
		motor.setNeutralMode(NeutralMode.Coast);
		motor.neutralOutput();
		motor.setSensorPhase(false);
		motor.configForwardLimitSwitchSource(
			LimitSwitchSource.FeedbackConnector,
			LimitSwitchNormal.NormallyOpen,
			0);
		motor.configReverseLimitSwitchSource(
			LimitSwitchSource.FeedbackConnector,
			LimitSwitchNormal.NormallyOpen,
			0);
		motor.configNominalOutputForward(0.0, 0);
		motor.configNominalOutputReverse(0.0, 0);
		motor.configClosedloopRamp(0.5, 0);
	}
}
