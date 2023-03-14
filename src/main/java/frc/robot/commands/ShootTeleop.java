package frc.robot.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootTeleop extends CommandBase {

    private final Solenoid solenoid;
    private Timer timer;
    private final double TIME = 0.5;

    public ShootTeleop(Solenoid solenoid) {
        this.solenoid = solenoid;
    }

    @Override
    public void initialize() {
        timer = new Timer();
        timer.reset();
        timer.start();
        solenoid.set(true);
    }

    @Override
    public void execute() {
        if (timer.advanceIfElapsed(TIME)) {
            solenoid.set(false);
        }
    }

    @Override
    public void end(boolean interrupted) {
        solenoid.set(false);
    }

    @Override
    public boolean isFinished() {
        return (timer.advanceIfElapsed(TIME));
    }
}
