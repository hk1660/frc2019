package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class TurnToAngle extends Command{
    private final PIDController m_pid;


    public TurnToAngle(double distance){


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
        }, d -> Robot.m_drivetrain.drive(0, d,0,0));
    
        m_pid.setAbsoluteTolerance(0.01);
        m_pid.setSetpoint(distance);
      }

      protected void initialize() {
        // Get everything in a safe starting state.
        Robot.m_drivetrain.reset();
        m_pid.reset();
        m_pid.enable();
      }
    

    }
    

}