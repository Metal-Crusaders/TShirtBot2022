package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TankDrive;

public class DriveTeleop extends CommandBase {
    private final TankDrive drive;
    private double leftInput, rightInput, steeringInput, throttle;

    private final double SENSITIVITY = 1;

    public DriveTeleop(TankDrive drive, double leftInput, double rightInput, double steeringInput) {
        this.drive = drive;
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.steeringInput = steeringInput;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // Uncomment for drift calibration
//        steeringInput = steeringInput > -0.25 && steeringInput < 0.25 ? 0 : steeringInput;
        throttle = rightInput - leftInput;
        drive.set(throttle + steeringInput, throttle - steeringInput);
        if (throttle != 0) {
            SmartDashboard.putNumber("Driving?", 1);
        } else {
            SmartDashboard.putNumber("Driving?", 0);
        }
//        //SmartDashboard
//        SmartDashboard.putNumber("Speed of LeftFront: ", leftFront.getPercentSpeed());
//        SmartDashboard.putNumber("Speed of LeftRear: ", m_robotContainer.leftRear.getPercentSpeed());
//        SmartDashboard.putNumber("Speed of RightFront: ", m_robotContainer.rightFront.getPercentSpeed());
//        SmartDashboard.putNumber("Speed of RightRear: ", m_robotContainer.rightRear.getPercentSpeed());

    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
