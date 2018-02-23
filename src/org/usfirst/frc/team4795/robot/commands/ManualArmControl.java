package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ManualArmControl extends Command {

  public ManualArmControl() {
    requires(Robot.arm);
  }

  protected void initialize() {

  }

  protected void execute() {

    double speed = Robot.intake.hasBox() ? 0.7 : 0.5;

    if (Math.abs(Robot.arm.getEncoderVelocity()) < 150)
      Robot.arm.setRaw(Robot.oi.getArmLeftJoyY() * speed);
    else {
      Robot.arm.setRaw(0);
    }
  }

  protected boolean isFinished() {
    return false;
  }

  protected void interrupted() {
    end();
  }

}
