package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TankDrive;

public class RegularDrive extends CommandBase {
    private final TankDrive drive;
    private Timer timer;

    public RegularDrive(TankDrive drive) {
        this.drive = drive;
        timer = new Timer();
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        drive.set(1, 1);
    }

    @Override
    public void execute() {
        // Uncomment for drift calibration
//        steeringInput = steeringInput > -0.25 && steeringInput < 0.25 ? 0 : steeringInput;
        if (timer.hasElapsed(3)) {
            drive.stop();
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
