package org.usfirst.frc.team4795.robot.commands;

import java.util.Hashtable;
import org.usfirst.frc.team4795.robot.Robot;
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilBox extends Command {
    double Feet;
    double Speed;

    double distanceInTicks;
    double leftTarget;
    double rightTarget;

    boolean isFinished = false;

    public DriveUntilBox(double feet, double speed) {
        requires(Robot.drivebase);
        Feet = feet;
        Speed = speed;
    }
    
    public DriveUntilBox(double feet, double speed, double timeout) {
        requires(Robot.drivebase);
        Feet = feet;
        Speed = speed;
        setTimeout(timeout);
    }
    
    protected void initialize() {
        distanceInTicks = Feet * Robot.drivebase.ENCODER_TICKS_PER_FT;
        leftTarget = (int) (Robot.drivebase.getLeftEncoder() + distanceInTicks);
        rightTarget = (int) (Robot.drivebase.getRightEncoder() + distanceInTicks);
    }

    protected void execute() {
        double leftSpeed =
                Math.pow((leftTarget - Robot.drivebase.getLeftEncoder()) / distanceInTicks, .5)
                        * Speed;
        double rightSpeed =
                Math.pow((rightTarget - Robot.drivebase.getRightEncoder()) / distanceInTicks, .5)
                        * Speed;
        if (Math.abs(leftSpeed) < 0.3 || Math.abs(rightSpeed) < 0.3) {
            leftSpeed = 0;
            rightSpeed = 0;
            isFinished = true;
        }
        if (Feet > 0) {
            Robot.drivebase.set(ControlMode.PercentOutput, leftSpeed, rightSpeed);
        } else
            Robot.drivebase.set(ControlMode.PercentOutput, -leftSpeed, -rightSpeed);

    }

    protected boolean isFinished() {
        return Robot.intake.hasBox();
    }
}
