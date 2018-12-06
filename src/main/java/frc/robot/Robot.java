/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends IterativeRobot {

    //MOTOR PORTS
    int RIGHT1 = 57;
    int RIGHT2 = 56;
    int LEFT1 = 62;
    int LEFT2 = 58;
    int SHOOT1 = 60;
    int SHOOT2 = 61;
    int SHOOT3 = 59;
    int SHOOT4 = 55;
    int JAW = 0;

    int LEFT_X_AXIS = 0;
    int LEFT_Y_AXIS = 1;
    int LEFT_TRIGGER = 2;
    int RIGHT_TRIGGER = 3;
    int RIGHT_X_AXIS = 4;
    int RIGHT_Y_AXIS = 5;

    //BUTTONS
    int LOAD_BUTTON = 4; //y
    int UNLOAD_BUTTON = 3;  //x
    int PRESSURE_OVERRIDE_BUTTON = 8; //START
    int AUTO_COMPRESSOR_BUTTON = 7; //BACK
    int SHOOTING_BUTTON = 1;  //a
    int STOP_SHOOTING_BUTTON = 2;//b
    int LAUNCH_COMBO_BUTTON = 33; //rt-axis
    int JAW_UP_BUTTON = 5;  //lb
    int JAW_DOWN_BUTTON = 6;    //rb

    int DRIVE_JOY = 1;

    //PCM PORTS
    int COMPRESSOR_PORT = 0;
    int LOAD_CHANNEL = 1;
    int RELOAD_CHANNEL = 2;

    //MOTORS
    private WPI_TalonSRX rightDriveF;
    private WPI_TalonSRX rightDriveR;
    private WPI_TalonSRX leftDriveF;
    private WPI_TalonSRX leftDriveR;
    private WPI_TalonSRX shootingWheel1FWD, shootingWheel1REV, shootingWheel2FWD, shootingWheel2REV;
    private Talon shootingJawMotor;

    private Joystick driveStick;

    private DigitalInput highLimitSwitch;
    private DigitalInput lowLimitSwitch;

    Timer launchingTimer = new Timer();
    
    private SendableChooser autonChooser = new SendableChooser();
    private boolean isShootingWheelSpinning = false;

    Compressor comp;
	DoubleSolenoid frisbeeLoader;
    private boolean isLaunching = false;
    private boolean shootingMotorFlag = false;


    public void robotInit() {
        System.out.println("__________________init__________________");


        try {
            driveStick = new Joystick(DRIVE_JOY);

            //Drivetrain Initializations
            rightDriveF = new WPI_TalonSRX(RIGHT1);
            rightDriveR = new WPI_TalonSRX(RIGHT2);
            leftDriveF = new WPI_TalonSRX(LEFT1);
            leftDriveR = new WPI_TalonSRX(LEFT2);

            //newHkDrive = new DifferentialDrive(rightDriveF, rightDriveR, rightDriveF, rightDriveR);

            shootingJawMotor = new Talon(JAW);
            shootingWheel1FWD = new WPI_TalonSRX(SHOOT1);
            shootingWheel1REV = new WPI_TalonSRX(SHOOT2);
            shootingWheel2FWD = new WPI_TalonSRX(SHOOT3);
            shootingWheel2REV = new WPI_TalonSRX(SHOOT4);

            comp = new Compressor(COMPRESSOR_PORT);
            frisbeeLoader = new DoubleSolenoid(LOAD_CHANNEL, RELOAD_CHANNEL);            //construct double solenoid
   
            highLimitSwitch = new DigitalInput(1);
            lowLimitSwitch = new DigitalInput(2);
            autonChooser.addObject("Move, autoaim, and Shoot 3 from back right", Integer.valueOf(1));
            autonChooser.addObject("Move, autoaim, and Shoot 3 from back left", Integer.valueOf(2));
            autonChooser.addDefault("Already aimed, shoot 3 discs", Integer.valueOf(3));
            SmartDashboard.putData("Autonomous select", autonChooser);
            System.out.println("Init Complete");

        } catch (Exception e) {
            System.out.println(e);
         }
    }

    
    public void autonomous()
    {
        
        /*
        Integer x = (Integer) autonChooser.getSelected();
        if(x.intValue() == 1)
        {
            autonomous_back_right_corner(true);
        }
        else if(x.intValue() == 2)
        {
            autonomous_back_right_corner(false);
        }
        else if(x.intValue() == 3)
        {
           autonomous_no_moving_just_shoot();   
        }
        else
        {
              autonomous_no_moving_just_shoot();
        }
        */
    }


    public void teleopPeriodic() {

        System.out.println("__________________teleop__________________");

        while (isOperatorControl() && isEnabled()) {

            checkCompressor();

            checkDrive();
            checkJawMotor();            
            checkLoadButtons();                    
            checkShootingWheels();
            checkLaunchComboButton();
            
            Timer.delay(0.01);
        }
    }

    /*---------------------  CUSTOM METHODS -----------------------*/

    // basic compressor functionality methods -Aldenis
    public void compressorCLOn() {
        this.comp.setClosedLoopControl(true);
        this.comp.start();
        SmartDashboard.putString("compressorStatus", "CL is on");
    }

    public void compressorCLOff() {
        this.comp.setClosedLoopControl(false);
        this.comp.start();
        SmartDashboard.putString("compressorStatus", "CL is off");
    }

    public void checkCompressor() {

        if (driveStick.getRawButton(PRESSURE_OVERRIDE_BUTTON)) {
            compressorCLOn();
        }
        if(driveStick.getRawButton(AUTO_COMPRESSOR_BUTTON)){
            compressorCLOff();
        }

    }


    //Methods that help drive the robot
    public void checkDrive(){
    	
    	double dSpeed = driveStick.getRawAxis(RIGHT_Y_AXIS);
    	double dTurn  = driveStick.getRawAxis(LEFT_X_AXIS);
        drive(dSpeed * 1.0, dTurn * 1.0);
        System.out.println("Drive: dSpeed = " + dSpeed + ", dTurn= "+ dTurn);
        
    }    
    public void drive(double dSpeed, double dTurn){
        
        rightDriveF.set(dSpeed + dTurn);
    	rightDriveR.set(dSpeed + dTurn);
    	leftDriveF.set(-(dSpeed - dTurn));
    	leftDriveR.set(-(dSpeed - dTurn));
    	
    }


    //Method that checks if the Jaw motor should move (No limit switch code yet)
    public void checkJawMotor() {
                      
    	double JawSpeed = 0.5;
    	boolean JawUpVal = driveStick.getRawButton(JAW_UP_BUTTON);
    	boolean JawDownVal = driveStick.getRawButton(JAW_DOWN_BUTTON);
        
    	if(JawUpVal){
    		shootingJawMotor.set(JawSpeed * 1.0);
    	} else if (JawDownVal){
    		shootingJawMotor.set(JawSpeed * -1.0);
    	}else{
    		shootingJawMotor.set(0.0);
    	}
    }


    public void turnOnShootingWheels() 
    {
            isShootingWheelSpinning = true;
            shootingWheel1FWD.set(-1.0);
            shootingWheel1REV.set(-1.0);
            shootingWheel2FWD.set(-1.0);
            shootingWheel2REV.set(-1.0);
    }
    public void turnOffShootingWheels() 
    {
            isShootingWheelSpinning = false;
            shootingWheel1FWD.set(0);
            shootingWheel1REV.set(0);
            shootingWheel2FWD.set(0);
            shootingWheel2REV.set(0);
    }
    
    //Method to check whether to spin the shooter wheels
    public void checkShootingWheels() {

        if (driveStick.getRawButton(SHOOTING_BUTTON)) {
            shootingMotorFlag = true;
        } else if (driveStick.getRawButton(STOP_SHOOTING_BUTTON)) {
            shootingMotorFlag = false;
        }

        if (shootingMotorFlag) {
            turnOnShootingWheels();
        } else if (!shootingMotorFlag) {
            turnOffShootingWheels();
        }

    }

    public void checkLaunchComboButton() {
        if (driveStick.getRawButton(LAUNCH_COMBO_BUTTON) && !isLaunching) {
            isLaunching = true;
            shootingMotorFlag = true;
            launchingTimer.reset();
            launchingTimer.start();
        }

        if (isLaunching) {
            if (launchingTimer.get() < 0.4) {
                loadFrisbee();
            } 
            else if ( launchingTimer.get() > 0.4 && launchingTimer.get() < 0.8 ){
                reloadFrisbee();
            }
            else {   
                isLaunching = false;
                
            }
        }
        if(launchingTimer.get()> 4)
        {
            launchingTimer.stop();
            shootingMotorFlag = false;
        }
    }


    //Methods to load and reload the frisbees to launch
    public void checkLoadButtons(){
        if (driveStick.getRawButton(LOAD_BUTTON)) {
            loadFrisbee();
        }
        if (driveStick.getRawButton(UNLOAD_BUTTON)) {
           reloadFrisbee();
        }

    }
    public void loadFrisbee() {
        this.frisbeeLoader.set(DoubleSolenoid.Value.kForward);
    }
    public void reloadFrisbee() {
        this.frisbeeLoader.set(DoubleSolenoid.Value.kReverse);
       }

/*
    public void autonomous_back_right_corner(boolean isRight_) {
        Timer autonomoustimer = new Timer();
        autonomoustimer.start();
        while (isAutonomous() && isEnabled()) {
            if (autonomoustimer.get() > 0 && autonomoustimer.get() < 1.1) {
                jagDrive(0.45, 0);
            }
            if (autonomoustimer.get() > 1.1 && autonomoustimer.get() < 1.3) {
                jagDrive(0, 0);
            }
            if (autonomoustimer.get() > 1.6 && autonomoustimer.get() < 2.8) {
                if(isRight_) {
                    jagDrive(0.45, -1);
                }
                else {
                    jagDrive(0.45, 1);
                }
            }
            if (autonomoustimer.get() > 2.8 && autonomoustimer.get() < 3) {
                jagDrive(0, 0);
            }
            if (autonomoustimer.get() > 3 && autonomoustimer.get() < 9) {
                tryStartCamera();
                
                boolean x_is_aligned = alignX();
                if (x_is_aligned ) 
                {
                    alignY();
                } 
            }
            if (autonomoustimer.get() > 6 && autonomoustimer.get() < 15) {
                shootingWheel1FWD.set(-1.0);
                shootingWheel1REV.set(-1.0);
                shootingWheel2FWD.set(-1.0);
                shootingWheel2REV.set(-1.0);
            }
            if (autonomoustimer.get() > 8 && autonomoustimer.get() < 9) {
                
                loadingRelaySwitchBack();
            }
            if (autonomoustimer.get() > 9 && autonomoustimer.get() < 10) {
                stopCamera();
                jagDrive(0, 0);
                loadingRelaySwitchFwd();
            }
            if (autonomoustimer.get() > 10 && autonomoustimer.get() < 11) {
                loadingRelaySwitchBack();
            }
            if (autonomoustimer.get() > 11 && autonomoustimer.get() < 12) {
                loadingRelaySwitchFwd();
            }
            if (autonomoustimer.get() > 12 && autonomoustimer.get() < 13) {
                loadingRelaySwitchBack();
            }
            if (autonomoustimer.get() > 13 && autonomoustimer.get() < 14) {
                loadingRelaySwitchFwd();
            }
        }
    }

    
    public void autonomous_no_moving_just_shoot()
    {
        Timer autonomoustimer = new Timer();
        autonomoustimer.start();
        while (isAutonomous() && isEnabled()) {
        if (autonomoustimer.get() > 0 && autonomoustimer.get() < 9) {
             
                tryStartCamera();
                
                boolean Y_is_aligned = alignY();
  
        }
            if (autonomoustimer.get() > 6 && autonomoustimer.get() < 15) {
                shootingWheel1FWD.set(-1.0);
                shootingWheel1REV.set(-1.0);
                shootingWheel2FWD.set(-1.0);
                shootingWheel2REV.set(-1.0);
            }
            if (autonomoustimer.get() > 8 && autonomoustimer.get() < 9) {
                loadingRelaySwitchBack();
            }
            if (autonomoustimer.get() > 9 && autonomoustimer.get() < 10) {
                loadingRelaySwitchFwd();
                 stopCamera();
                jagDrive(0, 0);
            }
            if (autonomoustimer.get() > 10 && autonomoustimer.get() < 11) {
                loadingRelaySwitchBack();
            }
            if (autonomoustimer.get() > 11 && autonomoustimer.get() < 12) {
                loadingRelaySwitchFwd();
            }
            if (autonomoustimer.get() > 12 && autonomoustimer.get() < 13) {
                loadingRelaySwitchBack();
            }
            if (autonomoustimer.get() > 13 && autonomoustimer.get() < 14) {
                loadingRelaySwitchFwd();
            }
        }
    }

*/

}
