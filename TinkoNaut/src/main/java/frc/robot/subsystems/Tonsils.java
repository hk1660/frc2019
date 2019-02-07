package frc.robot.subsystems;

/*-----IMPORTED LIBRARIES-----*/
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;


public class Tonsils extends Subsystem{

    private WPI_TalonSRX tonsilMotorLeft;
    private WPI_TalonSRX tonsilMotorRight;

    public Tonsils(){
        //Tonsil Motor prime = tonsil motor left
        //tonsilMotorLeft = new WPI_TalonSRX(RobotMap.TONSIL_PRIME_CHANNEL);
        tonsilMotorRight = new WPI_TalonSRX(RobotMap.TONSIL_CHANNEL);
    }

    public void moveRollers(double speed){
        //tonsilMotorLeft.set(speed);
        tonsilMotorRight.set(speed);
    }

    public void log() {
    }


    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }

}