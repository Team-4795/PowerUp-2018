package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmToPos extends Command {

  boolean isGoingForward;
  boolean hasBox;

  public ArmToPos(boolean isForward, boolean hasBox) {
    requires(Robot.arm);
    isGoingForward = isForward;
    this.hasBox = hasBox;
  }

  protected void initialize() {

    double dir = isGoingForward ? -1 : 1;
    double speed = Robot.intake.hasBox() ? 0.4 : 0.3;
    Robot.arm.setRaw(speed * dir);
  }

  protected void execute() {
    if (Math.abs(Robot.arm.getEncoderVelocity()) > 300)
      Robot.arm.setRaw(0);
    SmartDashboard.putNumber("Velocity", Robot.arm.getEncoderVelocity());

  }

  protected boolean isFinished() {
    if (timeSinceInitialized() > 0.5)
      return Math.abs(Robot.arm.getEncoderVelocity()) < 4;
    return false;
  }

  protected void interrupted() {
    end();
  }

}
