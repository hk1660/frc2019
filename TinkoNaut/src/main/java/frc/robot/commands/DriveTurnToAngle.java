package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveTurnToAngle extends Command {

  private final PIDController m_pid;

  private double kp = .001;
  private double ki = 0.0;
  private double kd = 0.0;
  private double kToleranceDegrees = 1.0; // how off can a turn be?
  private float kMinAngle = -180.0f;
  private float kMaxAngle = 180.0f;

  public DriveTurnToAngle(double angle) {

    requires(Robot.m_drivetrain);
    SmartDashboard.putNumber("angle", angle);
    SmartDashboard.putNumber("Drive kp", kp);
    SmartDashboard.putNumber("Drive ki", ki);
    SmartDashboard.putNumber("Drive kd", kd);

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
    }, turn -> Robot.m_drivetrain.setTurnSpeed(turn));

    m_pid.setAbsoluteTolerance(kToleranceDegrees);
    m_pid.setInputRange(kMinAngle, kMaxAngle);
    m_pid.setOutputRange(-1.0, 1.0);
    m_pid.setContinuous(true);        //-180 value turns into +180

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

  @Override
  protected void execute() {

    kp = SmartDashboard.getNumber("Drive kp", kp);
    ki = SmartDashboard.getNumber("Drive ki", ki);
    kd = SmartDashboard.getNumber("Drive kd", kd);
    System.out.println("P:" + kp + " I:" + ki + " D:" + kd);
    m_pid.setPID(kp, ki, kd);

    Robot.m_drivetrain.setForwardSpeed(Robot.m_oi.getDriverStick().getRightStickRaw_Y());
    super.execute();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    Robot.m_drivetrain.setForwardSpeed(0.0);
    m_pid.disable();

  }

}