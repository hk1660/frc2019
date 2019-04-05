package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.auto_commands.AutoDriveStraight;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class LLStrafe extends Command {
    
    double targetLeftThresh = -3.5;
    double targetRightThresh = -targetLeftThresh;
    int targetAreaScore = 400;

    double strafeRightSpeed = -0.5;
    double strafeLeftSpeed = -strafeRightSpeed;
    
    public LLStrafe() {
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
            Robot.m_drivetrain.setStrafeSpeed(strafeRightSpeed);
        }
        else if(Robot.m_limelight.getTXdouble() > targetRightThresh){
            Robot.m_drivetrain.setStrafeSpeed(strafeLeftSpeed);        }
        else {
            //System.out.println("on trarget???");
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end(){
        Robot.m_drivetrain.setStrafeSpeed(0.0);
    }

}
