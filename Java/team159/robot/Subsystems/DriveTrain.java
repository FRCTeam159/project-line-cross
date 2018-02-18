import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team.robot.Commands.DriveStraight;
import org.usfirst.frc.team.robot.Robot;
import org.usfirst.frc.team.robot.RobotMap;

public class DriveTrain extends Subsystem implements MotorSafety {

//     Talon SRX objects
    private WPI_TalonSRX frontLeft;
    private WPI_TalonSRX frontRight;
    private WPI_TalonSRX backLeft;
    private WPI_TalonSRX backRight;

    private MotorSafetyHelper safetyHelper = new MotorSafetyHelper(this);

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


//    Motor Safety functions
    @Override
    public void setExpiration(double timeout) {
        safetyHelper.setExpiration(timeout);
    }

    @Override
    public double getExpiration() {
        return safetyHelper.getExpiration();
    }

    @Override
    public boolean isAlive() {
        return safetyHelper.isAlive();
    }

    @Override
    public void stopMotor() {
        frontLeft.stopMotor();
        frontRight.stopMotor();
        backLeft.stopMotor();
        backRight.stopMotor();
        safetyHelper.feed();
    }

    @Override
    public void setSafetyEnabled(boolean enabled) {
        safetyHelper.setSafetyEnabled(enabled);
    }

    @Override
    public boolean isSafetyEnabled() {
        return safetyHelper.isSafetyEnabled();
    }

    @Override
    public String getDescription() {
        return "Robot Drive";
    }
}
