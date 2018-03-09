package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {

    boolean fastMode = false;

    public ArcadeDrive() {
        requires(Robot.drivebase);
    }

    protected void initialize() {}

    protected void execute() {

        double throttle = !(Robot.oi.MAIN_CONTROLLER.getRawButton(6)) ? -1 : -0.5;

        double JoyXValue = Robot.oi.getMainLeftJoyX();
        double JoyYValue = Robot.oi.getMainRightJoyY();

        // this code works by mapping the Y value of the joystick to the output of both motors, and
        // then using the X value to calculate the difference in outputs necessary to make a turn
        // that difference is then added to the left side, and subtracted from the right side,
        // turning the robot
        double outputLeft = JoyYValue;
        double outputRight = JoyYValue;

        double difference = JoyXValue / -2;

        // this variable accounts for turning while moving forward, if you want to move in an arc
        // and push left and forward, it will end up moving right and forward. This accounts for
        // that error and ensures the robot follows the direction of the stick
        // double correction = JoyYValue != 0 ? Math.abs(JoyYValue) / JoyYValue : -1;

        outputLeft += difference;
        outputRight -= difference;

        if (outputLeft > 0)
            Robot.drivebase.isDrivingForward = true;
        else if (outputLeft < 0)
            Robot.drivebase.isDrivingBackwords = true;
        else {
            Robot.drivebase.isDrivingBackwords = false;
            Robot.drivebase.isDrivingForward = false;
        }

        Robot.drivebase.set(ControlMode.PercentOutput, outputLeft * throttle,
                outputRight * throttle);

    }

    protected boolean isFinished() {
        return false;
    }

    protected void interrupted() {
        end();
    }

}
