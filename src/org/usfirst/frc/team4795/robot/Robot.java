package org.usfirst.frc.team4795.robot;

import org.usfirst.frc.team4795.robot.commands.CenterPositionAuto;
import org.usfirst.frc.team4795.robot.commands.DoNothing;
import org.usfirst.frc.team4795.robot.commands.LeftSideAuto;
import org.usfirst.frc.team4795.robot.commands.RightSideAuto;
import org.usfirst.frc.team4795.robot.subsystems.Arm;
import org.usfirst.frc.team4795.robot.subsystems.Drivebase;
import org.usfirst.frc.team4795.robot.subsystems.Intake;
import org.usfirst.frc.team4795.robot.subsystems.LEDSystem;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
    public static OI oi;
    public static Drivebase drivebase;
    public static Arm arm;
    public static Intake intake;
    public static LEDSystem ledSystem;

    public static Dial AutonomousSelector;
    public static Command selectedAutonomous;

    public static String gameData = "";
    public static boolean hasData = false;
    public static double delay = 0;
    public static double timeDelay = 0;
    public static boolean DebugMode = false;

    @Override
    public void robotInit() {
        drivebase = new Drivebase();
        arm = new Arm();
        intake = new Intake();
        ledSystem = new LEDSystem();

        oi = new OI();
        AutonomousSelector = new Dial(RobotMap.SELECTER_BIT_0.value, RobotMap.SELECTER_BIT_1.value,
                RobotMap.SELECTER_BIT_2.value);
    }

    public void robotPeriodic() {
        arm.resetEncoder();

        if (DebugMode) {
            SmartDashboard.putNumber("Arm Encoder", arm.getEncoderTicks());
            SmartDashboard.putNumber("Velocity", arm.getEncoderVelocity());
            SmartDashboard.putNumber("Yaw", drivebase.getYaw());
            SmartDashboard.putNumber("Right Encoder", drivebase.getRightEncoder());
            SmartDashboard.putNumber("Left Encoder", drivebase.getLeftEncoder());
            SmartDashboard.putNumber("Arm Voltage", arm.getVoltage());
            SmartDashboard.putNumber("Arm Current", arm.getCurrent());
        }

        SmartDashboard.putBoolean("Has Box?", intake.hasBox());

        SmartDashboard.putNumber("Selecter", AutonomousSelector.getDialPosition());
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {
        if (!hasData) {
            gameData = DriverStation.getInstance().getGameSpecificMessage();
            if (gameData.length() > 0) {
                hasData = true;

                switch ((int) AutonomousSelector.getDialPosition()) {
                    case 0:
                        selectedAutonomous = new DoNothing();
                        break;
                    case 1:
                        selectedAutonomous = new CenterPositionAuto();
                        break;
                    case 2:
                        selectedAutonomous = new LeftSideAuto();
                        break;
                    case 3:
                        selectedAutonomous = new RightSideAuto();
                        break;
                    default:
                        selectedAutonomous = new DoNothing();
                        break;
                }
                selectedAutonomous.start();
            }
        }
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {}

    public static void initTalon(TalonSRX motor) {
        motor.setNeutralMode(NeutralMode.Brake);
        motor.neutralOutput();
        motor.setSensorPhase(false);
        motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen, 0);
        motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
                LimitSwitchNormal.NormallyOpen, 0);
        motor.configNominalOutputForward(0.0, 0);
        motor.configNominalOutputReverse(0.0, 0);
        motor.configClosedloopRamp(0.5, 0);

        motor.getSensorCollection().setQuadraturePosition(0, 0);
    }

    public static void initVictor(VictorSPX motor) {
        motor.setNeutralMode(NeutralMode.Brake);
        motor.neutralOutput();
        motor.setSensorPhase(false);
        motor.configNominalOutputForward(0.0, 0);
        motor.configNominalOutputReverse(0.0, 0);
        motor.configClosedloopRamp(0.5, 0);
    }

}
