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
import frc.robot.RobotMap;
import frc.robot.subsystems.ElevatorWinchPID;

/**
 * Move the elevator to a given location. This command finishes when it is
 * within the tolerance, but leaves the PID loop running to maintain the
 * position. Other commands using the elevator should make sure they disable
 * PID!
 */
public class SetElevatorHeight extends Command {

  private double height = 0.0;

  public SetElevatorHeight(double height) { //height is the pid setpoint
    
    requires(Robot.m_elevatorWinch);
    this.height = height;
    // SmartDashboard.putNumber("Elev SetHeight", this.height);
    //System.out.println("We're in setElevatorHegiht constructor");
    

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    Robot.m_elevatorWinch.unlockPiston();
    SmartDashboard.putNumber("Elev SetHeight", this.height);
    //System.out.println("We're in setElevatorHegiht initialize()");
    
    Robot.m_elevatorWinch.getPIDController().reset(); //???
    Robot.m_elevatorWinch.getPIDController().enable();           //starts the pid loop
    Robot.m_elevatorWinch.setSetpoint(height);  //method call for all pid controllers
  
  }

  @Override
  protected void execute(){
    if(Robot.m_elevatorWinch.isLimitPressed()){
      Robot.m_elevatorWinch.lockPiston();
    }
  }

  //Ends the command if it's interrupted
  @Override
  protected void interrupted(){
    //Robot.m_elevatorWinch.getPIDController().disable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    //System.out.println("We're in setElevatorHegiht isFinished()");
    
    if(Robot.m_elevatorWinch.onTarget()){
      return true;
    } else if(!RobotMap.BB_FLAG && Math.abs(Robot.m_oi.getManipStick().getLeftStickRaw_Y()) > 0.2  ){
      return true;
    } else if(RobotMap.BB_FLAG && 
        (Robot.m_oi.getManipBoard().ButtonSeven().get() 
        || Robot.m_oi.getManipBoard().ButtonNine().get())){
      return true;
    }

    return false;

  }

  //no need for a execute method, because the pid controller will be
  //constantly making the elevator move to its proper position

    // Called once after isFinished returns true
    @Override
    protected void end() {
      // Stop PID and the wheels
      //m_pid.disable();
      //Robot.m_drivetrain.drive(0, 0, 0, 0);
      if(Robot.m_elevatorWinch.isLimitPressed()){
        Robot.m_elevatorWinch.lockPiston();
      }
    }
}
