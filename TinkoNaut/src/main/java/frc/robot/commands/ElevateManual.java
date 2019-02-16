/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class ElevateManual extends Command {
  public ElevateManual() {
    requires(Robot.m_elevatorWinch);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if( RobotMap.BB_FLAG){
      Robot.m_elevatorWinch.elevateManual(Robot.m_oi.getManipBoard().getRawAxis(0));
    } else {
      Robot.m_elevatorWinch.elevateManual(Robot.m_oi.getManipStick().getRightStickRaw_Y());
    }
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false; // Runs until interrupted
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_elevatorWinch.elevateManual(0.0);
  }
}