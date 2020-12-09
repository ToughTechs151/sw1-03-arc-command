package frc.robot;

/**
 * We continue on in the same style, creating new subclasses for 
 * related constants. This time we are adding an autonomous command
 * to run during the autonomous period, so we make a AutoConstants
 * class to hold them. 
 **/
public final class Constants {
    public static final class DriveConstants {
        public static final int kLeftMotorPort = 0;
        public static final int kRightMotorPort = 1;

    }
    public static final class AutoConstants {
        public static final double kAutoWaitSeconds = 3.0;
        public static final double kAutoSpeed = 0.5;
        public static final double kAutoDriveTime = 8.0;
    }
    public static final class OIConstants {
        public static final int kJoystickPort = 0;
    }
}
