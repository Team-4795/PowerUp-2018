package org.usfirst.frc.team4795.robot.commands;

import org.usfirst.frc.team4795.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ArmToPos extends Command {

    boolean isGoingForward;
    boolean hasBox;
    boolean extendTag = false;
    
    public ArmToPos(boolean isForward, boolean hasBox) {
        requires(Robot.arm);
        isGoingForward = isForward;
        this.hasBox = hasBox;
    }

    public ArmToPos(boolean isForward, boolean hasBox, boolean extendBagTag) {
        requires(Robot.arm);
        isGoingForward = isForward;
        this.hasBox = hasBox;
        extendTag = extendBagTag;
    }
    
    protected void initialize() {
        double dir = isGoingForward ? -1 : 1;
        double speed = Robot.intake.hasBox() ? 0.4 : 0.3;
        Robot.arm.setRaw(speed * dir);
        if(extendTag)
            Robot.arm.setBagTag(0.5);
    }

    protected void execute() {
        // stop the arm if its going too fast, and let gravity/its momentum push it down
        if (Math.abs(Robot.arm.getEncoderVelocity()) > 300)
            Robot.arm.setRaw(0);
    }

    protected boolean isFinished() {
        // if enough time has passed, and the arm has stopped moving due to hitting a hard stop,
        // command has ended
        return (Robot.arm.getRevLimitSwitch() || Robot.arm.getFwdLimitSwitch());
    }

    protected void interrupted() {
        end();
    }

}
