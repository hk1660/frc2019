/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Move the elevator to a given location. This command finishes when it is
 * within the tolerance, but leaves the PID loop running to maintain the
 * position. Other commands using the elevator should make sure they disable
 * PID!
 */
public class SetElevatorHeight extends Command {
  private final double height;

  public SetElevatorHeight(double height) { //height is the pid setpoint
    this.height = height;
    requires(Robot.m_elevatorWinch);
    SmartDashboard.putNumber("Elev SetHeight", this.height);
    System.out.println("We're in setElevatorHegiht constructor");

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("We're in setElevatorHegiht initialize()");
    Robot.m_elevatorWinch.enable();           //starts the pid loop
    Robot.m_elevatorWinch.setSetpoint(height);  //method call for all pid controllers
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println("We're in setElevatorHegiht isFinished()");
    return Robot.m_elevatorWinch.onTarget();
    

  }

  //no need for a execute method, because the pid controller will be
  //constantly making the elevator move to its proper position

}
