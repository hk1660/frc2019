package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class DriveEncoderZero extends Command{
    
    public DriveEncoderZero() {
        requires(Robot.m_drivetrain);
    }

    protected void execute() {
        Robot.m_drivetrain.zeroEncoder();
    }

    protected boolean isFinished() {
        return false; // Runs until interrupted
      }
}