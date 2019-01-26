package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.RobotMap;

public class Pneumatics {
    Compressor comp = new Compressor(RobotMap.COMPRESSOR_PORT);

    public Pneumatics() {
        this.comp.setClosedLoopControl(true);
        this.comp.start();
        SmartDashboard.putString("compressorStatus", "is on");
    }

}