// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveTeleop;
import frc.robot.motor.VictorSPX;
import frc.robot.subsystems.TankDrive;

public class RobotContainer {

    //Motors
    public VictorSPX leftFront, leftRear, rightFront, rightRear;

    public OI oi;

    //Subsystems
    public TankDrive drive;
    //Commands
    public DriveTeleop driveTeleop;

    public RobotContainer() {

        oi = new OI();

        //Drivetrain
        leftFront = new VictorSPX(RobotMap.leftFront, RobotMap.leftFrontReverse);
        leftRear = new VictorSPX(RobotMap.leftRear, RobotMap.leftRearReverse);
        rightFront = new VictorSPX(RobotMap.rightFront, RobotMap.rightFrontReverse);
        rightRear = new VictorSPX(RobotMap.rightRear, RobotMap.rightRearReverse);

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
