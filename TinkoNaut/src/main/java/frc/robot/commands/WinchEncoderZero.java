package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class WinchEncoderZero extends Command{
    
    public WinchEncoderZero() {
        requires(Robot.m_elevatorWinch);
    }

    protected void execute() {
        Robot.m_elevatorWinch.zeroEncoder();
    }

    protected boolean isFinished() {
        return false; // Runs until interrupted
      }
}