import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team.robot.Commands.DriveStraight;
import org.usfirst.frc.team.robot.Robot;
import org.usfirst.frc.team.robot.RobotMap;

public class DriveTrain extends Subsystem {

//     Talon SRX objects
    private WPI_TalonSRX frontLeft;
    private WPI_TalonSRX frontRight;
    private WPI_TalonSRX backLeft;
    private WPI_TalonSRX backRight;

//     Used to invert direction if it is incorrect
    private boolean directionInverted = false;

    @Override
    protected void initDefaultCommand() {
//         Sets the default command to the DriveStraight command
        setDefaultCommand(new DriveStraight(Robot.driveStraightSpeed, Robot.driveStraightTime, Robot.driveStraightDelay));
    }

    public DriveTrain() {
        super();

//         Defines the Talon SRXs
        frontLeft = new WPI_TalonSRX(RobotMap.TALON_SRX_FRONT_LEFT);
        frontRight = new WPI_TalonSRX(RobotMap.TALON_SRX_FRONT_RIGHT);
        backLeft = new WPI_TalonSRX(RobotMap.TALON_SRX_BACK_LEFT);
        backRight = new WPI_TalonSRX(RobotMap.TALON_SRX_BACK_RIGHT);

//         Configures front left and back right Talon SRXs as slaves to the other Talon SRXs
        frontLeft.set(ControlMode.Follower, RobotMap.TALON_SRX_BACK_LEFT);
        backRight.set(ControlMode.Follower, RobotMap.TALON_SRX_FRONT_RIGHT);

//         Enables the motor safety helpers on all the Talon SRXs
        frontLeft.setSafetyEnabled(true);
        frontRight.setSafetyEnabled(true);
        backLeft.setSafetyEnabled(true);
        backRight.setSafetyEnabled(true);
    }

    public void disable(){
//         Disables the master Talon SRXs
        frontRight.disable();
        backLeft.disable();
    }

    public void setSpeed(double speed){
//         Inverts the speed if true
        if(directionInverted){
            speed *= -1;
        }

//         Sets the master Talon SRXs
        frontRight.set(-speed);
        backLeft.set(speed);
    }
}
