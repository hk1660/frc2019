/*----------------------------------------------------------------------------*/
/* @author: Haim Singh                                                              */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;


/**
 * The hatchPanelPanel subsystem is a simple system with a 2 pistons to push out 
 * the hatchPanel off of the velcro
 */
public class HatchPanelPanel extends Subsystem {

  private DoubleSolenoid hpPiston;

  /**
   * Create a new claw subsystem.
   */
  public HatchPanelPanel() {
    super();

    hpPiston = new DoubleSolenoid(1, 2);

    // Let's name everything on the LiveWindow
    addChild("HatchPanelPanel", hpPiston);
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

    
    hpPiston.set(DoubleSolenoid.Value.kForward);
    
    
  }
  /**
   * Pulls hpPistons in
   */
  public void pull() {


    hpPiston.set(DoubleSolenoid.Value.kReverse);


  }
  
    /**
   * Retracts the hpPistons.
   */
  public void stop() {
    hpPiston.set(DoubleSolenoid.Value.kOff);
  }


}
