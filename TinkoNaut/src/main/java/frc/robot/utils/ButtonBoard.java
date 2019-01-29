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
	
	//BB buttons
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
	// public final static int POV_UP = 0;
	// public final static int POV_UP_RIGHT = 45;
	// public final static int POV_RIGHT = 90;
	// public final static int POV_DOWN_RIGHT = 135;
	// public final static int POV_DOWN = 180;
	// public final static int POV_DOWN_LEFT = 225;
	// public final static int POV_LEFT = 270;
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
		if(getRawAxis(AXIS_LEFT_X) > 0.5){
			return true;
		} else {
			return false;
		}
	}

	public boolean isPressedLeftDirection() {
		if(getRawAxis(AXIS_LEFT_X) < -0.5){
			return true;
		} else {
			return false;
		}
	}

	public boolean isPressedUpDirection() {
		if(getRawAxis(AXIS_LEFT_Y) < -0.5){
			return true;
		} else {
			return false;
		}
	}

	public boolean isPressedDownDirection() {
		if(getRawAxis(AXIS_LEFT_Y) > 0.5){
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


}