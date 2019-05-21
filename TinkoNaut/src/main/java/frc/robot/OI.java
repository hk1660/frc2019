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
import frc.robot.commands.DriveEncoderZero;
import frc.robot.commands.DriveTurnToAngle;
import frc.robot.commands.DriveAngleZero;
import frc.robot.commands.DriveCombo;
import frc.robot.commands.LLStrafe;
import frc.robot.commands.LLToggleOff;
import frc.robot.commands.LLToggleOn;
import frc.robot.commands.PistIn;
import frc.robot.commands.PistOut;
import frc.robot.commands.CargoEat;
import frc.robot.commands.CargoEatBoth;
import frc.robot.commands.CargoSpit;
import frc.robot.commands.CargoSpitBoth;
import frc.robot.commands.TonsilEat;
import frc.robot.commands.TonsilSpit;
import frc.robot.commands.ElevateManual;
import frc.robot.commands.SetElevatorHeight;
import frc.robot.commands.WinchLock;
import frc.robot.commands.WinchUnlock;
import frc.robot.commands.WinchEncoderZero;


import frc.robot.utils.XboxOne;
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
      manipBoard.ButtonOne().whileHeld(new CargoEatBoth());
      manipBoard.ButtonTwo().whileHeld(new CargoSpitBoth());  
      //manipBoard.ButtonOne().whileHeld(new CargoEat());
      //manipBoard.ButtonTwo().whileHeld(new CargoSpit());
      manipBoard.ButtonFour().whileHeld(new PistIn());
      manipBoard.ButtonThree().whileHeld(new PistOut());
      //manipBoard.ButtonFive().whileHeld(new TonsilSpit());
      manipBoard.ButtonFive().whileHeld(new WinchEncoderZero());
      //manipBoard.ButtonSix().whileHeld(new TonsilEat());
      manipBoard.ButtonSix().whenPressed(new SetElevatorHeight(RobotMap.EAT_CARGO_HEIGHT));
      manipBoard.ButtonEleven().whileHeld(new CargoEat());

      //BOARD Elevator Buttons
      // manipBoard.ButtonSeven().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1));
      // manipBoard.ButtonEight().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1_5));
      // manipBoard.ButtonNine().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2));
      // manipBoard.ButtonTen().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2_5));
      // manipBoard.ButtonEleven().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3));
      // manipBoard.ButtonTwelve().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3_5));
      manipBoard.ButtonPovLeft().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1));
      //manipBoard.ButtonPovRight().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1_5));
      manipBoard.ButtonPovDown().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2));
      manipBoard.ButtonPovUp().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2_5));
      //manipBoard.ButtonFive().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3));
      manipBoard.ButtonTwelve().whileHeld(new SetElevatorHeight(RobotMap.LEVEL_3_5));

      // //BOARD extra buttons
      //manipBoard.ButtonTwelve().whenPressed(new WinchLock()); 
      //manipBoard.ButtonTen().whenPressed(new WinchUnlock());
      manipBoard.ButtonTen().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1_5)); 
      //manipBoard.ButtonEight().whileHeld(new WinchEncoderZero());
      manipBoard.ButtonEight().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3));


      // //BOARD manual joystick
      manipBoard.ButtonSeven().whileHeld(new ElevateManual(1.0)); //see also SetElevatorHeight command
      manipBoard.ButtonNine().whileHeld(new ElevateManual(-1.0));

    } else {

      //STICK Action buttons
      manipStick.ButtonLB().whileHeld(new PistIn());
      manipStick.ButtonLeftTrigger().whileHeld(new PistOut());
      // manipStick.ButtonLB().whileHeld(new CargoEat());
      // manipStick.ButtonLeftTrigger().whileHeld(new CargoSpit());
      manipStick.ButtonA().whileHeld(new CargoEatBoth());
      manipStick.ButtonB().whileHeld(new CargoSpitBoth());
     // manipStick.ButtonX().whileHeld(new TonsilEat());
      //manipStick.ButtonY().whileHeld(new TonsilSpit());
      manipStick.ButtonY().whileHeld(new ElevateManual(1.0));
      manipStick.ButtonX().whileHeld(new ElevateManual(-1.0));

      //STICK Elevator buttons
      manipStick.ButtonPovDown().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1));
      manipStick.ButtonPovDownLeft().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_1_5));
      manipStick.ButtonPovLeft().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2));
      manipStick.ButtonPovUpLeft().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_2_5));
      manipStick.ButtonPovUp().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_3));
      manipStick.ButtonPovUpRight().whileHeld(new SetElevatorHeight(RobotMap.LEVEL_3_5));
  

      //STICK extra buttons
      manipStick.ButtonStart().whenPressed(new WinchUnlock());
      manipStick.ButtonBack().whenPressed(new WinchLock());
      manipStick.ButtonRB().whenPressed(new WinchEncoderZero());
     // manipStick.ButtonStart().whenPressed(new SetElevatorHeight(RobotMap.LEVEL_0));
      
     //STICK Manual Winch
   //  manipStick.ButtonRightTrigger().whenPress
    }

    //-------------DRIVER BUTTONS -------------------------//
    
    driverStick.ButtonRightTrigger().whileHeld(new LLToggleOn());
    driverStick.ButtonLeftTrigger().whileHeld(new LLToggleOff());
    driverStick.ButtonStart().whileHeld(new DriveEncoderZero());
    driverStick.ButtonY().whileHeld(new DriveAngleZero());
    driverStick.ButtonA().whileHeld(new LLStrafe());

    driverStick.ButtonPovRight().whenPressed(new DriveCombo(RobotMap.RIGHT_WALL_ANGLE, RobotMap.LL_FLAG));
    driverStick.ButtonPovLeft().whenPressed(new DriveCombo(RobotMap.LEFT_WALL_ANGLE, RobotMap.LL_FLAG));
    driverStick.ButtonPovUp().whenPressed(new DriveCombo(RobotMap.FRONT_WALL_ANGLE, RobotMap.LL_FLAG));
    driverStick.ButtonPovDown().whenPressed(new DriveCombo(RobotMap.BACK_WALL_ANGLE,RobotMap.LL_FLAG));
    driverStick.ButtonPovUpRight().whenPressed(new DriveCombo(RobotMap.RIGHT_ROCKET_FRONT_ANGLE, RobotMap.LL_FLAG));
    driverStick.ButtonPovDownRight().whenPressed(new DriveCombo(RobotMap.RIGHT_ROCKET_BACK_ANGLE, RobotMap.LL_FLAG));
    driverStick.ButtonPovUpLeft().whenPressed(new DriveCombo(RobotMap.LEFT_ROCKET_FRONT_ANGLE, RobotMap.LL_FLAG));
    driverStick.ButtonPovDownLeft().whenPressed(new DriveCombo(RobotMap.LEFT_ROCKET_BACK_ANGLE, RobotMap.LL_FLAG));

    // driverStick.ButtonPovRight().whileHeld(new DriveTurnToAngle(RobotMap.RIGHT_WALL_ANGLE));
    // driverStick.ButtonPovLeft().whileHeld(new DriveTurnToAngle(RobotMap.LEFT_WALL_ANGLE));
    // driverStick.ButtonPovUp().whileHeld(new DriveTurnToAngle(RobotMap.BACK_WALL_ANGLE));
    // driverStick.ButtonPovDown().whileHeld(new DriveTurnToAngle(RobotMap.FRONT_WALL_ANGLE));
    // driverStick.ButtonPovUpRight().whileHeld(new DriveTurnToAngle(RobotMap.RIGHT_ROCKET_FRONT_ANGLE));
    // driverStick.ButtonPovDownRight().whileHeld(new DriveTurnToAngle(RobotMap.RIGHT_ROCKET_BACK_ANGLE));
    // driverStick.ButtonPovUpLeft().whileHeld(new DriveTurnToAngle(RobotMap.LEFT_ROCKET_FRONT_ANGLE));
    // driverStick.ButtonPovDownLeft().whileHeld(new DriveTurnToAngle(RobotMap.LEFT_ROCKET_BACK_ANGLE));

  //-------------SMARTDASHBOARD COMMANDS  -------------------------//
  
    SmartDashboard.putData("Elevator Level 1", new SetElevatorHeight(RobotMap.LEVEL_1));
    SmartDashboard.putData("Elevator Level 1.5", new SetElevatorHeight(RobotMap.LEVEL_1_5));
    SmartDashboard.putData("Elevator Level 3.5", new SetElevatorHeight(RobotMap.LEVEL_3_5));
    //SmartDashboard.putData("Wrist Horizontal", new SetWristSetpoint(0));
    //SmartDashboard.putData("Raise Wrist", new SetWristSetpoint(-45));
    SmartDashboard.putData("Eat Cargo", new CargoEat());
    SmartDashboard.putData("Spit Cargo", new CargoSpit());
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
