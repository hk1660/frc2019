package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorPID extends Command {

  private final Double m_setpoint;
  
    public ElevatorPID(double setpoint) {
        requires(Robot.m_elevatorWinchPID);
        
        m_setpoint = setpoint;
    } 

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      Double current = Robot.m_elevatorWinchPID.returnPIDInput();
      while(m_setpoint != current) {
        if(m_setpoint > current) {
            Robot.m_elevatorWinchPID.moveWinch(-1.0);    
        } else {
            Robot.m_elevatorWinchPID.moveWinch(1.0);
        }
        end();  
      }
     }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
      Robot.m_elevatorWinchPID.moveWinch(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
      Robot.m_elevatorWinchPID.moveWinch(0.0);
  }
}	