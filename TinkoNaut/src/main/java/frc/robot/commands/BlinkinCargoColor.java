/*----------------------------------------------------------------------------*/
/* @author: Aaron Emerenciano                                                           */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BlinkinCargoColor extends Command{

    public BlinkinCargoColor(){
        //Gotta use that pid subsystem B
        requires(Robot.m_blinkin);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize(){
    }

     // Called repeatedly when this Command is scheduled to run
     @Override
     protected void execute(){

        if(Robot.m_blinkin.hasCargo()){
            Robot.m_blinkin.setGreen();
        } else {
            Robot.m_blinkin.setRed();
        }

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

        }
}