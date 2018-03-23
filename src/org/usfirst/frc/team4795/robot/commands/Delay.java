package org.usfirst.frc.team4795.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {

    public Delay(double timeout) {
        setTimeout(timeout);
    }

    protected void initialize() {}

    protected void execute() {

    }

    protected boolean isFinished() {
        return isTimedOut();
    }

}
