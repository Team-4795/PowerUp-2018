package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPositionAuto extends CommandGroup {
    public CenterPositionAuto() {
        // this var changes the direction the robot will turn based on the game data
        // provided by driverstation
        double gameDataMultiplier = Robot.gameData.charAt(0) == 'L' ? 1 : -1;
        double speed = 0.8;

        addSequential(new Delay(Robot.delay));
        addSequential(new ArmToPos(false, true));
        addSequential(new ExtendBagTag());
        addSequential(new DriveFeet(-3.5, speed));
        addSequential(new TurnToAngle(-90 * gameDataMultiplier));
        addSequential(new DriveFeet(-4.5, speed));
        addSequential(new TurnToAngle(90 * gameDataMultiplier));
        addSequential(new DriveFeet(-5.5, speed, 3.5));
        addSequential(new AutoIntakeControl(0.5));
        
        // two box
        // addSequential(new ArmToPos(true, false));
        // addSequential(new TurnToAngle(-90 * gameDataMultiplier));
        // addParallel(new AutoIntakeControl(0.5));
        // addSequential(new DriveFeet(-1, speed));
        // addSequential(new DriveFeet(1, speed));
        // addSequential(new TurnToAngle(90 * gameDataMultiplier));
        // addSequential(new ArmToPos(false, true));
        // addSequential(new AutoIntakeControl(-0.5));
    }
}
