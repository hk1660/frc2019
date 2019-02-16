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

      /* Button Board ------------------ XBOX
      Button 1 - Eat Cargo        LB
      Button 2 - Spit Cargo       LTrig
      Button 3 - Hatch Pull       A
      Button 4 - Hatch Push       B
      Button 5 - Tonsil Spit      Y
      Button 6 - Tonsil Eat       X
      Button 7 - Level 1          POV DOWN
      Button 8 - Level 1.5        POV DOWN_LEFT
      Button 9 - Level 2          POV_LEFT
      Button 10 - Level 2.5       POV_UP_LEFT
      Button 11 - Level 3         POV_UP
      Button 12 - 3.5             POV_UP_RIGHT
      Button X_AXIS_NEG L - Unlock    START
      Button X_AXIS_POS - Lock         SELECT
      Button Y_AXIS_NEG - Encoder Zero  RB
      Button Y_AXIS_POS - ????          RT
      */

      //BOARD action buttons
      manipBoard.ButtonOne().whileHeld(new EatCargo());
      manipBoard.ButtonTwo().whileHeld(new SpitCargo());
      manipBoard.ButtonThree().whileHeld(new PistIn());
      manipBoard.ButtonFour().whileHeld(new PistOut());
      manipBoard.ButtonFive().whileHeld(new TonsilSpit());
      manipBoard.ButtonSix().whileHeld(new TonsilEat());

      //BOARD Elevator Buttons
      manipBoard.ButtonSeven().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1));
      manipBoard.ButtonEight().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1_5));
      manipBoard.ButtonNine().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2));
      manipBoard.ButtonTen().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2_5));
      manipBoard.ButtonEleven().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3));
      manipBoard.ButtonTwelve().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3_5));

      // //BOARD extra buttons
      // manipBoard.ButtonNine().whenPressed(new LockWinch()); 
      // manipBoard.ButtonTen().whenPressed(new UnlockWinch()); 
      // manipBoard.ButtonEight().whenPressed(new EncoderZero());

      // //BOARD manual joystick
      // manipBoard.ButtonRight().whileHeld(new ElevateManual());
      // manipBoard.ButtonLeft().whileHeld(new ElevateManual());

    } else {

      //STICK Action buttons
      manipStick.ButtonA().whileHeld(new PistIn());
      manipStick.ButtonB().whileHeld(new PistOut());
      manipStick.ButtonLB().whileHeld(new EatCargo());
      manipStick.ButtonLeftTrigger().whileHeld(new SpitCargo());
      //manipStick.ButtonX().whileHeld(new TonsilEat());
      manipStick.ButtonY().whileHeld(new TonsilSpit());

      //STICK Elevator buttons
      manipStick.ButtonPovDown().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1));
      manipStick.ButtonPovDownLeft().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1_5));
      manipStick.ButtonPovLeft().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2));
      manipStick.ButtonPovUpLeft().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2_5));
      manipStick.ButtonPovUp().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3));
      manipStick.ButtonPovUpRight().whileHeld(new SetElevatorHeight(RobotMap.LEVEL_3_5));
      manipStick.ButtonX().whileHeld(new SetElevatorHeight(RobotMap.LEVEL_3_5));

      //STICK extra buttons
      manipStick.ButtonStart().whenPressed(new UnlockWinch());
      manipStick.ButtonBack().whenPressed(new LockWinch());
      manipStick.ButtonRB().whenPressed(new EncoderZero());

      //STICK manual joystick
      //???

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

  //-------------SMARTDASHBOARD COMMANDS  -------------------------//
  
    SmartDashboard.putData("Elevator Level 1", new SetElevatorHeight(RobotMap.LEVEL_1));
    SmartDashboard.putData("Elevator Level 1.5", new SetElevatorHeight(RobotMap.LEVEL_1_5));
    SmartDashboard.putData("Elevator Level 3.5", new SetElevatorHeight(RobotMap.LEVEL_3_5));
    //SmartDashboard.putData("Wrist Horizontal", new SetWristSetpoint(0));
    //SmartDashboard.putData("Raise Wrist", new SetWristSetpoint(-45));
    SmartDashboard.putData("Eat Cargo", new EatCargo());
    SmartDashboard.putData("Spit Cargo", new SpitCargo());
    //SmartDashboard.putData("Deliver Soda", new Autonomous());
  
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
