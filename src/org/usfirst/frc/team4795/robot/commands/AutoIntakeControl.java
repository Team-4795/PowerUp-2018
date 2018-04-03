package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AutoIntakeControl extends Command {
    double Speed;

    public AutoIntakeControl(double speed) {
        requires(Robot.intake);
        Speed = speed;
        setTimeout(0.5);
    }

    protected void initialize() {

    }

    protected void execute() {
        Robot.intake.variableIntake(Speed, Speed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void interrupted() {
        end();
    }

}
