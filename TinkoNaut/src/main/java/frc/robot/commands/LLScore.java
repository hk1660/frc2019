package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import  frc.robot.commands.DriveStraight;
import frc.robot.Robot;
import  frc.robot.subsystems.DriveTrain;

public class LLScore extends Command {
    int targetLeftThresh = 280;
    int targetRightThresh = 320;
    int targetAreaScore = 400;

    double strafeRightSpeed = 0.5;
    double strafeLeftSpeed = -strafeRightSpeed;
    public LLScore() {
        requires(Robot.m_limelight);
    }


    // Called just before this Command runs the first time
    @Override
    public void initialize(){
    }

      // Called repeatedly when this Command is scheduled to run
     @Override
     protected void execute(){
        if(Robot.m_limelight.getTXdouble() < targetLeftThresh) {
            Robot.m_drivetrain.drive(strafeRightSpeed,0.0,0.0,0.0);
        }
        else if(Robot.m_limelight.getTXdouble() > targetRightThresh){
                Robot.m_drivetrain.drive(strafeLeftSpeed,0.0,0.0,0.0);
        }
        else {
            System.out.println("Looks like there's no target, BOTTOM TEXT");
        }
        
        // if (Robot.m_limelight.getTXdouble() == targetLeftThresh - targetRightThresh &&  Robot.m_limelight.getXA != targetAreaScore) {
        //     Robot.m_drivetrain.drive(0.0,0.1,0.0,0.0);
        // }

    }
    // Make this return true when this Command no longer needs to run execute()
    @Override
        protected boolean isFinished() {
            return false;
        }
   // Called once after isFinished returns true
    @Override
        protected void end(){
        }

}
