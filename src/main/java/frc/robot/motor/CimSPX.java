package frc.robot.motor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class CimSPX extends VictorSPX implements MotorController {

    private Encoder encoder;

    public CimSPX(int channel, boolean reverse) {
        super(channel);
        super.setInverted(reverse);
    }

    public CimSPX(int channel, boolean reverse, Encoder encoder) {
        super(channel);
        super.setInverted(reverse);
        this.encoder = encoder;
    }
    @Override
    public void set(double power) {
        super.set(ControlMode.PercentOutput, power > 1 ? 1 : power < -1 ? -1 : power);
    }

    @Override
    public double get() {
        return 0;
    }

    @Override
    public void disable() {

    }

    @Override
    public void stopMotor() {

    }

    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    public Encoder getEncoder() {
        return this.encoder;
    }

    public void resetEncoder() {
        if (this.encoder != null) {
            this.encoder.reset();
        }
    }

    public void setReversed(boolean reverse) {
        super.setInverted(reverse);
    }

    public boolean isReversed() {
        return super.getInverted();
    }

    public void clearErrors() {
        super.clearStickyFaults();
    }

    public void brake() {
        super.setNeutralMode(NeutralMode.Brake);
    }

    public void coast() {
        super.setNeutralMode(NeutralMode.Coast);
    }

    public void stop() {
        super.set(ControlMode.PercentOutput, 0);
    }

    public void follow(CimSPX master) {
        super.set(ControlMode.Follower, master.getDeviceID());
    }
}
