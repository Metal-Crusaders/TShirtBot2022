package frc.robot.motor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class MyVictorSPX extends WPI_VictorSPX {

    static class Filter {
        private double sensitivity;
        private double value = 0;

        public Filter(double sensitivity) {
            this.sensitivity = sensitivity;
        }

        public void update(double newValue) {
            value = sensitivity * newValue + (1 - sensitivity) * value;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }

    private static final double DEFAULT_PERCENT_FILTER = 1.0;
    private Filter percentOutputFilter;

    private static final int TIMEOUT_MS = 30;

    private MyVictorSPX brownoutFollower = null;
    private boolean brownout = false;

    /**
     * Constructor
     */
    public MyVictorSPX(int channel, boolean reversed) {
        super(channel);
        super.setInverted(reversed);
        percentOutputFilter = new Filter(DEFAULT_PERCENT_FILTER);
    }
    public void set(double speed) {
        if (speed > 1) speed = 1;
        if (speed < -1) speed = -1;
        percentOutputFilter.update(speed);
        super.set(ControlMode.PercentOutput, percentOutputFilter.getValue());
    }
    public double getPercentSpeed() {
        return super.getMotorOutputPercent();
    }
    public void stop() {
        set(0);
    }
    public void brake() {
        this.set(0);
        super.setNeutralMode(NeutralMode.Brake);
    }
    public void coast() {
        this.set(0);
        super.setNeutralMode(NeutralMode.Coast);
    }
    public int getChannel() {
        return super.getDeviceID();
    }

    public boolean isReversed() {
        return super.getInverted();
    }

    public void setReversed(boolean reversed) {
        super.setInverted(reversed);
    }

    public double getError() {
        return super.getClosedLoopError(0);
    }

    public void enableBrownoutProtection() {
        if (brownoutFollower != null) {
            brownoutFollower.coast();
        }
        brownout = true;
    }

    public void disableBrownoutProtection() {
        if (brownoutFollower != null && brownout) {
            brownoutFollower.setNeutralMode(NeutralMode.Brake);
            brownoutFollower.set(ControlMode.Follower, getChannel());
        }
        brownout = false;
    }

}