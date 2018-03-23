package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossAutoLine extends CommandGroup {

    public CrossAutoLine() {
        double speed = 0.8;
        addSequential(new Delay(Robot.delay));
        addSequential(new DriveFeet(-13.5, speed));
    }
}
