package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightSideAuto extends CommandGroup {
    public RightSideAuto() {
        double gameDataMultiplier = Robot.gameData.charAt(0) == 'L' ? 1 : -1;
        double speed = 0.8;
        addSequential(new Delay(Robot.delay));
        if(gameDataMultiplier == -1){
            addSequential(new ArmToPos(false, true));
            addSequential(new DriveFeet(-12, speed));
            addSequential(new TurnToAngle(-90));
            addSequential(new DriveFeet(-4, speed));
            addSequential(new AutoIntakeControl(0.5));
        }

    }

}
