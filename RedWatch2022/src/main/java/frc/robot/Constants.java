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

    public static final class ShooterConstants {
        public static final int TOP_MOTOR_ID = 1;
        public static final boolean kTopReversedDefault = false;
        public static final int STALL_LIMIT = 45;
        public static final int kCurrentLimit = 60;
    }
    
	//in percentage 
    public static final double kIndexerSpeed = .50;
    public static final int kDriverController = 1;
    public static final double kHighShootSpeed = 1;
    public static final double kLowShootSpeed = 0.5;
    public static final String kShooterTab = "Shooter";
    public static final double kFlyWheelPID = 0.0005;

    public static final class IndexerConstants {
        public static final String kShuffleboardTab = "Indexer";

        public static final int kIndexMotorPort = 3;
        public static final int kDRIVE_AMPERAGE_PEAK_DURATION = 100;
        public static final int kCAN_TIMEOUT_SETUP = 500;
        public static final int kDRIVE_AMPERAGE_LIMIT_PEAK = 50;
        public static final int kDRIVE_AMPERAGE_LIMIT_CONTINUOUS = 35;
        public static final int kBeamBreakPort = 1;
    }

    public static double kFlyWheelTolerance = 50.0;
    public static double kFlyWheelVelocityTolerance = 250;
    public static double kLowShootRPM = 5500.0;
}
