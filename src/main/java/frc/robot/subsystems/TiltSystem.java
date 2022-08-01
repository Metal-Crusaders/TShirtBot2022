package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TiltSystem extends SubsystemBase{

    private final Solenoid leftSolenoid, rightSolenoid;

    public TiltSystem(Solenoid leftSolenoid, Solenoid rightSolenoid) {
        this.leftSolenoid = leftSolenoid;
        this.rightSolenoid = rightSolenoid;
    }

    public void set(boolean on) {
        this.leftSolenoid.set(on);
        this.rightSolenoid.set(on);
    }

    public Solenoid getLeftSolenoid() {
        return this.leftSolenoid;
    }

    public Solenoid getRightSolenoid() {
        return this.rightSolenoid;
    }

    public boolean leftOn() {
        return this.leftSolenoid.get();
    }

    public boolean rightOn() {
        return this.rightSolenoid.get();
    }

}
