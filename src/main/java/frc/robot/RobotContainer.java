// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.commands.DriveTeleop;
import frc.robot.commands.ShootTeleop;
import frc.robot.commands.TiltTeleop;
import frc.robot.sensors.CustomButton;
import frc.robot.motor.VictorSPX;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.TiltSystem;

public class RobotContainer {

    //Motors
    public VictorSPX leftFront, leftRear, rightFront, rightRear;

    //Pneumatics
    public Solenoid tshirtSolenoid;
    public Solenoid tiltLeftSolenoid;
    public Solenoid tiltRightSolenoid;
    public TiltSystem tiltSystem;

    //OI
    public OI oi;
    public CustomButton shootBtn;
    public CustomButton tiltBtn;

    //Subsystems
    public TankDrive drive;

    //Commands
    public DriveTeleop driveTeleop;
    public ShootTeleop shootTeleop;
    public TiltTeleop tiltTeleop;

    public RobotContainer() {

        //Drivetrain
        leftFront = new VictorSPX(RobotMap.leftFront, RobotMap.leftFrontReverse);
        leftRear = new VictorSPX(RobotMap.leftRear, RobotMap.leftRearReverse);
        rightFront = new VictorSPX(RobotMap.rightFront, RobotMap.rightFrontReverse);
        rightRear = new VictorSPX(RobotMap.rightRear, RobotMap.rightRearReverse);

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
        shootBtn = new CustomButton(oi.getXbox(), OI.XBOX_A);
        tiltBtn = new CustomButton(oi.getXbox(), OI.XBOX_X);

        //Commands
        driveTeleop = new DriveTeleop(drive, oi.getXboxLeftTrigger(), oi.getXboxRightTrigger(), oi.getXboxLeftX());
        shootTeleop = new ShootTeleop(tshirtSolenoid);
        tiltTeleop = new TiltTeleop(tiltSystem, tiltBtn.isHeld());

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        shootBtn.toggleWhenPressed(shootTeleop);
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
