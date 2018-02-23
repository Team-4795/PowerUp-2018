package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPositionAuto extends CommandGroup {
  public CenterPositionAuto() {
    // this var changes the direction the robot will turn based on the gamedata
    // provided by driverstation
    double gameDataMultiplier = Robot.gameData.charAt(0) == 'L' ? 1 : -1;

    addSequential(new ArmToPos(false, true));
    addSequential(new DriveDistance(-5));
    addSequential(new TurnToAngle(-90 * gameDataMultiplier));
    addSequential(new DriveDistance(-5));
    addSequential(new TurnToAngle(90 * gameDataMultiplier));
    addSequential(new DriveDistance(-3.5));
    addSequential(new Outtake());
  }
}
