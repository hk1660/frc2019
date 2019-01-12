package org.team1660.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Class to encapsulate all XboxOne functionality. No need to have multiple copies
 * in OI all the time
 *
 * @author Aldecai
 */

public class XboxOne extends Joystick {

    
    // Xbox controller button mappings
    public final static int A_BUTTON = 1;
    public final static int B_BUTTON = 2;
    public final static int X_BUTTON = 3;
    public final static int Y_BUTTON = 4;
    public final static int LB_BUTTON = 5;
    public final static int RB_BUTTON = 6;
    public final static int BACK_BUTTON = 7;
    public final static int START_BUTTON = 8;
    public final static int LEFT_JOY_BUTTON = 9;
    public final static int RIGHT_JOY_BUTTON = 10;
    public final static int LEFT_X_AXIS = 0;
    public final static int LEFT_Y_AXIS = 1;
    public final static int LT_AXIS = 2;
    public final static int RT_AXIS = 3;
    public final static int RIGHT_X_AXIS = 4;
    public final static int RIGHT_Y_AXIS = 5;
    public final static int POV_UP = 0;
    public final static int POV_UP_RIGHT = 45;
    public final static int POV_RIGHT = 90;
    public final static int POV_DOWN_RIGHT = 135;
    public final static int POV_DOWN = 180;
    public final static int POV_DOWN_LEFT = 225;
    public final static int POV_LEFT = 270;
    public final static int POV_UP_LEFT = 315;

	// Gamepad axis ports
	public static final int AXIS_LEFT_X = 0;
	public static final int AXIS_LEFT_Y = 1;
	public static final int AXIS_Left_SHOULDER_TRIGGER = 2;
	public static final int AXIS_Right_SHOULDER_TRIGGER = 3;
	public static final int AXIS_RIGHT_X = 4;
	public static final int AXIS_RIGHT_Y = 5;

	// Gamepad buttons
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int BUTTON_SHOULDER_LEFT_BUMPER = 5;
	public static final int BUTTON_SHOULDER_RIGHT_BUMPER = 6;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_START = 8;
	public static final int BUTTON_LEFT_STICK = 9;
	public static final int BUTTON_RIGHT_STICK = 10;

	// POV (D-Pad) buttons
	public static final int DPAD_UP = 0;
	public static final int DPAD_RIGHT = 90;
	public static final int DPAD_DOWN = 180;
	public static final int DPAD_LEFT = 270;

	// private static final int BUTTON_MODE = -1;
	// private static final int BUTTON_LOGITECH = -1;

	/**
	 * Default constructor
	 * 
	 * @param port
	 *            the port the joystick is plugged into on the DS.
	 */
	public XboxOne(int port) {
		super(port);
	}

	/**
	 * Returns the X position of the left stick.
	 * 
	 * @return Positive when pushing right on the stick (1.0 to -1.0).
	 */
	public double getLeftStickRaw_X() {
		return getRawAxis(AXIS_LEFT_X);
	}

	/**
	 * Returns the X position of the right stick.
	 * 
	 * @return Positive when pushing right on the stick (1.0 to -1.0).
	 */
	public double getRightStickRaw_X() {
		return getRawAxis(AXIS_RIGHT_X);
	}

	/**
	 * Returns the Y position of the left stick.
	 * 
	 * @return Positive when pushing up on the stick (1.0 to -1.0).
	 */
	public double getLeftStickRaw_Y() {
		return -getRawAxis(AXIS_LEFT_Y);
	}

	/**
	 * Returns the Y position of the right stick.
	 * 
	 * @return Positive when pushing up on the stick.
	 */
	public double getRightStickRaw_Y() {
		return -getRawAxis(AXIS_RIGHT_Y);
	}

	/**
	 * Returns the position of the shoulder trigger.
	 * 
	 * @return 1.0 to 0.0 (1.0 when depressed)
	 */
	public double getLeftTriggerAxisRaw() {
		return getRawAxis(AXIS_Left_SHOULDER_TRIGGER);
	}

	/**
	 * Returns the position of the shoulder trigger.
	 * 
	 * @return 1.0 to 0.0 (1.0 when depressed)
	 */
	public double getRightTriggerAxisRaw() {
		return getRawAxis(AXIS_Right_SHOULDER_TRIGGER);
	}

