/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Make sure the robot is in a state to pickup Cargo
 */
public class PrepareToPickup extends CommandGroup {
  /**
   * Create a new prepare to pickup command.
   */
  public PrepareToPickup() {
    addSequential(new SetElevatorHeight(RobotMap.LEVEL_1));
    addParallel(new EatCargo(), 3.0);
    addParallel(new TonsilEat(), 3.0);
    
  }
}
