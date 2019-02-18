/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Command to take in cargo from ground
 */
public class DriveCombo extends CommandGroup {
  /**
   * Create a new autonomous command.
   */
  public DriveCombo(double turnAngle, boolean limelightTarget) {
    
    addParallel(new DriveTurnToAngle(turnAngle));

    if(limelightTarget){
      addParallel(new LLStrafe());
    } 
  }
}
