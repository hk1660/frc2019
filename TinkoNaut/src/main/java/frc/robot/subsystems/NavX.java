package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.kauailabs.navx.frc.AHRS;


public class NavX implements PIDOutput { //extends PIDSubsystem

	private static AHRS navx;
	double rotateToAngleRate;

	public NavX(){
		//navx intialization
		try {
			navx = new AHRS(SPI.Port.kMXP); //navX-MXP initialized with (SPI, I2C, TTL UART) and USB //http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}		
	}
	
	public static int getCurrentAngle(){
			int rawAngle = (int) (navx.getAngle());
			SmartDashboard.putNumber("rawAngle", rawAngle);
			return rawAngle;
		}

		public void pidWrite(double output) {
			rotateToAngleRate = output;
		}
}
