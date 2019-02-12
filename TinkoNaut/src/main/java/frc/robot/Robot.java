/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous;
import frc.robot.commands.TonsilEat;
import frc.robot.commands.TonsilSpit;
import frc.robot.subsystems.Tonsils;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ElevatorWinchManual;
import frc.robot.subsystems.ElevatorWinchPID;
import frc.robot.subsystems.CargoGrabber;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.HatchPanelPanel;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Wrist;
import frc.robot.subsystems.NavX;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  Command m_autonomousCommand;

  public static DriveTrain m_drivetrain;
  public static ElevatorWinchManual m_elevatorWinch;
  public static ElevatorWinchPID m_elevatorWinchPID;
  public static CargoGrabber m_cargoGrabber;
  public static HatchPanelPanel m_hatchPanelPanel;
  public static Tonsils m_tonsils;
  public static Wrist m_wrist;
  public static Claw m_claw;
  public static OI m_oi;
  public static Limelight m_limelight;
  public static Pneumatics m_pneumatics;
  public static NavX m_navx;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // Initialize all subsystems
    m_navx = new TurnToAngle(180.0);


    if(m_oi.BB){

    }
    m_elevatorWinch = new ElevatorWinchManual();
    m_elevatorWinchPID = new ElevatorWinchPID();
    
    
    m_cargoGrabber = new CargoGrabber();
    m_hatchPanelPanel = new HatchPanelPanel();
    m_tonsils = new Tonsils();
    m_wrist = new Wrist();
    m_claw = new Claw();
    m_oi = new OI();
    m_limelight = new Limelight();
    m_pneumatics = new Pneumatics();
    m_navx = new NavX ();
    

    // instantiate the command used for the autonomous period
    m_autonomousCommand = new Autonomous();

    // Show what command your subsystem is running on the SmartDashboard
    SmartDashboard.putData(m_drivetrain);
    SmartDashboard.putData(m_cargoGrabber);
    SmartDashboard.putData(m_elevatorWinch);
    SmartDashboard.putData(m_elevatorWinchPID);
    SmartDashboard.putData(m_wrist);
    SmartDashboard.putData(m_claw);
    SmartDashboard.putData(m_limelight);
    




    
    
  }

  @Override
  public void autonomousInit() {


   m_autonomousCommand.start(); // schedule the autonomous command (example)
   DriveStraight autoFor = new DriveStraight(1.0);
   TurnToAngle turnAngle = new TurnToAngle(90.0);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    log();
    


   /* if(m_navx.getCurrentAngle()!= 90.0){

      
      Robot.m_drivetrain.drive(0, 0 ,0 , 90.0 );

    }
*/
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    m_autonomousCommand.cancel();
    
    
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    log();
    SmartDashboard.putData(m_elevatorWinch);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  /**
   * The log method puts interesting information to the SmartDashboard.
   */
  private void log() {
    m_drivetrain.log();
    m_cargoGrabber.log();
    m_elevatorWinch.log();
    m_elevatorWinchPID.log();
    m_wrist.log();
    m_claw.log();
    m_limelight.log();
    
  }
}
