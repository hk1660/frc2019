/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.PWM;
import frc.robot.commands.BlinkinCargoColor;


public class Blinkin extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private Spark spark;
  public DigitalInput cargoButton;
  

  private int sparkChannel = 0;  //Change channel, this is just filler
  public int cargoButtonChannel = 2;

  public double red = 0.61;
  public double green = 0.77;
  public double blue = 0.89;
  public double rainbow = -0.99;
  public double heartBeatSlow = 0.03;
  public double heartBeatFast = 0.07;

  public BlinkLeds(){
    //Initialize LED to Red
    spark = new Spark (sparkChannel);
    cargoButton = new DigitalInput(cargoButtonChannel);

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

  public boolean hasCargo(){
    cargoButton.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    BlinkinCargoColor();
  }

  public void log() {
  }


}
