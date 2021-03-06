package org.usfirst.frc.team4795.robot;

public enum RobotMap {
    // CAN motor controllers
    LEFT_MOTOR_1(5), LEFT_MOTOR_2(3), RIGHT_MOTOR_1(6), RIGHT_MOTOR_2(2), ARM_MOTOR(1), PCM(0),
    // PCM Mappings
    PCM_SHOOTER_FORWARD(6), PCM_SHOOTER_REVERSE(4),
    // PWM Mappings
    INTAKE_LEFT(0), INTAKE_RIGHT(1), BAG_TAG(2),
    // DIO Mappings
    LED_RED(7), LED_BLUE(8), LED_GREEN(9), INTAKE_LIMIT(0), SELECTER_BIT_0(2), SELECTER_BIT_1(
            3), SELECTER_BIT_2(4),
    // Joystick mappings
    XBOX_CONTROLLER(0), ARM_CONTROLLER(1),
    // Button Mappings
    INTAKE_IN(5), INTAKE_OUT(6), INTAKE_FIX(4), BATTLE_MODE(3), INTAKE_SHOOT(1), INTAKE_RETRACT(3);

    public final int value;

    RobotMap(int value) {
        this.value = value;
    }
}
