package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.RobotMap;

public class Pneumatics {
    Compressor comp = new Compressor(); //no compressor port needed for pcm?

    public Pneumatics() {
        this.comp.setClosedLoopControl(true);
        this.comp.start();
        SmartDashboard.putString("compressorStatus", "is on");
    }

    public double getAirPressure(){
        return 0.0;
    }

    public void log(){
        SmartDashboard.putNumber("Stored AirPressure", getAirPressure()); 
    }

}