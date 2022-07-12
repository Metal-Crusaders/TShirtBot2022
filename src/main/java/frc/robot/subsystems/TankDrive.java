package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.motor.CimSPX;

public class TankDrive extends SubsystemBase {
    private final CimSPX left, right;

    public TankDrive(CimSPX left, CimSPX right) {
        this.left = left;
        this.right = right;
    }

    public void set(double left, double right) {
        this.left.set(left);
        this.right.set(right);
    }

    public void stop() {
        this.brake();
        this.left.stop();
        this.right.stop();
    }

    public void coast() {
        this.left.coast();
        this.right.coast();
    }

    public void brake() {
        this.left.brake();
        this.right.brake();
    }

    public void setReversed(boolean left, boolean right) {
        this.left.setReversed(left);
        this.right.setReversed(right);
    }
}
