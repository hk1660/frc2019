package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Lift {

    DoubleSolenoid piston;

    piston = new DoubleSolenoid(1, 2);

    piston.set(DoubleSolenoid.Value.kOff);
    piston.set(DoubleSolenoid.Value.kForward);
    piston.set(DoubleSolenoid.Value.kReverse);
}