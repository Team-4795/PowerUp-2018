package org.usfirst.frc.team4795.robot;

import java.nio.ByteBuffer;
import java.util.BitSet;

import org.usfirst.frc.team4795.robot.commands.ArmToPos;
import org.usfirst.frc.team4795.robot.commands.CVJesus;
import org.usfirst.frc.team4795.robot.commands.DriveDistance;
import org.usfirst.frc.team4795.robot.commands.LeftSideAuto;
import org.usfirst.frc.team4795.robot.commands.RightSideAuto;
import org.usfirst.frc.team4795.robot.commands.TurnToAngle;
import org.usfirst.frc.team4795.robot.subsystems.Arm;
import org.usfirst.frc.team4795.robot.subsystems.Drivebase;
import org.usfirst.frc.team4795.robot.subsystems.Intake;
import org.usfirst.frc.team4795.robot.subsystems.LEDSystem;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
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
	public static LEDSystem ledSystem;
	
	public static DigitalInput selecterBit0;
	public static DigitalInput selecterBit1;
	public static DigitalInput selecterBit2;
	Command CVJUSUS;

	public static BitSet selecterNumber;

	@Override
	public void robotInit()
	{
		drivebase = new Drivebase();
		arm = new Arm();
		intake = new Intake();
		ledSystem = new LEDSystem();
		
		oi = new OI();

		selecterBit0 = new DigitalInput(RobotMap.SELECTER_BIT_0.value);
		selecterBit1 = new DigitalInput(RobotMap.SELECTER_BIT_1.value);
		selecterBit2 = new DigitalInput(RobotMap.SELECTER_BIT_2.value);
		selecterNumber = new BitSet(3);

		CVJUSUS = new ArmToPos(true, true);
	}

	public void robotPeriodic()
	{
		SmartDashboard.putNumber("Arm Encoder", arm.getEncoderTicks());
		SmartDashboard.putNumber("Yaw", drivebase.getYaw());
		SmartDashboard.putNumber("Right Encoder", drivebase.getrightEncoder());
		SmartDashboard.putNumber("Left Encoder", drivebase.getleftEncoder());
		SmartDashboard.putBoolean("Has Box?", intake.hasBox());

		selecterNumber.set(0, selecterBit0.get());
		selecterNumber.set(1, selecterBit1.get());
		selecterNumber.set(2, selecterBit2.get());

		SmartDashboard.putNumber("Selecter", convertBitSet(selecterNumber));
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
		drivebase.hasDriven = false;
		CVJUSUS.start();
	}

	@Override
	public void autonomousPeriodic()
	{
		arm.resetEncoder();
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
	}

	@Override
	public void testPeriodic()
	{
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

	public static long convertBitSet(BitSet bits)
	{
		long value = 0L;
		for (int i = 0; i < bits.length(); ++i)
		{
			value += bits.get(i) ? (1L << i) : 0L;
		}
		return value;
	}

}
