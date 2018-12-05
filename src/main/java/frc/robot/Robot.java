/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot;


//package org.usfirst.frc.team1660.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Jaguar;

//import edu.wpi.first.wpilibj.camera.AxisCamera;
//import edu.wpi.first.wpilibj.image.*;
//import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
//import edu.wpi.first.wpilibj.image.NIVision.Rect;
import edu.wpi.first.wpilibj.Relay;
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


    //MOTORS
    private DifferentialDrive newHkDrive;

    private WPI_TalonSRX rightDriveF;
    private WPI_TalonSRX rightDriveR;
    private WPI_TalonSRX leftDriveF;
    private WPI_TalonSRX leftDriveR;
    private WPI_TalonSRX shootingWheel1FWD, shootingWheel1REV, shootingWheel2FWD, shootingWheel2REV;
    private Jaguar shootingPitchMotor;

    private Relay loadingRelay, climbRelay, compressorRelay;

    private Joystick driveStick;
    private Joystick frisbeeStick;

    private DigitalInput highLimitSwitch;
    private DigitalInput lowLimitSwitch;
    private DigitalInput pressureLimitSwitch;
    //private MyCamera camera = new MyCamera();
    
    //private static double ROBOT_TURN_SPEED = 0.80;
    //private static String CAMERA_STATUS = "CAM_STATUS";
    //private boolean slow_drive_mode = true;
    private boolean shooting_motor_switch = true;
    private boolean is_launching = false;
    Timer launchingTimer = new Timer();
    //private Timer alignmentTimer = new Timer();
    private SendableChooser autonChooser = new SendableChooser();
    private boolean isShootingWheelSpinning = false;

   ///making a change to test with Marlahna
   

    public void robotInit() {
        System.out.println("__________________init__________________");


        try {
            driveStick = new Joystick(1);
            frisbeeStick = new Joystick(2);

            //Drivetrain Initializations
            rightDriveF = new WPI_TalonSRX(RIGHT1);
            rightDriveR = new WPI_TalonSRX(RIGHT2);
            leftDriveF = new WPI_TalonSRX(LEFT1);
            leftDriveR = new WPI_TalonSRX(LEFT2);

            //newHkDrive = new DifferentialDrive(rightDriveF, rightDriveR, rightDriveF, rightDriveR);

            shootingPitchMotor = new Jaguar(JAW);
            shootingWheel1FWD = new WPI_TalonSRX(SHOOT1);
            shootingWheel1REV = new WPI_TalonSRX(SHOOT2);
            shootingWheel2FWD = new WPI_TalonSRX(SHOOT3);
            shootingWheel2REV = new WPI_TalonSRX(SHOOT4);
   
            
            compressorRelay = new Relay(1, Relay.Direction.kForward);
            loadingRelay = new Relay(3, Relay.Direction.kBoth);
            climbRelay = new Relay(2, Relay.Direction.kBoth);
   
            compressorRelay.set(Relay.Value.kOff);
            loadingRelay.set(Relay.Value.kOff);
            climbRelay.set(Relay.Value.kOff); 
            //compressorRelaySwitchOn();
            
            highLimitSwitch = new DigitalInput(1);
            lowLimitSwitch = new DigitalInput(2);
            pressureLimitSwitch = new DigitalInput(3);  
            
            autonChooser.addObject("Move, autoaim, and Shoot 3 from back right", Integer.valueOf(1));
            autonChooser.addObject("Move, autoaim, and Shoot 3 from back left", Integer.valueOf(2));
            autonChooser.addDefault("Already aimed, shoot 3 discs", Integer.valueOf(3));
            
            SmartDashboard.putData("Autonomous select", autonChooser);
            
            System.out.println("Init Complete");

        } catch (Exception e) {
            System.out.println(e);
         }

    }

    
    public void teleopPeriodic() {
        shooting_motor_switch = false;
        System.out.println("__________________teleop__________________");
         //climbRelaySwitchBack();
        while (isOperatorControl() && isEnabled()) {

    //         System.out.println("\n\t boundingRectWidth: " + boundingRectWidth + " ... angle: "+ current.current_y); //delete after test
            
            newCheckDrive();
        	
            if (frisbeeStick.getRawButton(5)) {
                //loadingRelaySwitchFwd();
            }
            if (frisbeeStick.getRawButton(6)) {
               // loadingRelaySwitchBack();
            }
            
            /*
            if (manipJoystick.getRawButton(11)) {
                climbRelaySwitchFwd();
            }
            if (manipJoystick.getRawButton(12)) {
                climbRelaySwitchBack();
            }
            */            
            
            if (pressureLimitSwitch.get() == false){
                //compressorRelaySwitchOn();
            }
            
            if (pressureLimitSwitch.get() == true){
                //compressorRelaySwitchOff();
            }

            
            if (frisbeeStick.getRawButton(3)) {
                //compressorRelaySwitchOff();
                //loadingRelaySwitchOff();
                //climbRelaySwitchOff();
            }
            if (frisbeeStick.getRawButton(4)) {
                //debug_CheckRelays();
            }
            //checkShootingPitchMotorButton();
            newCheckPitchMotorButton();  //11 & 12
            
            checkShootingWheelButton();

            //checkResetAlignment();

            checkLaunchingButton();

            //checkCameraButtons();
            
            //test_adjustRobotTurnSpeed();
            
            Timer.delay(0.01);
        }
    }


    public void newCheckDrive() {

        double dSpeed = driveStick.getRawAxis(LEFT_Y_AXIS);
        double dTurn = driveStick.getRawAxis(LEFT_X_AXIS);
        newDrive(dSpeed * 1.0, dTurn * -1.0);
        System.out.println("Drive: dSpeed = " + dSpeed + ", dTurn= " + dTurn);

    }

    // Replaces method from the RobotDrive class to use WPI_TalonSRX
    public void newDrive(double dSpeed, double dTurn) {

        rightDriveF.set(dSpeed + dTurn);
        rightDriveR.set(dSpeed + dTurn);
        leftDriveF.set(dSpeed - dTurn);
        leftDriveR.set(dSpeed - dTurn);

    }
    
