/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.utils.XboxOne;
import frc.robot.commands.MecDriveWithJoystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.kauailabs.navx.frc.AHRS;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors (no Gyro)
 */
public class DriveTrain extends Subsystem {

  private WPI_TalonSRX frontLeft;
	private WPI_TalonSRX backLeft;
	private WPI_TalonSRX frontRight;
	private WPI_TalonSRX backRight;
  private MecanumDrive mecDrive;
<<<<<<< HEAD
  
  
=======
  //backLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);  //sets up encoder on winch talon


>>>>>>> 9b900d085727f35732e6a46d93b449e083962b73
  //private final Encoder m_leftEncoder = new Encoder(1, 2);
  //private final AnalogInput m_rangefinder = new AnalogInput(6);
  
  /**
   * Create a new drive train subsystem.
   */
  public DriveTrain() {
    super();
    
    //Drivetrain Initializations
    frontLeft = new WPI_TalonSRX(RobotMap.DRIVE_FRONT_LEFT_CHANNEL);
		backLeft = new WPI_TalonSRX(RobotMap.DRIVE_BACK_LEFT_CHANNEL);
		frontRight = new WPI_TalonSRX(RobotMap.DRIVE_FRONT_RIGHT_CHANNEL);
		backRight = new WPI_TalonSRX(RobotMap.DRIVE_BACK_RIGHT_CHANNEL);
    
		//we think the constructor switched the 3rd & 4th parameters
    mecDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    
    //m_leftEncoder.setDistancePerPulse(0.042);
    addChild("Drive", mecDrive);    // Let's name the sensors on the LiveWindow
    //addChild("Left Encoder", m_leftEncoder);
    backLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); 
    
  }
  
  
  /**
   * Mecanum-style driving for the DriveTrain with a Joystick.
   *
   * @param strafeParameter The speed to strafe sideways (1.0 is right, -1.0 is left)?
   * @param forwardParameter The speed to travel forward (1.0 is forward, -1.0 is backwards)
   * @param turnParameter The speed to turn the robot with (1.0 is turn right, and -1.0 is turn left)?
   * @param angleParameter The heading of the robot based off of a gyro/NavX
   */
  public void drive(double strafeParameter, double forwardParameter, double turnParameter, double angleParameter) {
    
    mecDrive.driveCartesian(strafeParameter, forwardParameter, turnParameter, angleParameter);
	}
  
  /**
   * Mecanum-style driving for the DriveTrain with a Joystick.
   *
   * @param joy The xboxone joystick to use to drive mecanum style.
   */
  public void drive(XboxOne joy) {

    drive(joy.getRightStickRaw_X(), joy.getRightStickRaw_Y(), joy.getLeftStickRaw_X(), 0);
  }

  
  /**
   * When no other command is running, driver uses the Xbox controller
   */
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MecDriveWithJoystick());
    
  }

  /**
   * The log method puts interesting information to the SmartDashboard.
   */
  public void log() {
SmartDashboard.putNumber("Raw Encoder Distance", getEncoderVal());
SmartDashboard.putNumber("Distance(Inches)", getDistance());

    //SmartDashboard.putNumber("Left Distance", m_leftEncoder.getDistance());
    //SmartDashboard.putNumber("Gyro", m_gyro.getAngle());
  }


  /**
   * Reset the robots sensors to the zero states.
   */
  // public void reset() {
  //   m_leftEncoder.reset();
  // }

  /**
   * Get the average distance of the encoders since the last reset.
   *
   * @return The distance driven (average of left and right encoders).
   */
  // public double getDistance() {
  //   return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2;
  // }

  /**
   * Get the distance to the obstacle.
   *
   * @return The distance to the obstacle detected by the rangefinder.
   */
  // public double getDistanceToObstacle() {
  //   // Really meters in simulation since it's a rangefinder...
  //   return m_rangefinder.getAverageVoltage();
  // }

  public int getEncoderVal(){
    return backLeft.getSelectedSensorPosition();
  }

  public double getDistance(){
    int clicks = getEncoderVal();
    int diameter = 6;
    double inchesPerRev = Math.PI * diameter;
    int clicksPerRev = 1000;
    double inchesPerClick = inchesPerRev/clicksPerRev ;

    return clicks * inchesPerClick;
  }

  public void zeroEncoder() {
    backLeft.setSelectedSensorPosition(0);
  }


}