/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.commands.BlinkinCargoColor;


public class Blinkin extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private Spark spark;
  public DigitalInput cargoButton;

  public double red = 0.61;
  public double darkRed = 0.57;
  public double hotPink = 0.59;
  
  public double green = 0.77;
  public double blue = 0.89;
  public double rainbow = -0.99;
  public double heartBeatSlow = 0.03;
  public double heartBeatFast = 0.07;
  

  public Blinkin(){
    //Initialize LED to Red
    spark = new Spark (RobotMap.BLINKIN_PWM_CHANNEL);
    cargoButton = new DigitalInput(RobotMap.CARGO_BUTTON_CHANNEL);

    setRed();

  }

  public void setLEDColor(int color){
    spark.set(color);
  }

  public void setGreen(){
    spark.set(green);
  }

  public void setRed(){
    spark.set(red);
  }

  public void setDarkRed(){
    spark.set(darkRed);
  }

  public void setHotPink(){
    spark.set(hotPink);
  }



  public void setRainbow(){
    spark.set(rainbow);
  }


  public boolean hasCargo(){
    return cargoButton.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   setDefaultCommand(new BlinkinCargoColor());
  }

  public void log() {
  }


}
