/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team1660.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1660.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveJoystick extends Command {
  public DriveJoystick() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.hkDrive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double forward = Robot.oi.driverStick.getRightStickRaw_Y();
    double turn = Robot.oi.driverStick.getLeftStickRaw_X();
    double strafe = Robot.oi.driverStick.getRightStickRaw_X();
    double angle = 0.0;

    Robot.hkDrive.drive(strafe, forward, turn, angle);
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    Robot.hkDrive.drive(0.0, 0.0, 0.0, 0.0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.hkDrive.drive(0.0, 0.0, 0.0, 0.0);
  }
}
