package org.usfirst.frc.team4795.robot;

public enum RobotMap
{
    // CAN motor controllers
    LEFT_MOTOR_1(5),
    LEFT_MOTOR_2(1),
    RIGHT_MOTOR_1(6),
    RIGHT_MOTOR_2(4),
    ARM_MOTOR(2),
    // Joystick mappings
    LEFT_JOY(0),
    RIGHT_JOY(1),
    MANIPULATOR(2);

    public final int value;

    RobotMap(int value) {
        this.value = value;
    }
}
