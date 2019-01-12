/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
/*-----IMPORTED LIBRARIES-----*/
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class HKDrive extends Subsystem implements PIDOutput {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
//Drivetrain Declarations
private WPI_TalonSRX frontLeft;
private WPI_TalonSRX backLeft;
private WPI_TalonSRX frontRight;
private WPI_TalonSRX backRight;
private MecanumDrive mecDrive;
PIDController turnController;
PowerDistributionPanel pdp;
private AHRS navx;

public void mecDrive(double strafeParameter, double forwardParameter, double turnParameter, boolean alignToField ) {
  driveCartesian(strafeParameter, forwardParameter, turnParameter, alignToField);

}



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
