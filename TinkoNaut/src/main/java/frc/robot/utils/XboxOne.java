package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.utils.JoystickPovButton;
import frc.robot.utils.JoystickAnalogButton;

/**
 * Class to encapsulate all XboxOne functionality. No need to have multiple
 * copies in OI all the time. Revised from team2168.org's F310 Class.
 *
 * @author Aldecai
 */

public class XboxOne extends Joystick {

	// XBoxOne axes
	public static final int AXIS_LEFT_X = 0;
	public static final int AXIS_LEFT_Y = 1;
	public static final int AXIS_LEFT_TRIGGER = 2;
	public static final int AXIS_RIGHT_TRIGGER = 3;
	public static final int AXIS_RIGHT_X = 4;
	public static final int AXIS_RIGHT_Y = 5;

	// XBoxOne buttons
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int BUTTON_LB = 5;
	public static final int BUTTON_RB = 6;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_START = 8;
	public static final int BUTTON_LEFT_JOY = 9;
	public static final int BUTTON_RIGHT_JOY = 10;

	// XboxOne Pov (D-Pad) buttons Manip
	public final static int POV_UP = 0;
	public final static int POV_UP_RIGHT = 45;
	public final static int POV_RIGHT = 90;
	public final static int POV_DOWN_RIGHT = 135;
	public final static int POV_DOWN = 180;
	public final static int POV_DOWN_LEFT = 225;
	public final static int POV_LEFT = 270;
	public final static int POV_UP_LEFT = 315;


	/**
	 * Default constructor
	 * 
	 * @param port the port the joystick is plugged into on the DS.
	 */
	public XboxOne(int port) {
		super(port);
	}

	/*----------------- AXIS ACCESSOR METHODS -------------------- */

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
		return getRawAxis(AXIS_LEFT_TRIGGER);
	}

	/**
	 * Returns the position of the shoulder trigger.
	 * 
	 * @return 1.0 to 0.0 (1.0 when depressed)
	 */
	public double getRightTriggerAxisRaw() {
		return getRawAxis(AXIS_RIGHT_TRIGGER);
	}

	/*----------------- Button Accessor Methods -------------------- */

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

	public boolean isPressedButtonLB() {
		return getRawButton(BUTTON_LB);
	}

	public boolean isPressedButtonRB() {
		return getRawButton(BUTTON_RB);
	}

	public boolean isPressedButtonBack() {
		return getRawButton(BUTTON_BACK);
	}

	public boolean isPressedButtonStart() {
		return getRawButton(BUTTON_START);
	}

	public boolean isPressedButtonLeftJoy() {
		return getRawButton(BUTTON_LEFT_JOY);
	}

	public boolean isPressedButtonRightJoy() {
		return getRawButton(BUTTON_RIGHT_JOY);
	}

	public boolean isPressedButtonLeftTrigger() {
		return ButtonLeftTrigger().get();
	}

	public boolean isPressedButtonRightTrigger() {
		return ButtonRightTrigger().get();
	}

	public boolean isPressedPovUp() {
		return ButtonPovUp().get();
	}


	/*----------------- Button Objects -------------------- */

	public JoystickButton ButtonA() {
		return new JoystickButton(this, BUTTON_A);
	}

	public JoystickButton ButtonB() {
		return new JoystickButton(this, BUTTON_B);
	}

	public JoystickButton ButtonX() {
		return new JoystickButton(this, BUTTON_X);
	}

	public JoystickButton ButtonY() {
		return new JoystickButton(this, BUTTON_Y);
	}

	public JoystickButton ButtonStart() {
		return new JoystickButton(this, BUTTON_START);
	}

	public JoystickButton ButtonBack() {
		return new JoystickButton(this, BUTTON_BACK);
	}

	public JoystickButton ButtonLB() {
		return new JoystickButton(this, BUTTON_LB);
	}

	public JoystickButton ButtonRB() {
		return new JoystickButton(this, BUTTON_RB);
	}

	public JoystickButton ButtonLeftJoy() {
		return new JoystickButton(this, BUTTON_LEFT_JOY);
	}

	public JoystickButton ButtonRightJoy() {
		return new JoystickButton(this, BUTTON_RIGHT_JOY);
	}

	/*----------------- Joystick Triggers (Analog Buttons) -------------------- */

	public JoystickAnalogButton ButtonLeftTrigger() {
		return new JoystickAnalogButton(this, AXIS_LEFT_TRIGGER, 1);
	}

	public JoystickAnalogButton ButtonRightTrigger() {
		return new JoystickAnalogButton(this, AXIS_RIGHT_TRIGGER, 1);
	}

	/* --------------------Pov Buttons -Marlahna & Haim -------------------------*/

	public JoystickPovButton ButtonPovUp() {
		return new JoystickPovButton(this, POV_UP);
	}

	public JoystickPovButton ButtonPovRight() {
		return new JoystickPovButton(this, POV_RIGHT);
	}

	public JoystickPovButton ButtonPovDown() {
		return new JoystickPovButton(this, POV_DOWN);
	}

	public JoystickPovButton ButtonPovLeft() {
		return new JoystickPovButton(this, POV_LEFT);
	}

	public JoystickPovButton ButtonPovUpRight() {
		return new JoystickPovButton(this, POV_UP_RIGHT);
	}

	public JoystickPovButton ButtonPovUpLeft() {
		return new JoystickPovButton(this, POV_UP_LEFT);
	}

	public JoystickPovButton ButtonPovDownRight() {
		return new JoystickPovButton(this, POV_DOWN_RIGHT);
	}

	public JoystickPovButton ButtonPovDownLeft() {
		return new JoystickPovButton(this, POV_DOWN_LEFT);
	}

}