package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TiltSystem;

public class TiltDownTeleop extends CommandBase {

    private final TiltSystem tiltSystem;

    public TiltDownTeleop(TiltSystem tiltSystem) {

        this.tiltSystem = tiltSystem;
        addRequirements(this.tiltSystem);

    }

    @Override
    public void initialize() {
        tiltSystem.set(false);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