/*
    public void checkResetAlignment() {
        if (frisbeeStick.getRawButton(1)) {
            camera.resetDesiredXY();
        }
    }
    public boolean alignX() {
        if (camera.isCameraTrackingRectangles()) {
            if (camera.isWithinThresholdX(camera.getDesiredX(), camera.getCurrentX())) {
                System.out.println("\tX IS ALIGNED");
                return true;
            } else {
                if(System.currentTimeMillis() % 600 < 200 )  //this is to simulate a pump command
                {
                    newDrive(0, 0); 
                }
                else
                {
                   if (camera.getCurrentX() < camera.getDesiredX()) {
                        newDrive(ROBOT_TURN_SPEED, -1); //turn left
                    } else {
                        newDrive(ROBOT_TURN_SPEED, 1);
                    } 
                }
            }
        }
        else
        {
            newDrive(0, 0);
        }
        return false;
    }
    public boolean alignY() {
        if (camera.isCameraTrackingRectangles()) {
            try {
                if (camera.isWithinThresholdY(camera.getDesiredY(), camera.getCurrentY())) {
                    System.out.println("\tY IS ALIGNED");
                    shootingPitchMotor.set(0);
                    return true;
                } else {
                    if (camera.getCurrentY() < camera.getDesiredY()) {
                        if(highLimitSwitch.get() == false)
                        {
                            shootingPitchMotor.set(1); // pitch up    
                        }
                        else
                        {
                            shootingPitchMotor.set(0); 
                        }
                        
                    } else {
                        if(lowLimitSwitch.get() == false)
                        {
                            shootingPitchMotor.set(-1); //pitch down
                        }
                        else
                        {
                            shootingPitchMotor.set(0);
                        }
                        
                    }
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
    
    public void stopCamera()
    {
        if(camera!=null && camera.isCameraRunning())
        {
            shootingPitchMotor.set(0); //make sure the pitch motor stops when camera stops 
            camera.stopProcessingImage();
            alignmentTimer.stop();
            alignmentTimer.reset();           
            SmartDashboard.putString(CAMERA_STATUS, "OFF");
            
        }
    }
    
    public void tryStartCamera()
    {
        try
        {
            if(camera==null)
            {
                System.out.println("\tINSTANTIATING CAMERA");
                camera = new MyCamera();
                System.out.println("\tCAMERA INSTATIATED!");
            }
            
            if(!camera.isCameraRunning())
            {
                camera.startProcessingImage();
                alignmentTimer.start();
                SmartDashboard.putString(CAMERA_STATUS, "STARTING...");
                Thread t = new Thread(new Runnable() {
                    public void run()
                    {
                        System.out.println("\tSTARTING CAMERA");
                        camera.processImageLoop();
                    }
                });
                t.start();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            //update dashboard that camera is not working
        }
        
    }
    
    public void resetDesiredXYTarget ()
    {
        tryStartCamera();
        if(camera.isCameraTrackingRectangles())
        {
            camera.resetDesiredXY();
            SmartDashboard.putString(CAMERA_STATUS, "RESET finished");
            System.out.println("\t RESET is FINISHED!!!");
        }
        else
        {
            System.out.println("\tCANNOT RESET!!!");
        }
    }
    public void winButton() {
            tryStartCamera();
            if (camera.isCameraTrackingRectangles()) 
            {
                boolean x_is_aligned = alignX();
                
                if (x_is_aligned ) 
                {
                    boolean y_is_aligned = alignY();
                    if(y_is_aligned)
                    {
                         System.out.println("\tALIGNED GOGOGO");
                         SmartDashboard.putString(CAMERA_STATUS, "GOGOGOGO");
                        //update dashboard   
                    }
                    else
                    {
                        SmartDashboard.putString(CAMERA_STATUS, "aligning Y");
                         System.out.println("\taligning Y...");
                    }
                    
                } 
                else 
                {
                    SmartDashboard.putString(CAMERA_STATUS, "aligning X");
                    System.out.println("\taligning x...");
                   //not yet aligned keep aligning
                }
            } 
            else 
            {
                SmartDashboard.putString(CAMERA_STATUS, "ERROR not fnd!");
                System.out.println("\tRECTANGELS NOT FOUND");
                //camera is not tracking rechtangles
            }
    }
    
    public void checkCameraButtons()
    {
        if(driveStick.getRawButton(6))
        {
            resetDesiredXYTarget();
        }
        else if(frisbeeStick.getRawButton(11))
        {
            winButton();
        }
        else
        {
            stopCamera();
        }
    }
   
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
    
    public void autonomous()
    {
        
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
    }
*/

    public void newCheckPitchMotorButton() {
                      
    	double pitchSpeed = 0.5;
    	boolean pitchUpVal = frisbeeStick.getRawButton(11);
    	boolean pitchDownVal = frisbeeStick.getRawButton(12);
        
    	if(pitchUpVal){
    		shootingPitchMotor.set(pitchSpeed * 1.0);
    	} else if (pitchDownVal){
    		shootingPitchMotor.set(pitchSpeed * -1.0);
    	}else{
    		shootingPitchMotor.set(0.0);

    	}
    		
    	
    }

    
   /*
    public void checkShootingPitchMotorButton() {
        try {
       //shootingPitchMotor.setX(0);
                      
       if(!frisbeeStick.getRawButton(11))  //not pressed auto-aim button
        {
            
                double joy_y_val = frisbeeStick.getY();
                
                if (joy_y_val > 0)
                {
                    if( highLimitSwitch.get() == false)
                    {
                        shootingPitchMotor.set(joy_y_val);
                    }
                    else
                    {
                        shootingPitchMotor.set(0);
                    }
                } 
                if (joy_y_val < 0 )
                {
                    if( lowLimitSwitch.get() == false)
                    {
                        shootingPitchMotor.set(joy_y_val);
                    }
                    else
                    {
                        shootingPitchMotor.set(0);
                    }
                }
         
            }
        
           } catch (Exception e) {
                System.out.println(e);
           }
    }

    public void debug_CheckRelays() {

        System.out.print("\nChecking Status of Relays:");

        System.out.println("\tcompressorRelay: " + compressorRelay.get().value);
        System.out.println("\tloadingRelay: " + loadingRelay.get().value);
        System.out.println("\tclimbRelay: " + climbRelay.get().value);
        
        System.out.println("\thighLimitSwitch: " + highLimitSwitch.get());
        System.out.println("\tlowLimitSwitch: " + lowLimitSwitch.get());
        

    }

    public void compressorRelaySwitchOn() {

        compressorRelay.set(Relay.Value.kOn);
        //System.out.println("Compressor Relay Value Now: " + compressorRelay.get().value);

    }

    public void compressorRelaySwitchOff() {

        compressorRelay.set(Relay.Value.kOff);
        //System.out.println("Compressor Relay Value Now: " + compressorRelay.get().value);
    }

    public void loadingRelaySwitchFwd() {
        loadingRelay.set(Relay.Value.kForward);
        //System.out.println("Loading Relay Value Now: " + loadingRelay.get().value);

    }

    public void loadingRelaySwitchBack() {
        loadingRelay.set(Relay.Value.kReverse);
        //System.out.println("Loading Relay Value Now: " + loadingRelay.get().value);
    }

    public void loadingRelaySwitchOff() {
        loadingRelay.set(Relay.Value.kOff);
        System.out.println("Loading Relay Value Now: " + loadingRelay.get().value);

    }

    public void climbRelaySwitchFwd() {

        climbRelay.set(Relay.Value.kForward);
        System.out.print("Climb Relay Value: " + climbRelay.get().value);
        SmartDashboard.putString("Climb piston", "down");
        

    }

    public void climbRelaySwitchBack() {
        climbRelay.set(Relay.Value.kReverse);
        System.out.print("Climb Relay Value: " + climbRelay.get().value);
        SmartDashboard.putString("Climb piston", "up");
    }
*/
    public void climbRelaySwitchOff() {
        climbRelay.set(Relay.Value.kOff);
        //System.out.print("Climb Relay Value: " + climbRelay.get().value);
        //SmartDashboard.putInt("Climb Relay Value", climbRelay.get().value);

    }
    public void turnShootingWheelOn() 
    {
    if (isShootingWheelSpinning == false)
    {
        try {
                isShootingWheelSpinning = true;
                shootingWheel1FWD.set(-1.0);
                shootingWheel1REV.set(-1.0);
                shootingWheel2FWD.set(-1.0);
                shootingWheel2REV.set(-1.0);
        }
        catch (Exception e) 
        {
        }
    }
    }
    public void turnShootingWheelOff() 
    {
    if (isShootingWheelSpinning)
    {
        try {
        isShootingWheelSpinning = false;
                shootingWheel1FWD.set(0);
                shootingWheel1REV.set(0);
                shootingWheel2FWD.set(0);
                shootingWheel2REV.set(0);
        }
        catch (Exception e)
        {   
        }   
    }
    }
    public void checkShootingWheelButton() {
        try {
            if (frisbeeStick.getRawButton(9)) {
                shooting_motor_switch = true;
            } 
            if (frisbeeStick.getRawButton(10)) {
                shooting_motor_switch = false;
            }

            if (shooting_motor_switch == true) {
                shootingWheel1FWD.set(-1.0);
                shootingWheel1REV.set(-1.0);
                shootingWheel2FWD.set(-1.0);
                shootingWheel2REV.set(-1.0);

            } else {
                shootingWheel1FWD.set(0);
                shootingWheel1REV.set(0);
                shootingWheel2FWD.set(0);
                shootingWheel2REV.set(0);
            }
        } catch (Exception e) {
        }
    }

    public void checkLaunchingButton() {
        if (frisbeeStick.getRawButton(1) && is_launching == false) {
            is_launching = true;
            shooting_motor_switch = true;
            launchingTimer.reset();
            launchingTimer.start();
        }

        if (is_launching) {
            if (launchingTimer.get() < 0.4) {
                //loadingRelaySwitchBack();
            } 
            else if ( launchingTimer.get() > 0.4 && launchingTimer.get() < 0.8 ){
                //loadingRelaySwitchFwd();
            }
            else {   
                is_launching = false;
                
            }
        }
        if(launchingTimer.get()> 4)
        {
            launchingTimer.stop();
            shooting_motor_switch = false;
        }
    }
    /*
    public void test_adjustRobotTurnSpeed()
    {
        if(driveStick.getRawButton(3))
        {
            ROBOT_TURN_SPEED += 0.01;
        }
        if(driveStick.getRawButton(2))
        {
            ROBOT_TURN_SPEED -= 0.01;
        }
        if(driveStick.getRawButton(5))
        {
            jagDrive(ROBOT_TURN_SPEED, 1);
        }
        else if(driveStick.getRawButton(4))
        {
            jagDrive(ROBOT_TURN_SPEED, -1);
        }
        else
        {
            jagDrive(0,0);
        }
        SmartDashboard.putDouble("ROBOT_TURN_SPEED", ROBOT_TURN_SPEED);
    }
    */
   
    
