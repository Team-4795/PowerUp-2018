package org.usfirst.frc.team4795.robot.subsystems;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;
import org.usfirst.frc.team4795.robot.commands.ArcadeDrive;
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

public class Drivebase extends Subsystem implements PIDOutput {
  private final TalonSRX leftMotor1;
  private final VictorSPX leftMotor2;
  private final TalonSRX rightMotor1;
  private final TalonSRX rightMotor2;
  private final AHRS ahrs;

  public final PIDController turnController;

  private static final double kP = -0.03;
  private static final double kI = 0.00;
  private static final double kD = 0.00;
  private static final double kF = 0.00;

  private static final double kToleranceDegrees = 2.0f;

  public static final double WHEEL_DIAMETER_IN = 4.0;
  public static final int ENCODER_TICKS_PER_REV = 2048;
  public static final double ENCODER_TICKS_PER_FT =
      (ENCODER_TICKS_PER_REV * 48) / (Math.PI * WHEEL_DIAMETER_IN);

  private int leftTarget;
  private int rightTarget;
  private double distanceInTicks;

  public boolean hasDriven;

  // state Vars for LEDs
  public boolean isDrivingForward;
  public boolean isDrivingBackwords;

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
    turnController.setOutputRange(-0.3, 0.3);
    turnController.setAbsoluteTolerance(kToleranceDegrees);
    turnController.setContinuous(true);

  }

  public void set(ControlMode mode, double leftValue, double rightValue) {
    leftMotor1.set(mode, leftValue);
    rightMotor1.set(mode, rightValue);
  }

  public void rotateDegrees(double angle) {
    ahrs.reset();
    turnController.reset();
    turnController.setPID(kP, kI, kD, 0.0);
    turnController.setSetpoint(angle);
    turnController.enable();
  }

  public boolean driveFeet(double feet) {
    boolean isFinished = false;
    if (!hasDriven) {
      distanceInTicks = feet * ENCODER_TICKS_PER_FT;
      leftTarget = (int) (getLeftEncoder() + distanceInTicks);
      rightTarget = (int) (getRightEncoder() + distanceInTicks);
      hasDriven = true;
    } else {
      double leftSpeed = Math.pow((leftTarget - getLeftEncoder()) / distanceInTicks, .5) * 0.8;
      double rightSpeed = Math.pow((rightTarget - getRightEncoder()) / distanceInTicks, .5) * 0.8;
      if (Math.abs(leftSpeed) < 0.50 || Math.abs(rightSpeed) < 0.50) {
        leftSpeed = 0;
        rightSpeed = 0;
        hasDriven = false;
        isFinished = true;
      }
      if (feet > 0) {
        set(ControlMode.PercentOutput, leftSpeed, rightSpeed);
      } else
        set(ControlMode.PercentOutput, -leftSpeed, -rightSpeed);
    }
    return isFinished;
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
