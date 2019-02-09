package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import  frc.robot.commands.DriveStraight;
import frc.robot.Robot;
import  frc.robot.subsystems.DriveTrain;

public class LLScore extends Command {
    
    int targetLeftThresh = 280;
    int targetRightThresh = 320;
    int targetAreaScore = 400;

    public LLScore() {
        requires(robot.m_limelight);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize(){
    }

      // Called repeatedly when this Command is scheduled to run
     @Override
     protected void execute(){
        if (robot.m_limelight.getTX() >= targetLeftThresh && robot.m_limelight.getTX() <= targetRightThresh) {
            while((targetLeftThresh - robot.m_limelight.getTX()) < 0 && robot.m_limelight.getTX() != 0){
                drive(0.5,0.0,0.0,0.0);
            }
            while((targetRightThresh - robot.m_limelight.getTX()) > 0&& robot.m_limelight.getTX() != 0){
                drive(-0.5,0.0,0.0,0.0);
            }
        }
        if (robot.m_limelight.getTX() == targetLeftThresh - targetRightThresh && robot.m_limelight && robot.m_limelight.getXA != targetAreaScore) {
            drive(0.0,0.1,0.0,0.0);
        }

    }
    // Make this return true when this Command no longer needs to run execute()
    @Override
        protected boolean isFinished() {
        }
   // Called once after isFinished returns true
    @Override
        protected void end(){
        }

}
