// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveTeleop;
import frc.robot.motor.CimSPX;
import frc.robot.subsystems.TankDrive;

public class RobotContainer {

    //Motors
    public CimSPX leftFront, leftRear, rightFront, rightRear;

    public OI oi;

    //Subsystems
    public TankDrive drive;
    //Commands
    public DriveTeleop driveTeleop;

    public RobotContainer() {

        oi = new OI();

        //Drivetrain
        leftFront = new CimSPX(RobotMap.leftFront, RobotMap.leftFrontReverse);
        leftRear = new CimSPX(RobotMap.leftRear, RobotMap.leftRearReverse);
        rightFront = new CimSPX(RobotMap.rightFront, RobotMap.rightFrontReverse);
        rightRear = new CimSPX(RobotMap.rightRear, RobotMap.rightRearReverse);

        leftRear.follow(leftFront);
        rightRear.follow(rightFront);

        drive = new TankDrive(leftFront, rightFront);
        driveTeleop = new DriveTeleop(drive, oi.getXboxLeftTrigger(), oi.getXboxRightTrigger(), oi.getXboxLeftX());
        configureButtonBindings();
    }

    private void configureButtonBindings() {
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
