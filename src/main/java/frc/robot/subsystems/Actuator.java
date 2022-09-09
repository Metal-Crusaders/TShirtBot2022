package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Actuator extends SubsystemBase {

    private final VictorSP motor;

    public Actuator(VictorSP motor) {
        this.motor = motor;
    }

    public void set(double speed) {
        this.motor.setVoltage(speed * -12);
    }

    public void stop() {
        this.motor.set(0);
    }

    public VictorSP getMotor() {
        return this.motor;
    }
}
