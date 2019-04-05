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
import frc.robot.commands.DriveEncoderZero;

/**
 * The main autonomous command to pickup and deliver the soda to the box.
 */
public class Autonomous extends CommandGroup {
  /**
   * Create a new autonomous command.
   */
  public Autonomous() {
   
    //System.out.println("testing");
    //  addSequential(new DriveEncoderZero());
     addSequential(new AutoDriveStraight(60)); 
    // addParallel(new DriveTurnToAngle(RobotMap.FRONT_WALL_ANGLE));
    addSequential(new DriveTurnToAngle(RobotMap.RIGHT_WALL_ANGLE)); //turn 90.0
      addSequential(new AutoDriveStraight(27.0));
      addSequential(new DriveTurnToAngle(RobotMap.RIGHT_ROCKET_FRONT_ANGLE)); //-90.0
      addSequential(new AutoDriveStraight(6.0));
    //  addSequential(new DriveTurnToAngle(RobotMap.RIGHT_WALL_ANGLE )); //90.0
    //  addSequential(new AutoDriveStraight(1.5));
    //  //addSequential(new DriveTurnToAngle(90.0));
     //addSequential(new SetElevatorHeight((RobotMap.LEVEL_3)));
    //addSequential(new EatCargo());
    //addSequential(new TonsilEat());
    // broken
    // addSequential(new DriveStraight(-2)); // Use Encoders if ultrasonic
    // is broken
    
  }
}
