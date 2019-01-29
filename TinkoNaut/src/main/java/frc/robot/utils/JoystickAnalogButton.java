package frc.robot.utils;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Trigger commands from an analog input on the joystick
 *
 * @author James@team2168.org
 */
public class JoystickAnalogButton extends Button {

	private GenericHID m_joystick;
	private int m_axisNumber;
	private double m_threshhold = 0.5;
	private int m_direction;

	/**
	 * Create a button for triggering commands off a joystick's analog axis
	 *
	 * @param joystick
	 *            The GenericHID object that has the button (e.g. Joystick,
	 *            KinectStick, etc)
	 * @param axisNumber
	 *            The axis number
	 */
	public JoystickAnalogButton(GenericHID joystick, int axisNumber, int direction) {
		m_joystick = joystick;
		m_axisNumber = axisNumber;
		m_direction = direction;
	}

	/**
	 * Create a button for triggering commands off a joystick's analog axis
	 *
	 * @param joystick
	 *            The GenericHID object that has the button (e.g. Joystick,
	 *            KinectStick, etc)
	 * @param axisNumber
	 *            The axis number
	 * @param threshold
	 *            The threshold to trigger above (positive) or below (negative)
	 */
	public JoystickAnalogButton(GenericHID joystick, int axisNumber, int direction, double threshold) {
		m_joystick = joystick;
		m_axisNumber = axisNumber;
		m_direction = direction;
		m_threshhold = threshold;
	}

	/**
	 * Set the value above which triggers should occur (for positive thresholds) or
	 * below which triggers should occur (for negative thresholds) The default
	 * threshold value is 0.5
	 *
	 * @param threshold
	 *            the threshold value (1 to -1)
	 */
	public void setThreshold(double threshold) {
		m_threshhold = threshold;
	}

	/**
	 * Get the defined threshold value.
	 * 
	 * @return the threshold value
	 */
	public double getThreshold() {
		return m_threshhold;
	}

	/**
	 * Get the state of the JoystickAnalogButton.
	 * 
	 * @return true when the analog value exceeds the specified threshold.
	 */
	public boolean get() {

		double rawAxisValue = m_joystick.getRawAxis(m_axisNumber);

		if (m_direction > 0) {
			// Return true if axis value is greater than positive threshold
			return  rawAxisValue > m_threshhold;
		} else {
			// Return true if axis value is less than negative threshold
			return rawAxisValue < -m_threshhold;
		}
	}

}