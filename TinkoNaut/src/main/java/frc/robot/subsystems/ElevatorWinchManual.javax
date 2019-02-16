/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.utils.XboxOne;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.ElevateManual;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTable;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class ElevatorWinchManual extends Subsystem {

  private WPI_TalonSRX winchMotor;
  private WPI_TalonSRX winchMotorTwo;
  private DoubleSolenoid theLocker;
  public DigitalInput limitSwitch;

  NetworkTableEntry height;
  NetworkTable table;
  /**
   * Create a new drive train subsystem.
   */
  public ElevatorWinchManual() {
    super();

    //Drivetrain Initializations
    winchMotor = new WPI_TalonSRX(RobotMap.WINCH_MOTOR_CHANNEL);
    winchMotorTwo = new WPI_TalonSRX(RobotMap.SECOND_WINCH_MOTOR_CHANNEL);
    winchMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    theLocker = new DoubleSolenoid(RobotMap.PISTON_IN_WINCH_CHANNEL, RobotMap.PISTON_OUT_WINCH_CHANNEL);
    limitSwitch = new DigitalInput(RobotMap.DIGITAL_PORT_LIMIT);
  }

  /**
   * When no other command is running let the operator control eleavtor (up & down) using the
   * x box joystick. 
   */
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ElevateWithJoystick());
  }

  /**
   * The log method puts interesting information to the SmartDashboard.
   */
   /*public void update() {
     height = table.getEntry("height");
    }*/
  public void log() {
    //update();
 
    SmartDashboard.putBoolean("WinchManualMoving?", true);
    SmartDashboard.putData(winchMotor);
    SmartDashboard.putNumber("Encoder Height", this.getEncoderVal());
    SmartDashboard.putBoolean("Limit Switch", this.isLimitPressed());
    
  }

  public void elevate(double speed) {
    zeroWithLimitCheck();
    if(getEncoderVal() > 0 && getEncoderVal() < RobotMap.LEVEL_3){
      winchMotor.set(speed);
      winchMotorTwo.set(speed);
    }
  }

  public int getEncoderVal(){
    return -winchMotor.getSelectedSensorPosition();
  }

  public void zeroEncoder() {
    winchMotor.setSelectedSensorPosition(0);
  }

  public boolean isLimitPressed(){
    return limitSwitch.get();
  }

  public void zeroWithLimitCheck(){
    if(isLimitPressed()){
      zeroEncoder();
    }
  }

  /**
   * Tank style driving for the DriveTrain.
   *
   * @param joy The xboxone joystick to use to drive mecanum style.
   */
  public void elevateJ(XboxOne joy) {

    elevate(joy.getRightStickRaw_Y());
  }

  
  // Lock method will lock the winch's gearbox
  public void lock() {
    theLocker.set(DoubleSolenoid.Value.kForward);
  }

  // Unlock method does opposite of above method
  public void unlock() {
    theLocker.set(DoubleSolenoid.Value.kReverse);
  }

  // Stops the solenoid
  public void stop() {
    theLocker.set(DoubleSolenoid.Value.kOff);
  }




}