package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.ManualArmControl;
import org.usfirst.frc.team4795.robot.commands.TurnToAngle;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem
{

	private final TalonSRX armMotor;

	public static double kP = 0.0;
	public static double kI = 0.00;
	public static double kD = 0.00;
	public static double kF = 0.00;
	public static int IZone = 10;

	public static final int kToleranceTicks = 50;
	public static final int ENCODER_TICKS_PER_REV = 2048;

	public static final double NO_BOX_INITIAL_TORQUE = 0.5;
	public static final double BOX_INITIAL_TORQUE = 0.7;
	
	public static final double BATTLE_MODE_TORQUE_ADDITION = 0.2;
	
	
	public Arm()
	{
		armMotor = new TalonSRX(RobotMap.ARM_MOTOR.value);
		Robot.initTalon(armMotor);
		armMotor.configOpenloopRamp(0.5, 0);
		armMotor.configClosedloopRamp(0.3, 0);
		armMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				0);
		armMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
				0);
		armMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		// THESE ARE PERSISTANT, THEY MUST BE CHANGED, THEY WILL NOT RESET ON THEIR OWN
		armMotor.configPeakOutputForward(1, 0);
		armMotor.configPeakOutputReverse(-1, 0);

		armMotor.configAllowableClosedloopError(0, kToleranceTicks, 0);
		armMotor.setSelectedSensorPosition(getEncoderTicks(), 0, 0);
	}

	public void setRaw(double value)
	{
		armMotor.set(ControlMode.PercentOutput, value);
	}
	
	public void setAdjusted(double value, double InitialTorque)
	{
		double adjustedSpeed = -InitialTorque * Math.cos(((getEncoderTicks() - 150) / 8192.0) * 2 * Math.PI) + value/3;
		setRaw(adjustedSpeed);
		SmartDashboard.putNumber("Adjusted", adjustedSpeed);
	}
	public void setPosition(double rotations)
	{
		armMotor.set(ControlMode.Position, rotations);
	}

	public void holdArm()
	{
		armMotor.set(ControlMode.Velocity, 0.001);
	}

	public void setPIDF(double P, double I, double D, double F)
	{
		armMotor.config_kP(0, P, 0);
		armMotor.config_kI(0, I, 0);
		armMotor.config_kD(0, D, 0);
		armMotor.config_kF(0, F, 0);
	}

	@Override
	protected void initDefaultCommand()
	{
		setDefaultCommand(new ManualArmControl());
	}

	public int getEncoderTicks()
	{
		return armMotor.getSensorCollection().getQuadraturePosition();
	}

	public double getCurrent()
	{
		return armMotor.getOutputCurrent();
	}
	
	public double getVoltage()
	{
		return armMotor.getMotorOutputVoltage();
	}
	
	public int getEncoderVelocity()
	{
		return armMotor.getSensorCollection().getQuadratureVelocity();
	}
	
	public boolean getRevLimitSwitch()
	{
		return armMotor.getSensorCollection().isRevLimitSwitchClosed();
	}

	public boolean getFwdLimitSwitch()
	{
		return armMotor.getSensorCollection().isFwdLimitSwitchClosed();
	}

	public void resetEncoder()
	{
		if (getRevLimitSwitch())
		{
			armMotor.getSensorCollection().setQuadraturePosition(0, 0);
		}

	}
}
