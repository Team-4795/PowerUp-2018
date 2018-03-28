package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ExtendBagTag extends Command {

    public ExtendBagTag() {
        requires(Robot.arm);
    }

    protected void initialize() {
        Robot.arm.setBagTag(0.5);
    }

    protected void execute() {
        
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
