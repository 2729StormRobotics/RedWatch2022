// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Drivetrain
    public static final int kCurrentLimit = 60;
    public static final int LEFT_MOTOR_ID = 2;
    public static final int RIGHT_MOTOR_ID = 1;
    public static final boolean kLeftReversedDefault = true;
    public static final boolean kRightReversedDefault = !kLeftReversedDefault;
    public static final String kShuffleboardTab = "Testing";
    public static final int LEFT_MOTOR2_ID = 0;
    public static final int RIGHT_MOTOR2_ID = 0;
    public static final int STALL_LIMIT = 45;

    public static final double kWheelDiameter = 6.0;
    public static final double kGearRatio = 1/13;
    public static final double kDistancePerRevolution = kWheelDiameter * kGearRatio;
    public static final double kSpeedPerRevolution = kDistancePerRevolution / 60.0;

    public static final class ShooterConstants {
        public static final int TOP_MOTOR_ID = 3;
        public static final boolean kTopReversedDefault = false;
        public static final int STALL_LIMIT = 45;
        public static final int kCurrentLimit = 60;
    }

    
	//in percentage 
    public static final double kIndexerSpeed = .50;
    public static final double kHighShootSpeed = 1;
    public static final double kLowShootSpeed = 0.5;


    public static final String kShooterTab = "Shooter";
    public static final double kFlyWheelPID = 0.25;
    public static final int kDriverController = 1;

    public static final class IndexerConstants {
        public static final String kShuffleboardTab = "Indexer";

        public static final int kIndexMotorPort = 6;
        public static final int kDRIVE_AMPERAGE_PEAK_DURATION = 100;
        public static final int kCAN_TIMEOUT_SETUP = 500;
        public static final int kDRIVE_AMPERAGE_LIMIT_PEAK = 50;
        public static final int kDRIVE_AMPERAGE_LIMIT_CONTINUOUS = 35;
        public static final int kBeamBreakPort = 2;
    }

    public static double kFlyWheelTolerance = 50.0;
    public static double kFlyWheelVelocityTolerance = 250;

    public static class LightConstants {
        public static final int kBlinkinDriverPort = 0; //TODO: Find a port for this
		public static final double kDisabled = 0.0; //TODO: Find what color we want for this and its value
		public static final double kLightsOff = 0.99;
        public static final double kRedBall = 0.61;
        public static final double kBlueBall = 0.87;
        public static final double kDefaultColor = 0.93; //TODO: Find what we want default to be (same as disabled?)
        public static final double kYellowBall = 0.69;
        public static final double kGreenBall = 0.75;
    }
}
