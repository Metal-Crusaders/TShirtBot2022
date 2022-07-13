package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {

    private XboxController xbox;

    public OI() {
        this.xbox = new XboxController(RobotMap.Controller);
    }

    public double getXboxLeftY() {
        return -this.xbox.getLeftY();
    }

    public double getXboxLeftX() {
        return -this.xbox.getLeftX();
    }

    public double getXboxRightY() {
        return this.xbox.getRightY();
    }

    public double getXboxRightX() {
        return this.xbox.getRightX();
    }

    public double getXboxLeftTrigger() {
        return this.xbox.getLeftTriggerAxis();
    }

    public double getXboxRightTrigger() {
        return this.xbox.getRightTriggerAxis();
    }

    public XboxController getXbox() {
        return this.xbox;
    }

}