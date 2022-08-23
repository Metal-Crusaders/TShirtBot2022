package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {

    public static int XBOX_A = 1;
    public static int XBOX_B = 2;
    public static int XBOX_X = 3;
    public static int XBOX_Y = 4;

    private double DEADBAND = 0.07;

    private XboxController xbox;

    public OI() {
        this.xbox = new XboxController(RobotMap.Controller);
    }

    public double getXboxLeftY() {
        return -this.xbox.getLeftY();
    }

    public double getXboxLeftX() {
//        return ((((-1 * this.xbox.getLeftX()) >= (-1 * DEADBAND)) || ((-1 * this.xbox.getLeftX()) <= DEADBAND)) ? 0.0 : (-1 * this.xbox.getLeftX()));
        return (-this.xbox.getLeftX());
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