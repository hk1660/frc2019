package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class EncoderZero extends Command{
    
    public EncoderZero() {
        requires(Robot.m_elevatorWinch);
    }

    protected void execute() {
        Robot.m_elevatorWinch.zeroEncoder();
    }

    protected boolean isFinished() {
        return false; // Runs until interrupted
      }
}