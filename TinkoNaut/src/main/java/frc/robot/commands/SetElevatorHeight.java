/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
<<<<<<< HEAD
import frc.robot.subsystems.ElevatorWinchPID;
=======
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
>>>>>>> 9b900d085727f35732e6a46d93b449e083962b73
import frc.robot.Robot;

/**
 * Move the elevator to a given location. This command finishes when it is
 * within the tolerance, but leaves the PID loop running to maintain the
 * position. Other commands using the elevator should make sure they disable
 * PID!
 */
public class SetElevatorHeight extends Command {
  private double height = 0;

  public SetElevatorHeight(double height) { //height is the pid setpoint
    requires(Robot.m_elevatorWinch);
<<<<<<< HEAD
    this.height = height;
=======
    SmartDashboard.putNumber("Elev SetHeight", this.height);
    System.out.println("We're in setElevatorHegiht constructor");

>>>>>>> 9b900d085727f35732e6a46d93b449e083962b73
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
