/*----------------------------------------------------------------------------*/
/* @author: Aaron Emerenciano                                                           */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleLL extends Command{

    public ToggleLL(){
        //Gotta use that pid subsystem B
        //requires(Robot.m_elevatorWinch);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize(){
    }

     // Called repeatedly when this Command is scheduled to run
     @Override
     protected void execute(){
         RobotMap.LL_FLAG= true;
     }

      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished(){
          return false;
      }
       // Called once after isFinished returns true
       @Override
       protected void end(){
            RobotMap.LL_FLAG = false;
       }

       // Called when another command which requires one or more of the same
       // subsystems is scheduled to run
       @Override
        protected void interrupted() {

        }
}