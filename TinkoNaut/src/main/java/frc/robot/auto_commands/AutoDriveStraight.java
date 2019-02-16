/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.TurnToAngle;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class AutoDriveStraight extends Command {
  private final PIDController m_pid;
  private final int kWheelEncoderTolerance = 2000;
 // private double turnAngle = 90.0;

  /**
   * Create a new DriveStraight command.
   * @param distance The distance to drive
   */
  
  public AutoDriveStraight(double distance) {
    requires(Robot.m_drivetrain);
    m_pid = new PIDController(2, 0, 0, new PIDSource() {
      PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

      @Override
      public double pidGet() {
        return Robot.m_drivetrain.getDistance();
        //return 0.0;
      }

      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
        m_sourceType = pidSource;
      }

      @Override
      public PIDSourceType getPIDSourceType() {
        return m_sourceType;
      }
    }, d -> Robot.m_drivetrain.drive(0, 1.0,0,0));
 

    m_pid.setAbsoluteTolerance(kWheelEncoderTolerance);
    m_pid.setSetpoint(distance);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Get everything in a safe starting state.
    //Robot.m_drivetrain.reset();
    m_pid.reset();
    m_pid.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return m_pid.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Stop PID and the wheels
    m_pid.disable();
    Robot.m_drivetrain.drive(0, 0,0,0);
  }

  
}
