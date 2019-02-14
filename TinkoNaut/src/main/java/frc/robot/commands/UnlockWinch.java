/*----------------------------------------------------------------------------*/
/* @author: Aaron Emerenciano                                                            */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class UnlockWinch extends Command{

    public UnlockWinch(){
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
         //Robot.m_elevatorWinchPID.unlock();
         Robot.m_elevatorWinch.unlock();

     }

      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished(){
          return false;
      }
       // Called once after isFinished returns true
       @Override
       protected void end(){
            Robot.m_elevatorWinch.stop();
           //Robot.m_elevatorWinchPID.stop();
       }

       // Called when another command which requires one or more of the same
       // subsystems is scheduled to run
       @Override
        protected void interrupted() {

            Robot.m_elevatorWinch.stop();
            //Robot.m_elevatorWinchPID.stop();
        }
}