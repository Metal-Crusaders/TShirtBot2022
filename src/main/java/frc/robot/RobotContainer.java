// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.commands.*;
import frc.robot.sensors.CustomButton;
import frc.robot.motor.MyVictorSPX;
import frc.robot.subsystems.Actuator;
import frc.robot.subsystems.TankDrive;

public class RobotContainer {

    //Motors
    public MyVictorSPX leftFront, leftRear, rightFront, rightRear;

    public VictorSP actuatorMotor;

    //Pneumatics
    public Solenoid tshirtSolenoidA;
    public Solenoid tshirtSolenoidB;

    //OI
    public OI oi;
    public CustomButton shootABtn;
    public CustomButton shootBBtn;

    //Subsystems
    public TankDrive drive;
    public Actuator actuator;

    //Commands
    public DriveTeleop driveTeleop;
    public ShootTeleop shootTeleopA;
    public ShootTeleop shootTeleopB;
    public TiltTeleop tiltTeleop;

    public RobotContainer() {

        //Drivetrain
        leftFront = new MyVictorSPX(RobotMap.leftFront, RobotMap.leftFrontReverse);
        leftRear = new MyVictorSPX(RobotMap.leftRear, RobotMap.leftRearReverse);
        rightFront = new MyVictorSPX(RobotMap.rightFront, RobotMap.rightFrontReverse);
        rightRear = new MyVictorSPX(RobotMap.rightRear, RobotMap.rightRearReverse);

        leftRear.follow(leftFront);
        rightRear.follow(rightFront);

        drive = new TankDrive(leftFront, rightFront);

        actuatorMotor = new VictorSP(RobotMap.actuatorPWMId);
        actuator = new Actuator(actuatorMotor);

        //Pneumatics / Shooter
        tshirtSolenoidA = new Solenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.tShirtSolenoidA);
        tshirtSolenoidB = new Solenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.tShirtSolenoidB);

        //OI
        oi = new OI();
        shootABtn = new CustomButton(oi.getXbox(), OI.XBOX_A);
        shootBBtn = new CustomButton(oi.getXbox(), OI.XBOX_B);

        //Commands
        driveTeleop = new DriveTeleop(drive, oi::getXboxLeftTrigger, oi::getXboxRightTrigger, oi::getXboxLeftX);
        shootTeleopA = new ShootTeleop(tshirtSolenoidA);
        shootTeleopB = new ShootTeleop(tshirtSolenoidB);
        tiltTeleop = new TiltTeleop(actuator, oi::getXboxRightY);

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        shootABtn.toggleWhenPressed(shootTeleopA);
        shootBBtn.toggleWhenPressed(shootTeleopB);
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
