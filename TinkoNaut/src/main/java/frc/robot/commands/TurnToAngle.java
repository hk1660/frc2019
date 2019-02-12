package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TurnToAngle extends Command {

  private final PIDController m_pid;

  private double kp = 1.0;
  private double ki = 0.1;
  private double kd = 0.1;

  public TurnToAngle(double angle, double strafeSpeed, double forwardSpeed) {

    requires(Robot.m_drivetrain);
    SmartDashboard.putNumber("angle", angle);

    m_pid = new PIDController(kp, ki, kd, new PIDSource() {
      PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

      @Override
      public double pidGet() {
        return Robot.m_navx.getCurrentAngle();

      }

      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
        m_sourceType = pidSource;
      }

      @Override
      public PIDSourceType getPIDSourceType() {
        return m_sourceType;
      }
    }, turn -> Robot.m_drivetrain.drive(strafeSpeed, forwardSpeed, turn, 0)); // mayg3 pass thru other argument too

    m_pid.setAbsoluteTolerance(1.0);
    m_pid.setSetpoint(angle);
  }

  protected void initialize() {
    // Get everything in a safe starting state.
    // Robot.m_drivetrain.reset();
    m_pid.reset();
    m_pid.enable();

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return m_pid.onTarget(); // change to m_pid
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    m_pid.disable();

  }

}