package frc.robot.commands;

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
