package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.ArcadeDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivebase extends Subsystem implements PIDOutput {
    private final TalonSRX leftMotor1;
    private final VictorSPX leftMotor2;
    private final TalonSRX rightMotor1;
    private final TalonSRX rightMotor2;
    private final AHRS ahrs;

    public final PIDController turnController;

    private final double kP = -0.03;
    private final double kI = 0.00;
    private final double kD = 0.00;
    private final double kF = 0.00;

    private static final double kToleranceDegrees = 2.0f;

    public final double WHEEL_DIAMETER_IN = 4.0;
    public final int ENCODER_TICKS_PER_REV = 2048;
    public final double ENCODER_TICKS_PER_FT =
            (ENCODER_TICKS_PER_REV * 48) / (Math.PI * WHEEL_DIAMETER_IN);

    // state variables for LEDs
    public boolean isDrivingForward;
    public boolean isDrivingBackwards;

    public Drivebase() {
        leftMotor1 = new TalonSRX(RobotMap.LEFT_MOTOR_1.value);
        leftMotor2 = new VictorSPX(RobotMap.LEFT_MOTOR_2.value);
        rightMotor1 = new TalonSRX(RobotMap.RIGHT_MOTOR_1.value);
        rightMotor2 = new TalonSRX(RobotMap.RIGHT_MOTOR_2.value);
        ahrs = new AHRS(SPI.Port.kMXP);

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
        turnController.setOutputRange(-0.45, 0.45);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
    }

    public void set(ControlMode mode, double leftValue, double rightValue) {
        leftMotor1.set(mode, leftValue);
        rightMotor1.set(mode, rightValue);
    }

    public void rotateDegrees(double angle) {
        // reset the NavX so that the setpoint is relative to your current position and not the
        // absolute position of the NavX
        ahrs.reset();
        turnController.reset();
        turnController.setPID(kP, kI, kD, 0.0);
        turnController.setSetpoint(angle);
        turnController.enable();
    }

    public int getLeftEncoder() {
        return -leftMotor1.getSensorCollection().getQuadraturePosition();
    }

    public int getRightEncoder() {
        return rightMotor1.getSensorCollection().getQuadraturePosition();
    }

    public double getYaw() {
        return ahrs.getYaw();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }

    @Override
    public void pidWrite(double output) {
        set(ControlMode.PercentOutput, -output, output);
    }
}
