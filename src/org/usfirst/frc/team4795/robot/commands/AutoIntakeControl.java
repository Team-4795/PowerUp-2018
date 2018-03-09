package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AutoIntakeControl extends Command {
    double Speed;

    public AutoIntakeControl(double speed) {
        requires(Robot.intake);
        Speed = speed;
    }

    protected void initialize() {

    }

    protected void execute() {
        Robot.intake.variableIntake(Speed, Speed);
    }

    protected boolean isFinished() {
        if (timeSinceInitialized() > 0.2)
            return true;

        return false;
    }

    protected void interrupted() {
        end();
    }

}
