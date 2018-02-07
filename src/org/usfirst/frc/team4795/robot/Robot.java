package org.usfirst.frc.team4795.robot;

import org.usfirst.frc.team4795.robot.commands.ArmToPos;
import org.usfirst.frc.team4795.robot.commands.CVJesus;
import org.usfirst.frc.team4795.robot.commands.DriveDistance;
import org.usfirst.frc.team4795.robot.commands.LeftSideAuto;
import org.usfirst.frc.team4795.robot.commands.RightSideAuto;
import org.usfirst.frc.team4795.robot.commands.TurnToAngle;
import org.usfirst.frc.team4795.robot.subsystems.Arm;
import org.usfirst.frc.team4795.robot.subsystems.Drivebase;
import org.usfirst.frc.team4795.robot.subsystems.Intake;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot
{
	public static OI oi;
	public static Drivebase drivebase;
	public static Arm arm;
	public static Intake intake;
	Command CVJUSUS;
	
	@Override
	public void robotInit()
	{
		drivebase = new Drivebase();
		arm = new Arm();
		intake = new Intake();
		oi = new OI();
		
		CVJUSUS = new ArmToPos();
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
		arm.kP = 0;
		arm.kI = 0;
		arm.kD = 0;
		
		drivebase.hasDriven = false;
		CVJUSUS.start();
		//Scheduler.getInstance().add(new ArmToPos());
	}

	@Override
	public void autonomousPeriodic()
	{
		arm.resetEncoder();
		SmartDashboard.putNumber("Arm Encoder", arm.getEncoderTicks());
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
		arm.resetEncoder();
		SmartDashboard.putNumber("Yaw", drivebase.getYaw());
		SmartDashboard.putNumber("Right Encoder", drivebase.getrightEncoder());
		SmartDashboard.putNumber("Left Encoder", drivebase.getleftEncoder());
		SmartDashboard.putNumber("Arm Encoder", arm.getEncoderTicks());
	}

	@Override
	public void testPeriodic()
	{
		SmartDashboard.putNumber("Yaw", drivebase.getYaw());
	}

	public static void initTalon(TalonSRX motor)
	{
		motor.setNeutralMode(NeutralMode.Brake);
		motor.neutralOutput();
		motor.setSensorPhase(false);
		motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		motor.configNominalOutputForward(0.0, 0);
		motor.configNominalOutputReverse(0.0, 0);
		motor.configClosedloopRamp(0.5, 0);

		motor.getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	public static void initVictor(VictorSPX motor)
	{
		motor.setNeutralMode(NeutralMode.Brake);
		motor.neutralOutput();
		motor.setSensorPhase(false);
		motor.configNominalOutputForward(0.0, 0);
		motor.configNominalOutputReverse(0.0, 0);
		motor.configClosedloopRamp(0.5, 0);
	}
	
}
