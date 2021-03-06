package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.ManualArmControl;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

    private final TalonSRX armMotor;
    private final Servo bagTag;

    public final int kToleranceTicks = 50;
    public final int ENCODER_TICKS_PER_REV = 2048;

    public Arm() {
        armMotor = new TalonSRX(RobotMap.ARM_MOTOR.value);
        bagTag = new Servo(RobotMap.BAG_TAG.value);
        Robot.initTalon (armMotor);

        armMotor.configOpenloopRamp(0.5, 0);
        armMotor.configClosedloopRamp(0.3, 0);
        armMotor.configPeakOutputForward(1, 0);
        armMotor.configPeakOutputReverse(-1, 0);

        armMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen, 0);
        armMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen, 0);

        // Closed loop settings
        armMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        armMotor.configAllowableClosedloopError(0, kToleranceTicks, 0);
        armMotor.setSelectedSensorPosition(getEncoderTicks(), 0, 0);

    }

    public void setRaw(double value) {
        armMotor.set(ControlMode.PercentOutput, value);
    }

    public void setAdjusted(double value, double InitialTorque) {
        double adjustedSpeed =
                InitialTorque * Math.cos(((getEncoderTicks() - 90.0) / 8192.0) * 2 * Math.PI)
                        + value / 3;
        setRaw(adjustedSpeed);
    }

    public void setPosition(double rotations) {
        armMotor.set(ControlMode.Position, rotations);
    }

    public void setBagTag(double value) {
        bagTag.set(value);
    }

    public void resetEncoder() {
        if (getRevLimitSwitch()) {
            armMotor.getSensorCollection().setQuadraturePosition(0, 0);
        }
        if (getFwdLimitSwitch()) {
            armMotor.getSensorCollection().setQuadraturePosition(3086, 0); // I LOVE BITCONNECT
        }
    }

    public void setPIDF(double P, double I, double D, double F) {
        armMotor.config_kP(0, P, 0);
        armMotor.config_kI(0, I, 0);
        armMotor.config_kD(0, D, 0);
        armMotor.config_kF(0, F, 0);
    }

    public int getEncoderTicks() {
        return -armMotor.getSensorCollection().getQuadraturePosition();
    }

    public double getCurrent() {
        return armMotor.getOutputCurrent();
    }

    public double getVoltage() {
        return armMotor.getMotorOutputVoltage();
    }

    public int getEncoderVelocity() {
        return armMotor.getSensorCollection().getQuadratureVelocity();
    }

    public boolean getRevLimitSwitch() {
        return armMotor.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean getFwdLimitSwitch() {
        return armMotor.getSensorCollection().isFwdLimitSwitchClosed();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ManualArmControl());
    }
}
