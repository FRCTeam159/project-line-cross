import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team.robot.Commands.DriveStraight;
import org.usfirst.frc.team.robot.Subsystems.DriveTrain;

public class Robot extends IterativeRobot {

    public static DriveTrain driveTrain;

//    TODO determine correct speed and time for your robot
//     DriveStraight variables
    public static double driveStraightSpeed = 0.3;
    public static double driveStraightTime = 5;
    public static double driveStraightDelay = 0;

    @Override
    public void robotInit() {
//         Creates drive train subsystem
        driveTrain = new DriveTrain();

//          Uncomment if you want to set the DriveStraight variables from SmartDashboard
//        SmartDashboard.putNumber("Drive Straight Speed", driveStraightSpeed);
//        SmartDashboard.putNumber("Drive Straight Time", driveStraightTime);
//        SmartDashboard.putNumber("Drive Straight Delay", driveStraightDelay);
    }

    @Override
    public void autonomousInit() {
//        Gets the values from SmartDashboard, if the keys do not exist it uses the variables in code
        double speed = SmartDashboard.getNumber("Drive Straight Speed", driveStraightSpeed);
        double time = SmartDashboard.getNumber("Drive Straight Time", driveStraightTime);
        double delay = SmartDashboard.getNumber("Drive Straight Delay", driveStraightDelay);

//         Creates and starts a new DriveStraight command
        DriveStraight driveStraight = new DriveStraight(speed, time, delay);
        driveStraight.start();
    }
}
