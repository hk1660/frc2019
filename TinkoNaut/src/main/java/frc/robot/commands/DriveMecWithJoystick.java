/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

/**
 * Have the robot drive mecanum style using the XBOXONE Joystick until interrupted.
 */
public class DriveMecWithJoystick extends Command {
  public DriveMecWithJoystick() {
    requires(Robot.m_drivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drivetrain.driveJoystick(Robot.m_oi.getDriverStick());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false; // Runs until interrupted
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.drive(0, 0, 0, 0);
  }
}
