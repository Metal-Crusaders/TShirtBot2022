package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TiltSystem;

public class TiltTeleop extends CommandBase {

    private final TiltSystem tiltSystem;
    private boolean onInput;

    public TiltTeleop(TiltSystem tiltSystem, boolean onInput) {

        this.tiltSystem = tiltSystem;
        this.onInput = onInput;
        addRequirements(this.tiltSystem);

    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        tiltSystem.set(onInput);
    }

    @Override
    public void end(boolean interrupted) {
        tiltSystem.set(false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
