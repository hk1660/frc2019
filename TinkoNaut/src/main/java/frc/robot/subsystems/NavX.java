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

public class NavX extends Subsystem{


	 static  double starterAngle;
	 //static double actualAngle;

	private static AHRS navx;
	//double rotateToAngleRate;

	public NavX() {
		// navx intialization
		try {
			
			navx = new AHRS(SPI.Port.kMXP); // navX-MXP initialized with (SPI, I2C, TTL UART) and USB
											// //http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
											
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
		
		zeroAngle();		
	}
	
	public static void zeroAngle(){
		//navx.zeroYaw();
		starterAngle = navx.getAngle();

	}

	//returns the angle the robot has changed since start of match in a -180 to 180 range
	public static double getCurrentAngle() {	
		double currentAngle = (starterAngle - navx.getAngle() ) % 360;
		if(currentAngle > 180.0){
			currentAngle -= 360.0;
		}
		return currentAngle;
	}

    @Override
    public void initDefaultCommand() {
	}
	
	public void log() {
		SmartDashboard.putNumber("navxStarterAngle", starterAngle);		
		SmartDashboard.putNumber("navxRawAngle", navx.getAngle());		
		SmartDashboard.putNumber("navxCurrentAngle", getCurrentAngle());
		
	}

}




