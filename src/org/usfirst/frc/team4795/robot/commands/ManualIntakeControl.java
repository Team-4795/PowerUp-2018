package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import org.usfirst.frc.team4795.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class ManualIntakeControl extends Command {

    public ManualIntakeControl() {
        requires(Robot.intake);
    }

    protected void initialize() {

    }

    protected void execute() {
        boolean isRequestingIn = Robot.oi.ARM_CONTROLLER.getRawButton(RobotMap.INTAKE_IN.value);
        boolean isRequestingOut = Robot.oi.ARM_CONTROLLER.getRawButton(RobotMap.INTAKE_OUT.value);
        boolean isRequestingFix = Robot.oi.ARM_CONTROLLER.getRawButton(RobotMap.INTAKE_FIX.value);
        boolean isRequestingShoot =
                Robot.oi.ARM_CONTROLLER.getRawButton(RobotMap.INTAKE_SHOOT.value);

        if (isRequestingIn) {
            Robot.intake.variableIntake(0.5, 0.5);
            Robot.intake.intaking = true;
        } else if (isRequestingOut) {
            Robot.intake.variableIntake(-0.5, -0.5);
            Robot.intake.outtaking = true;
        } else if (isRequestingFix) {
            Robot.intake.variableIntake(1, -0.5);
            Robot.intake.outtaking = true;
        } else if (isRequestingShoot) {
            Robot.intake.variableIntake(-1, -1);
            Robot.intake.outtaking = true;
        } else {
            Robot.intake.holdBox();
            Robot.intake.outtaking = false;
            Robot.intake.intaking = false;
        }

    }

    protected boolean isFinished() {
        return false;
    }

    protected void interrupted() {
        end();
    }

}
