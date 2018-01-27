/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team/**XXX**/.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	// Motor controllers
	Spark leftMaster;
	Spark leftSlave; // Comment (ctl + /) this line if using PWM Y-Wire, or only using 1 motor on left side
	Spark rightMaster;
	Spark rightSlave; // See above
	
	SpeedControllerGroup leftGearbox;
	SpeedControllerGroup rightGearbox;
	DifferentialDrive drive;
	
	double autoWaitTime;
	double autoDriveTime;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Define the motor controllers for the left gearbox
		leftMaster = new Spark(0); // TODO: Make sure these are the same as the PWM wires in the RoboRIO, and that you are using SPARKs
		leftSlave = new Spark(1);
		leftGearbox = new SpeedControllerGroup(leftMaster, leftSlave);
		
		// Right gearbox
		rightMaster = new Spark(2);
		rightSlave = new Spark(3);
		rightGearbox = new SpeedControllerGroup(rightMaster, rightSlave);
		
		drive = new DifferentialDrive(leftGearbox, rightGearbox);
		
		SmartDashboard.setDefaultNumber("Wait Timer", 0);
	}

	/**
	 * This function is called once at the start of autonomous.
	 */
	@Override
	public void autonomousInit() {
		autoWaitTime = SmartDashboard.getNumber("Wait Timer", 0); // Gets how long to wait before moving forwards, drivers must type this in when setting up the match
		autoDriveTime = 2;  // TODO: See if this drives where you need it to be
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		double timeElapsed = 15 - DriverStation.getInstance().getMatchTime(); // The DriverStation gives an approximate time until the end of the period
		
		if (timeElapsed >= autoWaitTime) {
			if (timeElapsed <= autoWaitTime + autoDriveTime) {
				drive.tankDrive(0.2, 0.2); // Left and Right speeds, 20% power
			}
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		// Put your teleop code here
	}
}
