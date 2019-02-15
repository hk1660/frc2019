package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.utils.JoystickPovButton;
import frc.robot.utils.JoystickAnalogButton;

/**
 * Class to encapsulate all ButtonBoard functionality.
 *
 * @author KT & JAB
 */

public class ButtonBoard extends Joystick {

	// BB axes
	public static final int AXIS_LEFT_X = 0;
	public static final int AXIS_LEFT_Y = 1;

	// BB buttons
	public static final int BUTTON_ONE = 1; // eat
	public static final int BUTTON_TWO = 2; // spit
	public static final int BUTTON_THREE = 3; // hatch push
	public static final int BUTTON_FOUR = 4;// hatch pull
	public static final int BUTTON_FIVE = 5; // ground level
	public static final int BUTTON_SIX = 6; // level 1.5
	public static final int BUTTON_SEVEN = 7; // level 2
	public static final int BUTTON_EIGHT = 8; // level 2.5
	public static final int BUTTON_NINE = 9; // level 3 & LOCK
	public static final int BUTTON_TEN = 10; // level 3.5 & UNLOCK
	public static final int BUTTON_ELEVEN = 11;
	public static final int BUTTON_TWELVE = 12;

	// XboxOne Pov (D-Pad) buttons Manip
	public final static int POV_UP = 0;
	// public final static int POV_UP_RIGHT = 45;
	public final static int POV_RIGHT = 90;
	// public final static int POV_DOWN_RIGHT = 135;
	public final static int POV_DOWN = 180;
	// public final static int POV_DOWN_LEFT = 225;
	public final static int POV_LEFT = 270;
	// public final static int POV_UP_LEFT = 315;

	/**
	 * Default constructor
	 * 
	 * @param port the port the joystick is plugged into on the DS.
	 */
	public ButtonBoard(int port) {
		super(port);
	}

	/*----------------- AXIS ACCESSOR METHODS -------------------- */

	public boolean isPressedRightDirection() {
		if (getRawAxis(AXIS_LEFT_X) > 0.5) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPressedLeftDirection() {	
		if (getRawAxis(AXIS_LEFT_X) < -0.5) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPressedUpDirection() {
		if (getRawAxis(AXIS_LEFT_Y) < -0.5) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPressedDownDirection() {
		if (getRawAxis(AXIS_LEFT_Y) > 0.5) {
			return true;
		} else {
			return false;
		}
	}

	/*----------------- Button Accessor Methods -------------------- */

	/**
	 * Checks whether Button A is being pressed
	 * 
	 * @return true if pressed
	 */
	public boolean isPressedButtonOne() {
		return getRawButton(BUTTON_ONE);
	}

	/**
	 * Checks whether Button B is being pressed
	 * 
	 * @return true if pressed
	 */
	public boolean isPressedButtonTwo() {
		return getRawButton(BUTTON_TWO);
	}

	/**
	 * Checks whether Button X is being pressed
	 * 
	 * @return true if pressed
	 */
	public boolean isPressedButtonThree() {
		return getRawButton(BUTTON_THREE);
	}

	/**
	 * Checks whether Button Y is being pressed
	 * 
	 * @return true if pressed
	 */
	public boolean isPressedButtonFour() {
		return getRawButton(BUTTON_FOUR);
	}

	public boolean isPressedButtonFive() {
		return getRawButton(BUTTON_FIVE);
	}

	public boolean isPressedButtonSix() {
		return getRawButton(BUTTON_SIX);
	}

	public boolean isPressedButtonSeven() {
		return getRawButton(BUTTON_SEVEN);
	}

	public boolean isPressedButtonEight() {
		return getRawButton(BUTTON_EIGHT);
	}

	public boolean isPressedButtonNine() {
		return getRawButton(BUTTON_NINE);
	}

	public boolean isPressedButtonTen() {
		return getRawButton(BUTTON_TEN);
	}

	public boolean isPressedButtonEleven() {
		return getRawButton(BUTTON_ELEVEN);
	}

	public boolean isPressedButtonTwelve() {
		return getRawButton(BUTTON_TWELVE);
	}

	/*----------------- Button Objects -------------------- */

	public JoystickButton ButtonOne() {
		return new JoystickButton(this, BUTTON_ONE);
	}

	public JoystickButton ButtonTwo() {
		return new JoystickButton(this, BUTTON_TWO);
	}

	public JoystickButton ButtonThree() {
		return new JoystickButton(this, BUTTON_THREE);
	}

	public JoystickButton ButtonFour() {
		return new JoystickButton(this, BUTTON_FOUR);
	}

	public JoystickButton ButtonFive() {
		return new JoystickButton(this, BUTTON_EIGHT);
	}

	public JoystickButton ButtonSix() {
		return new JoystickButton(this, BUTTON_SEVEN);
	}

	public JoystickButton ButtonSeven() {
		return new JoystickButton(this, BUTTON_FIVE);
	}

	public JoystickButton ButtonEight() {
		return new JoystickButton(this, BUTTON_SIX);
	}

	public JoystickButton ButtonNine() {
		return new JoystickButton(this, BUTTON_NINE);
	}

	public JoystickButton ButtonTen() {
		return new JoystickButton(this, BUTTON_TEN);
	}

	public JoystickButton ButtonEleven() {
		return new JoystickButton(this, BUTTON_ELEVEN);
	}

	public JoystickButton ButtonTwelve() {
		return new JoystickButton(this, BUTTON_TWELVE);
	}

	/*----------------- Joystick Triggers (Analog Buttons) -------------------- */

	public JoystickAnalogButton ButtonUp() {
		return new JoystickAnalogButton(this, this.AXIS_LEFT_Y, -1);
	}

	public JoystickAnalogButton ButtonDown() {
		return new JoystickAnalogButton(this, this.AXIS_LEFT_Y, 1);
	}

	public JoystickAnalogButton ButtonLeft() {
		return new JoystickAnalogButton(this, this.AXIS_LEFT_X, -1);
	}

	public JoystickAnalogButton ButtonRight() {
		return new JoystickAnalogButton(this, this.AXIS_LEFT_X, 1);
	}

	/**/ 
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
}