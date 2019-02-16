/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.XboxOne;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * The elevator subsystem uses PID to go to a given height. Unfortunately, in
 * it's current state PID values for simulation are different than in the real
 * world do to minor differences.
 */
public class ElevatorWinchPID extends PIDSubsystem {

  private final WPI_TalonSRX winchMotor;
  private final WPI_TalonSRX winchMotorTwo;
  private DoubleSolenoid theLocker;
  public DigitalInput limitSwitch;
  
  private static final double kp = 2.0;
  private static final double ki = 0.0;
  private static final double kd = 0.0;
  private static final double kElevatorTolerance = 500;

  /**
   * Create a new elevator subsystem.
   */
  public ElevatorWinchPID() {
    super("Winch", kp, ki, kd);// The constructor passes a name for the subsystem and the P, I and D constants
                                  // that are used when computing the motor output

    winchMotor = new WPI_TalonSRX(RobotMap.WINCH_MOTOR_CHANNEL);
    winchMotorTwo = new WPI_TalonSRX(RobotMap.SECOND_WINCH_MOTOR_CHANNEL);
    theLocker = new DoubleSolenoid(RobotMap.PISTON_IN_WINCH_CHANNEL, RobotMap.PISTON_OUT_WINCH_CHANNEL);
    limitSwitch = new DigitalInput(RobotMap.DIGITAL_PORT_LIMIT);

    winchMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);  //sets up encoder on winch talon

    getPIDController().setAbsoluteTolerance(kElevatorTolerance);
    getPIDController().setContinuous(false);
  
  }

  public void initDefaultCommand() {
  }

  public double returnPIDInput() {
    return getEncoderVal(); // returns the sensor value that is providing the feedback for the system
  }

  protected void usePIDOutput(double output) {

    zeroWithLimitCheck();
    
      winchMotor.pidWrite(output); // this is where the computed output value fromthe PIDController is applied to the motor
      winchMotorTwo.pidWrite(output);
  }


  protected void toLimit(){

  }


  public void elevateManual(double speed) {
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
      //SmartDashboard.putBoolean("Test something", true);
      zeroEncoder();
      if(getSetPoint() < RobotMap.LEVEL_0 + 100){
        setSetpoint(0);
      }
    }
  }

  // Lock method will lock the winch's gearbox
  public void lockPiston() {
    theLocker.set(DoubleSolenoid.Value.kForward);
  }

  // Unlock method does opposite of above method
  public void unlockPiston() {
    theLocker.set(DoubleSolenoid.Value.kReverse);
  }

  // Stops the solenoid
  public void stopPiston() {
    theLocker.set(DoubleSolenoid.Value.kOff);
  }


   /**
   * Tank style driving for the DriveTrain.
   * @param joy The xboxone joystick to use to drive mecanum style.
   */
  public void elevateJoystick(XboxOne joy) {
    elevateManual(joy.getRightStickRaw_Y());
  }


  /**
   * The log method puts interesting information to the SmartDashboard.
   */
  public void log() {
    SmartDashboard.putBoolean("WinchPID?", true);
    SmartDashboard.putData(winchMotor);
    SmartDashboard.putNumber("Encoder Height", this.getEncoderVal());
    SmartDashboard.putBoolean("Limit Switch", this.isLimitPressed());
 
  }

}