package org.usfirst.frc.team4795.robot;

public enum RobotMap
{
    // CAN motor controllers
    LEFT_MOTOR_1(5),
    LEFT_MOTOR_2(3),
    RIGHT_MOTOR_1(6),
    RIGHT_MOTOR_2(2),
    ARM_MOTOR(1),
    //PWM Mappings
    INTAKE_LEFT(0),
    INTAKE_RIGHT(1),
    //DIO Mappings
    LED_RED(0),
    LED_BLUE(1),
    LED_GREEN(2),
    INTAKE_LIMIT(3),
    // Joystick mappings
    LEFT_JOY(0),
    RIGHT_JOY(1),
    XBOX_CONTROLLER(1),
    MANIPULATOR(2),
	//Button Mappings
	INTAKE_IN(5),
	INTAKE_OUT(6);
	
    public final int value;

    RobotMap(int value) {
        this.value = value;
    }
}
