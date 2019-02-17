package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class DriveAngleZero extends Command{
    
    public DriveAngleZero() {
        requires(Robot.m_navx);
    }

    protected void execute() {
        Robot.m_navx.zeroAngle();
    }

    protected boolean isFinished() {
        return false; // Runs until interrupted
      }
}