/*
    
    public void checkDriveJoysticks() {
        if(driveStick.getRawButton(8))
        {
            if(slow_drive_mode)
            {
                slow_drive_mode = false;
            }
            else
            {
                slow_drive_mode = true;
            }
        }



        //if(!frisbeeStick.getRawButton(11)) //not pressed auto-aim button
        if(true)
        {
        
            if (slow_drive_mode) {
                double leftJoyVal = driveStick.getY();
                double rightJoyVal = frisbeeStick.getY();
                if(driveStick.getY() > 0 )
                {
                    leftJoyVal = leftJoyVal * leftJoyVal * .6 + .4;
                }
                
                else if (driveStick.getY() < 0)
                
                {
                    leftJoyVal = leftJoyVal * leftJoyVal * -.6 - .4;
                }
                else
                {
                    leftJoyVal = 0;
                }
                
                if(driveStick.getY() > 0)
                {
                    rightJoyVal = rightJoyVal * rightJoyVal * .6 + .4;
                }
                else if (driveStick.getY() < 0)
                {
                    rightJoyVal = rightJoyVal * rightJoyVal * -.6 - .4;
                   
                }
                else 
                {
                    rightJoyVal = 0;
                 }
                hkDrive.tankDrive(leftJoyVal * -1, rightJoyVal * -1, true);
            } else {

                hkDrive.tankDrive(driveStick.getY() * -1, frisbeeStick.getY() * - 1, true);
            }
        }
        
    }

*/

}
