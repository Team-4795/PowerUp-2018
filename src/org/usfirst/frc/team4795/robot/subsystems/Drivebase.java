package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivebase extends Subsystem implements PIDOutput
{
	private final TalonSRX leftMotor1;
	private final TalonSRX leftMotor2;
	private final TalonSRX rightMotor1;
	private final TalonSRX rightMotor2;

	public static final double P_POS = 0.8;
	public static final double I_POS = 0.001;
	public static final double D_POS = 1000.0;
	public static final int I_ZONE_POS = 2000;

	public static final double WHEEL_DIAMETER_IN = 8.0;
	public static final int ENCODER_TICKS_PER_REV = 8192;
	public static final double ENCODER_TICKS_PER_FT = (ENCODER_TICKS_PER_REV
			* 48) / (Math.PI * WHEEL_DIAMETER_IN);

	public Drivebase()
	{
		leftMotor1 = new TalonSRX(RobotMap.LEFT_MOTOR_1.value);
		leftMotor2 = new TalonSRX(RobotMap.LEFT_MOTOR_2.value);
		rightMotor1 = new TalonSRX(RobotMap.RIGHT_MOTOR_1.value);
		rightMotor2 = new TalonSRX(RobotMap.RIGHT_MOTOR_2.value);

		Robot.initTalon(leftMotor1);
		Robot.initTalon(leftMotor2);
		Robot.initTalon(rightMotor1);
		Robot.initTalon(rightMotor2);

		leftMotor2.follow(leftMotor1);
		rightMotor2.follow(rightMotor1);

		rightMotor1.setInverted(true);
		rightMotor2.setInverted(true);
	}

	public void set(ControlMode mode, double leftValue, double rightValue)
	{
		leftMotor1.set(mode, leftValue);
		rightMotor1.set(mode, rightValue);
	}

	public void driveFeet(double feet)
	{
		setPIDF(P_POS, I_POS, D_POS, 0.0, 10);
		setIZone(I_ZONE_POS);
		double distanceInTicks = feet * ENCODER_TICKS_PER_FT;
		set(ControlMode.Position,
				-leftMotor1.getSensorCollection().getQuadraturePosition()
						+ distanceInTicks,
				rightMotor1.getSensorCollection().getQuadraturePosition()
						+ distanceInTicks);
	}

	public void setPIDF(double P, double I, double D, double F, int timeout)
	{
		leftMotor1.config_kP(0, P, timeout);
		leftMotor1.config_kI(0, I, timeout);
		leftMotor1.config_kD(0, D, timeout);
		leftMotor1.config_kF(0, F, timeout);

		rightMotor1.config_kP(0, P, timeout);
		rightMotor1.config_kI(0, I, timeout);
		rightMotor1.config_kD(0, D, timeout);
		rightMotor1.config_kF(0, F, timeout);
	}

	public void setIZone(int i)
	{
		leftMotor1.config_IntegralZone(0, i, 10);
		rightMotor1.config_IntegralZone(0, i, 10);
	}

	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new TankDrive());
	}

	@Override
	public void pidWrite(double output)
	{
		set(ControlMode.PercentOutput, output, output);
	}

	public int getleftEncoder()
	{
		return leftMotor1.getSensorCollection().getQuadraturePosition();
	}
	
	public int getrightEncoder()
	{
		return rightMotor1.getSensorCollection().getQuadraturePosition();
	}
}
