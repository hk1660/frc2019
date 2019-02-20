/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveTurnToAngle;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;
//import frc.robot.auto_commands;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class AutoDriveStraight extends Command {

  //private final PIDController m_pid;
  private final int kWheelEncoderTolerance = 2000;
  private final double inchesForward;
  // private double turnAngle = 90.0;

  /**
   * Create a new DriveStraight command.
   * 
   * @param distance The distance to drive
   */

  public AutoDriveStraight(double inchesForward) {

    //requires(Robot.m_drivetrain);
    this.inchesForward = inchesForward;

    // m_pid = new PIDController(.5, 0, .2, new PIDSource() {
    //   PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

    //   @Override
    //   public double pidGet() {
    //     return Robot.m_drivetrain.getDistance();
    //     // return 0.0;
    //   }

    //   @Override
    //   public void setPIDSourceType(PIDSourceType pidSource) {
    //     m_sourceType = pidSource;
    //   }

    //   @Override
    //   public PIDSourceType getPIDSourceType() {
    //     return m_sourceType;
    //   }
    // }, forwardSpeed -> Robot.m_drivetrain.setForwardSpeed(forwardSpeed));

    // m_pid.setAbsoluteTolerance(kWheelEncoderTolerance);
    // m_pid.setSetpoint(Robot.m_drivetrain.getRawDistance(inchesForward));
  }


  
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drivetrain.zeroEncoder();
    System.out.println("Running AutoDriveStraight");
    // Get everything in a safe starting state.
    // Robot.m_drivetrain.reset();
    //m_pid.reset();
    //m_pid.enable();
  }

  @Override
  protected void execute() {
    super.execute();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // return false;

    double inchesWant = inchesForward; 
    double inchesHave = Robot.m_drivetrain.getDistance();
    return inchesHave>inchesWant;
    //return m_pid.onTarget();
  }

  @Override
  protected void execute() {

    Robot.m_drivetrain.setForwardSpeed(0.8);
    

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Stop PID and the wheels
<<<<<<< HEAD
    //m_pid.disable();
    //Robot.m_drivetrain.drive(0, 0, 0, 0);
    Robot.m_drivetrain.setForwardSpeed(0.0);
  }

 protected void log(){

  }
}
=======
    m_pid.disable();
    Robot.m_drivetrain.drive(0,0,0,0);
  } 
}
>>>>>>> e384d71bf2f91de2258d1126725179365fee14ce
