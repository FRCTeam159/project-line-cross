import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team.robot.Robot;

public class DriveStraight extends Command {
    private double time;
    private double speed;
    private double delay;
    private Timer timer = new Timer();

//     Used to determine if the robot has started moving yet
    private boolean started = false;

    public DriveStraight(double speed, double time, double moveDelay) {
        this.speed = speed;
        this.time = time;
        this.delay = moveDelay;
    }

    protected void initialize(){
//         Ensures the timer is started at 0
        timer.reset();
        timer.start();

//         Starts driving if there is no delay
        if(delay == 0) {
            started = true;
            Robot.driveTrain.setSpeed(speed);
        }
    }

    protected void execute() {
//         Starts if not already started and past delay
        if(!started && timer.get() > delay){
            started = true;
            Robot.driveTrain.setSpeed(speed);
        }
    }

    protected boolean isFinished() {
//         Gets the current time from the timer and compares against run time
        return timer.get() >= time;
    }

    protected void interrupted(){
        end();
    }

    protected void end(){
//         Disables the drive train on end
        Robot.driveTrain.disable();
    }
}
