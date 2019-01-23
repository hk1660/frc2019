/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
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

    hpPiston.set(DoubleSolenoid.Value.kOff);
    hpPiston.set(DoubleSolenoid.Value.kForward);
    hpPiston.set(DoubleSolenoid.Value.kReverse);
  }

  /**
   * Retracts the hpPistons.
   */
  public void stop() {
    /m_motor.set(0);
  }


}
