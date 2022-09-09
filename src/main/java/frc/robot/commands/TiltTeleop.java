package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Actuator;

import java.util.function.DoubleSupplier;

public class TiltTeleop extends CommandBase {

    private Actuator actuatorMotor;
    private DoubleSupplier tiltInput;

    private final double DEADBAND = 0.1;

    public TiltTeleop(Actuator actuatorMotor, DoubleSupplier tiltInput) {

        this.actuatorMotor = actuatorMotor;
        this.tiltInput = tiltInput;

        addRequirements(actuatorMotor);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        double tilt = tiltInput.getAsDouble();
        if (tilt > -DEADBAND && tilt < DEADBAND) {
            tilt = 0;
        }

        actuatorMotor.set(tilt);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.actuatorMotor.stop();
    }


}