package frc.robot.subsystems;

/*-----IMPORTED LIBRARIES-----*/
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;


public class CargoGrabber extends Subsystem{

    private WPI_TalonSRX cargoRollerMotor;

    public CargoGrabber(){

        cargoRollerMotor = new WPI_TalonSRX(RobotMap.CARGO_ROLLER_CHANNEL);

    }


    public void moveRoller(double speed){

        cargoRollerMotor.set(-speed);

    }

    public void log() {
    }

    
    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }

}