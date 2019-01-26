package frc.robot.utils;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Trigger commands from an DPAD/POV on the joystick
 *
 * @author Haim and Bianchi And Marlahna 
 */
public class JoystickPOVButton extends Button {

	GenericHID m_joystick;
	int m_povAngle;


	public JoystickPOVButton(GenericHID joystick, int POV_ANGLE) {
		m_joystick = joystick;
		m_povAngle = POV_ANGLE;
	}

	public boolean get() {
		return m_joystick.getPOV() == POV_ANGLE;
	}
}