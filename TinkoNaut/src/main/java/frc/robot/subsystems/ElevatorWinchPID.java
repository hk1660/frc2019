/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * The elevator subsystem uses PID to go to a given height. Unfortunately, in
 * it's current state PID values for simulation are different than in the real
 * world do to minor differences.
 */
public class ElevatorWinchPID extends PIDSubsystem {
  private final WPI_TalonSRX winchMotor;
  private final int encoder;
  private DoubleSolenoid theLocker;

  // private static final double kP_real = 4;
  // private static final double kI_real = 0.07;
  // private static final double kP_simulation = 18;
  // private static final double kI_simulation = 0.2;

  /**
   * Create a new elevator subsystem.
   */
  public ElevatorWinchPID() {
    super("Winch", 2.0, 0.0, 0.0);// The constructor passes a name for the subsystem and the P, I and D constants
                                  // that are used when computing the motor output
    setAbsoluteTolerance(0.05);
    getPIDController().setContinuous(false);

    winchMotor = new WPI_TalonSRX(RobotMap.WINCH_MOTOR_CHANNEL);
    encoder = winchMotor.getSelectedSensorPosition(0);
    theLocker = new DoubleSolenoid(RobotMap.PISTON_IN_WINCH_CHANNEL, RobotMap.PISTON_OUT_WINCH_CHANNEL);

  }

  public void initDefaultCommand() {
  }

  public double returnPIDInput() {
    return encoder; // returns the sensor value that is providing the feedback for the system
  }

  protected void usePIDOutput(double output) {
    winchMotor.pidWrite(output); // this is where the computed output value fromthe PIDController is applied to the motor
  }

  public void moveWinch(double speed) {
    winchMotor.set(speed);
  }

  /**
   * The log method puts interesting information to the SmartDashboard.
   */
  public void log() {
    SmartDashboard.putNumber("Encoder stuff", encoder);
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