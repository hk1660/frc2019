package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.DriveTrain;

public class AutoTurn extends Command{
    private final PIDController m_pid;

    public AutoTurn(double angle){
        requires(Robot.m_drivetrain);

    m_pid = new PIDController(2, 0, 0, new PIDSource() {
      PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

      @Override
      public double pidGet() {
        return Robot.m_drivetrain.getDistance();
    
      }

      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
        m_sourceType = pidSource;
      }

      @Override
      public PIDSourceType getPIDSourceType() {
        return m_sourceType;
      }
    }, d -> Robot.m_drivetrain.drive(0.0,0,1.0,0));
 

    m_pid.setAbsoluteTolerance(0.01);
    m_pid.setSetpoint(angle);
  }

   // Called just before this Command runs the first time
   @Override
   protected void initialize() {
     // Get everything in a safe starting state.
     Robot.m_drivetrain.zeroEncoder();

     m_pid.reset();
     m_pid.enable();
   }

  @Override
  protected boolean isFinished() {
    return false;
  }
  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Stop PID and the wheels
    m_pid.disable();
    Robot.m_drivetrain.drive(0, 0,0,0);
  }

  }