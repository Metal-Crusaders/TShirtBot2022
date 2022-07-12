// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveTeleop;
import frc.robot.motor.CimSRX;
import frc.robot.subsystems.TankDrive;

public class RobotContainer {

    //Motors
    public CimSRX leftFront, leftRear, rightFront, rightRear;

    //Solenoids
    public Solenoid tshirtSolenoid;

    //Subsystems
    public TankDrive drive;
    //Commands
    public DriveTeleop driveTeleop;

    public RobotContainer() {

        //Drivetrain
        leftFront = new CimSRX(RobotMap.leftFront, RobotMap.leftFrontReverse);
        leftRear = new CimSRX(RobotMap.leftRear, RobotMap.leftRearReverse);
        rightFront = new CimSRX(RobotMap.rightFront, RobotMap.rightFrontReverse);
        rightRear = new CimSRX(RobotMap.rightRear, RobotMap.rightRearReverse);

        leftRear.follow(leftFront);
        rightRear.follow(rightFront);

        drive = new TankDrive(leftFront, rightFront);

        configureButtonBindings();
    }

    private void configureButtonBindings() {
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
