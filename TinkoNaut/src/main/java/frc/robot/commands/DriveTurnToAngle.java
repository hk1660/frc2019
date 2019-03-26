package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveTurnToAngle extends Command {

  private final PIDController m_pid;

  private double kp = 0.05;//.05?
  private double ki = 0.0;
  private double kd = 0.1;
  private double kToleranceDegrees = 3.0; // how off can a turn be?
  private float kMinAngle = -180.0f;
  private float kMaxAngle = 180.0f;
  private double setAngle;

  public DriveTurnToAngle(double angle) {

    //requires(Robot.m_drivetrain);
    this.setAngle = angle;
    SmartDashboard.putNumber("Set angle", setAngle);
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

  /*@Override
  protected void interrupted() {
    System.out.println("Interru");
    m_pid.disable();
  }*/

  protected void initialize() {
    // Get everything in a safe starting state.
    // Robot.m_drivetrain.reset();
    RobotMap.NAVX_TURN_FLAG = true;
    m_pid.reset();
    m_pid.enable();

    kp = SmartDashboard.getNumber("Drive kp", kp);
    ki = SmartDashboard.getNumber("Drive ki", ki);
    kd = SmartDashboard.getNumber("Drive kd", kd);
    //.out.println("P:" + kp + " I:" + ki + " D:" + kd);
    m_pid.setPID(kp, ki, kd);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return m_pid.onTarget() || !RobotMap.NAVX_TURN_FLAG; // change to m_pid
  }

  @Override
  protected void execute() {
    
    SmartDashboard.putNumber("Set angle", setAngle);
    

    //Robot.m_drivetrain.setForwardSpeed(Robot.m_oi.getDriverStick().getRightStickRaw_Y());
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
    m_pid.disable();
    Robot.m_drivetrain.setForwardSpeed(0.0);
    Robot.m_drivetrain.setTurnSpeed(0.0);

  }

}