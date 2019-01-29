/*----------------------------------------------------------------------------*/
/* @author: Haim Singh                                                              */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;


/**
 * The hatchPanelPanel subsystem is a simple system with a 2 pistons to push out 
 * the hatchPanel off of the velcro
 */
public class HatchPanelPanel extends Subsystem {

  private DoubleSolenoid hpPistonFixed;
  private DoubleSolenoid hpPistonElev;

  /**
   * Create a new claw subsystem.
   */
  public HatchPanelPanel() {
    super();

    hpPistonElev = new DoubleSolenoid(RobotMap.PISTON_IN_ELEV_CHANNEL, RobotMap.PISTON_OUT_ELEV_CHANNEL);
    hpPistonFixed = new DoubleSolenoid(RobotMap.PISTON_IN_FIXED_CHANNEL, RobotMap.PISTON_OUT_FIXED_CHANNEL);

    // Let's name everything on the LiveWindow
    addChild("hatchPanelFixed", hpPistonFixed);
    addChild("hatchPanelElev", hpPistonElev);

  }

  @Override
  public void initDefaultCommand() {
  }

  public void log() {
  }

  /**
   * Pushes hpPistons out
   */
  public void push() {

    hpPistonFixed.set(DoubleSolenoid.Value.kForward);
    hpPistonElev.set(DoubleSolenoid.Value.kForward);
    
  }
  /**
   * Pulls hpPistons in
   */
  public void pull() {

    hpPistonFixed.set(DoubleSolenoid.Value.kReverse);
    hpPistonElev.set(DoubleSolenoid.Value.kReverse);

  }
  
    /**
   * Retracts the hpPistons.
   */
  public void stop() {
    hpPistonFixed.set(DoubleSolenoid.Value.kOff);
    hpPistonElev.set(DoubleSolenoid.Value.kOff);
  
  }


}
