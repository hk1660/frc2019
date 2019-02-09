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
import frc.robot.commands.ElevateWithJoystick;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class ElevatorWinchManual extends Subsystem {

  private WPI_TalonSRX winchMotor;
  NetworkTableEntry height;
  /**
   * Create a new drive train subsystem.
   */
  public ElevatorWinchManual() {
    super();

    //Drivetrain Initializations
    winchMotor = new WPI_TalonSRX(RobotMap.WINCH_MOTOR_CHANNEL);
    winchMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); 
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
   public void update() {
     height = table.getEntry("height");
    }
  public void log() {
    update();
    double y = this.getEncoderVal();
    SmartDashboard.putBoolean("WinchManualMoving?", true);
    SmartDashboard.putData(winchMotor);
    SmartDashboard.putNumber("Encoder Height", this.getEncoderVal());
  }

  public void elevate(double speed) {

    winchMotor.set(speed);
   


  }
  public int getEncoderVal(){
    return winchMotor.getSelectedSensorPosition();
  }

  public void zeroEncoder() {
    winchMotor.setSelectedSensorPosition(0);
  }

  /**
   * Tank style driving for the DriveTrain.
   *
   * @param joy The xboxone joystick to use to drive mecanum style.
   */
  public void elevateJ(XboxOne joy) {

    elevate(joy.getRightStickRaw_Y());
  }




}