	/**
	 * Checks whether Button A is being pressed
	 * 
	 * @return true if pressed
	 */
	public boolean isPressedButtonA() {
		return getRawButton(BUTTON_A);
	}

	/**
	 * Checks whether Button B is being pressed
	 * 
	 * @return true if pressed
	 */
	public boolean isPressedButtonB() {
		return getRawButton(BUTTON_B);
	}

	/**
	 * Checks whether Button X is being pressed
	 * 
	 * @return true if pressed
	 */
	public boolean isPressedButtonX() {
		return getRawButton(BUTTON_X);
	}

	/**
	 * Checks whether Button Y is being pressed
	 * 
	 * @return true if pressed
	 */
	public boolean isPressedButtonY() {
		return getRawButton(BUTTON_Y);
	}

	public boolean isPressedButtonLeftBumper() {
		return getRawButton(BUTTON_SHOULDER_LEFT_BUMPER);
	}

	public boolean isPressedButtonRightBumper() {
		return getRawButton(BUTTON_SHOULDER_RIGHT_BUMPER);
	}

	public boolean isPressedButtonBack() {
		return getRawButton(BUTTON_BACK);
	}

	public boolean isPressedButtonStart() {
		return getRawButton(BUTTON_START);
	}

	public boolean isPressedButtonLeftStick() {
		return getRawButton(BUTTON_LEFT_STICK);
	}

	public boolean isPressedButtonRightStick() {
		return getRawButton(BUTTON_RIGHT_STICK);
	}

	public boolean isPressedButtonLeftTrigger() {
		return ButtonLeftTrigger().get();
	}

	public boolean isPressedButtonRightTrigger() {
		return ButtonRightTrigger().get();
	}

	/**
	 * Returns an object of Button A.
	 */
	public JoystickButton ButtonA() {
		return new JoystickButton(this, BUTTON_A);
	}

	/**
	 * Returns an object of Button B.
	 */
	public JoystickButton ButtonB() {
		return new JoystickButton(this, BUTTON_B);
	}

	/**
	 * Returns an object of Button X.
	 */
	public JoystickButton ButtonX() {
		return new JoystickButton(this, BUTTON_X);
	}

	/**
	 * Returns an object of Button Y.
	 */
	public JoystickButton ButtonY() {
		return new JoystickButton(this, BUTTON_Y);
	}

	/**
	 * Gets Start button object
	 * 
	 * @return the Start button
	 */
	public JoystickButton ButtonStart() {
		return new JoystickButton(this, BUTTON_START);
	}

	/**
	 * Gets the Back button object
	 * 
	 * @return the Back button
	 */
	public JoystickButton ButtonBack() {
		return new JoystickButton(this, BUTTON_BACK);
	}

	/**
	 * Gets the state of the left shoulder
	 * 
	 * @return the state of the left shoulder
	 */
	public JoystickButton ButtonLeftBumper() {
		return new JoystickButton(this, BUTTON_SHOULDER_LEFT_BUMPER);
	}

	/**
	 * Gets the state of the right shoulder
	 * 
	 * @return the state of the right shoulder
	 */
	public JoystickButton ButtonRightBumper() {
		return new JoystickButton(this, BUTTON_SHOULDER_RIGHT_BUMPER);
	}

	public JoystickButton ButtonLeftStick() {
		return new JoystickButton(this, BUTTON_LEFT_STICK);
	}

	public JoystickButton ButtonRightStick() {
		return new JoystickButton(this, BUTTON_RIGHT_STICK);
	}

	public JoystickAnalogButton ButtonLeftTrigger() {
		return new JoystickAnalogButton(this, AXIS_Left_SHOULDER_TRIGGER, 0.5);
	}

	public JoystickAnalogButton ButtonRightTrigger() {
		return new JoystickAnalogButton(this, AXIS_Right_SHOULDER_TRIGGER, 0.5);
	}

	public JoystickPOVButton ButtonUpDPad() {
		return new JoystickPOVButton(this, DPAD_UP);
	}

	public JoystickPOVButton ButtonRightDPad() {
		return new JoystickPOVButton(this, DPAD_RIGHT);
	}

	public JoystickPOVButton ButtonDownDPad() {
		return new JoystickPOVButton(this, DPAD_DOWN);
	}

	public JoystickPOVButton ButtonLeftDPad() {
		return new JoystickPOVButton(this, DPAD_LEFT);
	}
}