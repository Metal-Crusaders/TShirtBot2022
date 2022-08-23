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
    public Solenoid tshirtSolenoidA;
    public Solenoid tshirtSolenoidB;
    public Solenoid tiltLeftSolenoid;
    public Solenoid tiltRightSolenoid;
    public TiltSystem tiltSystem;

    //OI
    public OI oi;
    public CustomButton shootABtn;
    public CustomButton shootBBtn;
    public CustomButton tiltUpBtn;
    public CustomButton tiltDownBtn;

    //Subsystems
    public TankDrive drive;

    //Commands
    public DriveTeleop driveTeleop;
    public ShootTeleop shootTeleopA;
    public ShootTeleop shootTeleopB;
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
        tshirtSolenoidA = new Solenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.tShirtSolenoidA);
        tshirtSolenoidB = new Solenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.tShirtSolenoidB);
        tiltLeftSolenoid = new Solenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.tiltLeftSolenoid);
        tiltRightSolenoid = new Solenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.tiltRightSolenoid);

        tiltSystem = new TiltSystem(tiltLeftSolenoid, tiltRightSolenoid);

        //OI
        oi = new OI();
        shootABtn = new CustomButton(oi.getXbox(), OI.XBOX_A);
        shootBBtn = new CustomButton(oi.getXbox(), OI.XBOX_B);
        tiltUpBtn = new CustomButton(oi.getXbox(), OI.XBOX_X);
        tiltDownBtn = new CustomButton(oi.getXbox(), OI.XBOX_Y);

        //Commands
        driveTeleop = new DriveTeleop(drive, oi::getXboxLeftTrigger, oi::getXboxRightTrigger, oi::getXboxLeftX);
        shootTeleopA = new ShootTeleop(tshirtSolenoidA);
        shootTeleopB = new ShootTeleop(tshirtSolenoidB);
        tiltUpTeleop = new TiltUpTeleop(tiltSystem);
        tiltDownTeleop = new TiltDownTeleop(tiltSystem);

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        shootABtn.toggleWhenPressed(shootTeleopA);
        shootBBtn.toggleWhenPressed(shootTeleopB);
        tiltUpBtn.toggleWhenPressed(tiltUpTeleop);
        tiltDownBtn.toggleWhenPressed(tiltDownTeleop);
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
