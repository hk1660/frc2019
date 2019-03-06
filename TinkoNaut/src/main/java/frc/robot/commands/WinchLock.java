/*----------------------------------------------------------------------------*/
/* @author: Aaron Emerenciano                                                           */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class WinchLock extends Command{

    public WinchLock(){
        //Gotta use that pid subsystem B
        requires(Robot.m_elevatorWinch);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize(){
    }

      // Called repeatedly when this Command is scheduled to run
      @Override
      protected void execute(){
        Robot.m_elevatorWinch.lockPiston();
        RobotMap.LOCK_FLAG = true;
      }
 
       // Make this return true when this Command no longer needs to run execute()
       @Override
       protected boolean isFinished(){
           return false;
       }
        // Called once after isFinished returns true
        @Override
        protected void end(){

        }
 
        // Called when another command which requires one or more of the same
        // subsystems is scheduled to run
        @Override
         protected void interrupted() {
             //Robot.m_elevatorWinch.lockPiston();
         }
}