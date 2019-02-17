/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * The main autonomous command to pickup and deliver the soda to the box.
 */
public class Autonomous extends CommandGroup {
  /**
   * Create a new autonomous command.
   */
  public Autonomous() {
   //addSequential(new PrepareToPickup());
    // addSequential(new Pickup());
     //addSequential(new SetDistanceToBox(0.10));
     addSequential(new AutoDriveStraight(3.0)); // Use Encoders if ultrasonic is
     addSequential(new AutoTurn(90.0));
     addSequential(new SetElevatorHeight((RobotMap.LEVEL_3)));
    //addSequential(new EatCargo());
    //addSequential(new TonsilEat());
    // broken
    // addSequential(new Place());
    // addSequential(new SetDistanceToBox(0.60));
    // addSequential(new DriveStraight(-2)); // Use Encoders if ultrasonic
    // is broken
    // addParallel(new SetWristSetpoint(-45));
    // addSequential(new CloseClaw());
    
  }
}
