package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Limelight extends Subsystem {

    NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public void update() {
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
    }

    // //post to smart dashboard periodically
    public void log() {
        update();
        // //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }
    public NetworkTableEntry getTX(){
        return tx;
    }
    public NetworkTableEntry getTY(){
        return ty;
    }
    public NetworkTableEntry getTA(){
        return ta;
    }
    public double getTXdouble(){
        return tx.getDouble(-1.0);
    }
    public double getTYdouble(){
        return ty.getDouble(-1.0);
    }
    public double getTAdouble(){
        return ta.getDouble(-1.0);
    }
    @Override
    public void initDefaultCommand() {
    }

}