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

    public static final int TOP_MOTOR_ID = 8;
    public static final int BOTTOM_MOTOR_ID = 9;
    public static final int kBeamBreak = 4;
    public static final int kDriverControllerPort = 0;
    public static double kIndexerSpeed = 0.5;

    public static final class VisionConstants {
        public static final double kLimelightHeight = 0.0; //TODO: Once limelight is mounted measure the distance from the center of the camera to the ground
        public static final double kLimelightAngle = 0.0; //TODO: Once limelight is mounted measure angle of limelight from horizontal to limelight
        public static final double kHubHeight = 0.0; //TODO: Look at manual to find distance from floor to top of the hub

        public static final int kDefaultPipeline = 0;


    }
    }
