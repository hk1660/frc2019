package frc.team1660.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem{

    private DoubleSolenoid piston;

    public Lift(){
        piston = new DoubleSolenoid(1, 2);
    }

/*
    piston.set(DoubleSolenoid.Value.kOff);
    piston.set(DoubleSolenoid.Value.kForward);
    piston.set(DoubleSolenoid.Value.kReverse);
*/

    @Override
	public void initDefaultCommand() {
	  // Set the default command for a subsystem here.
	  // setDefaultCommand(new MySpecialCommand());
	}
}