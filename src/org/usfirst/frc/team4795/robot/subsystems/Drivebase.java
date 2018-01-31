package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivebase extends Subsystem implements PIDOutput
{
	private final TalonSRX leftMotor1;
	private final VictorSPX leftMotor2;
	private final TalonSRX rightMotor1;
	private final TalonSRX rightMotor2;
	private final AHRS ahrs;

	public final PIDController turnController;

	public static final double kP = -0.03;
	public static final double kI = 0.00;
	public static final double kD = 0.00;
	public static final double kF = 0.00;

	public static final double kToleranceDegrees = 2.0f;

	public static final double WHEEL_DIAMETER_IN = 4.0;
	public static final int ENCODER_TICKS_PER_REV = 2048;
	public static final double ENCODER_TICKS_PER_FT = (ENCODER_TICKS_PER_REV * 48) / (Math.PI * WHEEL_DIAMETER_IN);

	private int leftTarget;
	private int rightTarget;
	public boolean hasDriven;
	private double distanceInTicks;

	public Drivebase()
	{
		leftMotor1 = new TalonSRX(RobotMap.LEFT_MOTOR_1.value);
		leftMotor2 = new VictorSPX(RobotMap.LEFT_MOTOR_2.value);
		rightMotor1 = new TalonSRX(RobotMap.RIGHT_MOTOR_1.value);
		rightMotor2 = new TalonSRX(RobotMap.RIGHT_MOTOR_2.value);
		ahrs = new AHRS(SPI.Port.kMXP);
		// P = 0.002
		Robot.initTalon(leftMotor1);
		Robot.initVictor(leftMotor2);
		Robot.initTalon(rightMotor1);
		Robot.initTalon(rightMotor2);

		leftMotor2.follow(leftMotor1);
		rightMotor2.follow(rightMotor1);

		rightMotor1.setInverted(true);
		rightMotor2.setInverted(true);

		turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-0.3, 0.3);
		turnController.setAbsoluteTolerance(kToleranceDegrees);
		turnController.setContinuous(true);
		LiveWindow.add(turnController);

	}

	public void set(ControlMode mode, double leftValue, double rightValue)
	{
		leftMotor1.set(mode, leftValue);
		rightMotor1.set(mode, rightValue);
	}

	public void rotateDegrees(double angle)
	{
		double setpoint = ((getYaw() + angle) % 360) - 180;
		SmartDashboard.putNumber("Setpoint", setpoint);
		SmartDashboard.putNumber("Yaw", getYaw());
		ahrs.reset();
		turnController.reset();
		turnController.setPID(kP, kI, kD, 0.0);
		turnController.setSetpoint(angle);
		turnController.enable();
	}

	public boolean driveFeet(double feet)
	{
		boolean isFinished = false;
		if (!hasDriven)
		{
			distanceInTicks = feet * ENCODER_TICKS_PER_FT;
			leftTarget = (int) (getleftEncoder() + distanceInTicks);
			rightTarget = (int) (getrightEncoder() + distanceInTicks);
			hasDriven = true;
		} else
		{
			double leftSpeed = Math.pow((leftTarget - getleftEncoder()) / distanceInTicks, .5);
			double rightSpeed = Math.pow((rightTarget - getrightEncoder()) / distanceInTicks, .5);
			if (Math.abs(leftSpeed) < 0.50 || Math.abs(rightSpeed) < 0.50)
			{
				leftSpeed = 0;
				rightSpeed = 0;
				hasDriven = false;
				isFinished = true;
			}
			SmartDashboard.putNumber("Target Ticks", leftTarget);
			
			set(ControlMode.PercentOutput, leftSpeed, rightSpeed);
		}
		return isFinished;
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
		set(ControlMode.PercentOutput, -output, output);
	}

	public int getleftEncoder()
	{
		return -leftMotor1.getSensorCollection().getQuadraturePosition();
	}

	public int getrightEncoder()
	{
		return rightMotor1.getSensorCollection().getQuadraturePosition();
	}

	public double getYaw()
	{
		return ahrs.getYaw();
	}
}
