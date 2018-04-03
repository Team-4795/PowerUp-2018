package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class LightLED extends Command {
    double targetTime;
    double delay = 0.2;
    public LightLED() {
        requires(Robot.ledSystem);
    }

    protected void initialize() {
        targetTime = Timer.getFPGATimestamp() + delay;
    }

    protected void execute() {
        if (Robot.intake.intaking) {
            Robot.ledSystem.ledStripRed.set(true);
            Robot.ledSystem.ledStripGreen.set(true);
            Robot.ledSystem.ledStripBlue.set(false);
        } else if (Robot.intake.outtaking) {
            Robot.ledSystem.ledStripRed.set(true);
            Robot.ledSystem.ledStripGreen.set(false);
            Robot.ledSystem.ledStripBlue.set(true);
        } else if (Robot.drivebase.isDrivingForward) {
            Robot.ledSystem.ledStripRed.set(false);
            Robot.ledSystem.ledStripGreen.set(true);
            Robot.ledSystem.ledStripBlue.set(true);
        } else if (Robot.drivebase.isDrivingBackwards) {
            Robot.ledSystem.ledStripRed.set(true);
            Robot.ledSystem.ledStripGreen.set(false);
            Robot.ledSystem.ledStripBlue.set(false);
        } else {
            Robot.ledSystem.ledStripRed.set(true);
            Robot.ledSystem.ledStripGreen.set(true);
            Robot.ledSystem.ledStripBlue.set(true);
        }
        
        if(Robot.intake.hasBox())
        {
            if(Timer.getFPGATimestamp() < targetTime)
            {
                Robot.ledSystem.ledStripRed.set(false);
                Robot.ledSystem.ledStripGreen.set(false);
                Robot.ledSystem.ledStripBlue.set(false);
            }
            else if(Timer.getFPGATimestamp() > targetTime + delay)
            {
                targetTime = Timer.getFPGATimestamp() + delay;
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
