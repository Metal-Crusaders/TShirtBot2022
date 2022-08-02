// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.commands.*;
import frc.robot.sensors.CustomButton;
import frc.robot.motor.MyVictorSPX;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.TiltSystem;

public class RobotContainer {

    //Motors
    public MyVictorSPX leftFront, leftRear, rightFront, rightRear;

    //Pneumatics
    public Solenoid tshirtSolenoid;
    public Solenoid tiltLeftSolenoid;
    public Solenoid tiltRightSolenoid;
    public TiltSystem tiltSystem;

    //OI
    public OI oi;
    public CustomButton driveRegBtn;
    public CustomButton shootBtn;
    public CustomButton tiltUpBtn;
    public CustomButton tiltDownBtn;

    //Subsystems
    public TankDrive drive;

    //Commands
    public RegularDrive regDrive;
    public DriveTeleop driveTeleop;
    public ShootTeleop shootTeleop;
    public TiltUpTeleop tiltUpTeleop;
    public TiltDownTeleop tiltDownTeleop;

    public RobotContainer() {

        //Drivetrain
        leftFront = new MyVictorSPX(RobotMap.leftFront, RobotMap.leftFrontReverse);
        leftRear = new MyVictorSPX(RobotMap.leftRear, RobotMap.leftRearReverse);
        rightFront = new MyVictorSPX(RobotMap.rightFront, RobotMap.rightFrontReverse);
        rightRear = new MyVictorSPX(RobotMap.rightRear, RobotMap.rightRearReverse);

        leftRear.follow(leftFront);
        rightRear.follow(rightFront);

        drive = new TankDrive(leftFront, rightFront);

        //Pneumatics / Shooter
        tshirtSolenoid = new Solenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.tShirtSolenoid);
        tiltLeftSolenoid = new Solenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.tiltLeftSolenoid);
        tiltRightSolenoid = new Solenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.tiltRightSolenoid);

        tiltSystem = new TiltSystem(tiltLeftSolenoid, tiltRightSolenoid);

        //OI
        oi = new OI();
        driveRegBtn = new CustomButton(oi.getXbox(), OI.XBOX_B);
        shootBtn = new CustomButton(oi.getXbox(), OI.XBOX_A);
        tiltUpBtn = new CustomButton(oi.getXbox(), OI.XBOX_X);
        tiltDownBtn = new CustomButton(oi.getXbox(), OI.XBOX_Y);

        //Commands
        regDrive = new RegularDrive(drive);
        driveTeleop = new DriveTeleop(drive, oi.getXboxLeftTrigger(), oi.getXboxRightTrigger(), oi.getXboxLeftX());
        shootTeleop = new ShootTeleop(tshirtSolenoid);
        tiltUpTeleop = new TiltUpTeleop(tiltSystem);
        tiltDownTeleop = new TiltDownTeleop(tiltSystem);

//        drive.setDefaultCommand(driveTeleop);

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        driveRegBtn.toggleWhenPressed(regDrive);
        shootBtn.toggleWhenPressed(shootTeleop);
        tiltUpBtn.toggleWhenPressed(tiltUpTeleop);
        tiltDownBtn.toggleWhenPressed(tiltDownTeleop);
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
