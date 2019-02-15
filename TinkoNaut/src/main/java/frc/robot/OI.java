/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.auto_commands.Autonomous;
import frc.robot.commands.PistIn;
import frc.robot.commands.PistOut;
import frc.robot.commands.EatCargo;
import frc.robot.commands.SpitCargo;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.SetElevatorHeight;
import frc.robot.commands.TonsilEat;
import frc.robot.commands.TonsilSpit;
import frc.robot.commands.ElevateManual;
import frc.robot.commands.LockWinch;
import frc.robot.commands.UnlockWinch;
import frc.robot.commands.LLScore;
import frc.robot.commands.ToggleLL;
import frc.robot.commands.EncoderZero;

import frc.robot.utils.XboxOne;
import frc.robot.utils.JoystickPovButton;
import frc.robot.utils.ButtonBoard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  // make 2 joysticks for the robot driving & operation
  public static XboxOne driverStick;
  public static XboxOne manipStick;
  public static ButtonBoard manipBoard;

  /** Construct the OI and all of the buttons on it.
   */
  public OI() {
      
    //--------------JOYSTICKS----------------------------------//
    driverStick = new XboxOne(RobotMap.DRIVER_JOYSTICK_PORT);
    if(RobotMap.BB_FLAG){
      manipBoard = new ButtonBoard(RobotMap.MANIPULATOR_JOYSTICK_PORT);
    } else {
      manipStick = new XboxOne(RobotMap.MANIPULATOR_JOYSTICK_PORT);
    } 
  
    //-------------MANIPULATOR BUTTONS -------------------------//
    if (RobotMap.BB_FLAG) {

      /* Buttons 
      Button 1 - Eat Cargo
      Button 2 - Spit Cargo
      Button 3 - Hatch Pull
      Button 4 - Hatch Push
      Button 5 - Tonsil Spit
      Button 6 - Tonsil Eat
      Button 7 - Level 1
      Button 8 - Level 1.5
      Button 9 - Level 2
      Button 10 - Level 2.5
      Button 11 - Level 3
      Button 12 - 3.5
      
      Button POV L - Unlock
      Button POV R - Lock
      Button POV D - Encoder Zero
      Button POV U - ????
      */

      manipBoard.ButtonOne().whileHeld(new EatCargo());
      manipBoard.ButtonTwo().whileHeld(new SpitCargo());
      manipBoard.ButtonThree().whileHeld(new PistIn());
      manipBoard.ButtonFour().whileHeld(new PistOut());
      manipBoard.ButtonFive().whileHeld(new TonsilSpit());
      manipBoard.ButtonSix().whileHeld(new TonsilEat());

      //manipBoard.ButtonRight().whileHeld(new ElevateManual());
      //manipBoard.ButtonLeft().whileHeld(new ElevateManual());
      // tonsils CHANGE BUTTONS LATER 
    
      // manipBoard.ButtonFive().whileHeld(new TonsilEat());
      // manipBoard.ButtonTwo().whileHeld(new TonsilSpit());
      
      // lock mechannims
      // Need to be changed
      // manipBoard.ButtonNine().whenPressed(new LockWinch()); 
      // manipBoard.ButtonTen().whenPressed(new UnlockWinch()); 
      // manipBoard.ButtonEight().whenPressed(new EncoderZero());


      // Elevator Buttons
      manipBoard.ButtonSeven().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1));
      manipBoard.ButtonEight().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1_5));
      manipBoard.ButtonNine().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2));
      manipBoard.ButtonTen().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2_5));
      manipBoard.ButtonEleven().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3));
      manipBoard.ButtonTwelve().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3_5));

    } else {
      manipStick.ButtonA().whileHeld(new PistIn());
      manipStick.ButtonB().whileHeld(new PistOut());
      manipStick.ButtonLB().whileHeld(new EatCargo());
      manipStick.ButtonLeftTrigger().whileHeld(new SpitCargo());
      manipStick.ButtonX().whileHeld(new TonsilEat());
      manipStick.ButtonY().whileHeld(new TonsilSpit());

    }

    //-------------DRIVER BUTTONS -------------------------//
    
    driverStick.ButtonA().whileHeld(new ToggleLL());

    if(RobotMap.LL_FLAG){
      driverStick.ButtonPovRight().whileHeld(new TurnToAngle(RobotMap.RIGHT_WALL_ANGLE, Robot.m_limelight.getStrafeToTargetSpeed(),0));
      driverStick.ButtonPovLeft().whileHeld(new TurnToAngle(RobotMap.LEFT_WALL_ANGLE, Robot.m_limelight.getStrafeToTargetSpeed(),0));
      driverStick.ButtonPovUp().whileHeld(new TurnToAngle(RobotMap.BACK_WALL_ANGLE, Robot.m_limelight.getStrafeToTargetSpeed(),0));
      driverStick.ButtonPovDown().whileHeld(new TurnToAngle(RobotMap.FRONT_WALL_ANGLE,Robot.m_limelight.getStrafeToTargetSpeed(),0));
      driverStick.ButtonPovUpRight().whileHeld(new TurnToAngle(RobotMap.RIGHT_ROCKET_FRONT_ANGLE, Robot.m_limelight.getStrafeToTargetSpeed(),0));
      driverStick.ButtonPovDownRight().whileHeld(new TurnToAngle(RobotMap.RIGHT_ROCKET_BACK_ANGLE, Robot.m_limelight.getStrafeToTargetSpeed(),0));
      driverStick.ButtonPovUpLeft().whileHeld(new TurnToAngle(RobotMap.LEFT_ROCKET_FRONT_ANGLE, Robot.m_limelight.getStrafeToTargetSpeed(),0));
      driverStick.ButtonPovDownLeft().whileHeld(new TurnToAngle(RobotMap.LEFT_ROCKET_BACK_ANGLE, Robot.m_limelight.getStrafeToTargetSpeed(),0));
    }else{
      driverStick.ButtonPovRight().whileHeld(new TurnToAngle(RobotMap.RIGHT_WALL_ANGLE, 0, 0));
      driverStick.ButtonPovLeft().whileHeld(new TurnToAngle(RobotMap.LEFT_WALL_ANGLE, 0, 0));
      driverStick.ButtonPovUp().whileHeld(new TurnToAngle(RobotMap.BACK_WALL_ANGLE, 0, 0));
      driverStick.ButtonPovDown().whileHeld(new TurnToAngle(RobotMap.FRONT_WALL_ANGLE,0, 0));
      driverStick.ButtonPovUpRight().whileHeld(new TurnToAngle(RobotMap.RIGHT_ROCKET_FRONT_ANGLE, 0, 0));
      driverStick.ButtonPovDownRight().whileHeld(new TurnToAngle(RobotMap.RIGHT_ROCKET_BACK_ANGLE, 0, 0));
      driverStick.ButtonPovUpLeft().whileHeld(new TurnToAngle(RobotMap.LEFT_ROCKET_FRONT_ANGLE, 0, 0));
      driverStick.ButtonPovDownLeft().whileHeld(new TurnToAngle(RobotMap.LEFT_ROCKET_BACK_ANGLE, 0, 0));    
  }

  //-------------SMARTDASHBOARD  -------------------------//
  
    // SmartDashboard.putData("Elevator Bottom", new SetElevatorSetpoint(0));
    // SmartDashboard.putData("Elevator Platform", new SetElevatorSetpoint(0.2));
    // SmartDashboard.putData("Elevator Top", new SetElevatorSetpoint(0.3));
    // SmartDashboard.putData("Wrist Horizontal", new SetWristSetpoint(0));
    // SmartDashboard.putData("Raise Wrist", new SetWristSetpoint(-45));
    // SmartDashboard.putData("Open Claw", new OpenClaw());
    // SmartDashboard.putData("Close Claw", new CloseClaw());
    // SmartDashboard.putData("Deliver Soda", new Autonomous());
  
  }

  //-------------JOYSTICK ACCESSOR METHODS -------------------------//
  public XboxOne getDriverStick() {
    return this.driverStick;
  }

  public XboxOne getManipStick() {
    return this.manipStick;
  }

  public ButtonBoard getManipBoard() {
    return this.manipBoard;
  }

}
