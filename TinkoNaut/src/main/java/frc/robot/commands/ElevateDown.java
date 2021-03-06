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
 * Elevator goes up manually with a button push
 */
public class ElevateDown extends Command {
  public ElevateDown() {
    requires(Robot.m_elevatorWinch);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_elevatorWinch.getPIDController().disable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      Robot.m_elevatorWinch.elevateManual(-1.0);
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
