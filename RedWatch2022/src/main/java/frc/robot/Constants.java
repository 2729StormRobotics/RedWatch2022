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

    public static final int kIndexMotorPort = 0;
    public static final int kDRIVE_AMPERAGE_PEAK_DURATION = 100;
    public static final int kCAN_TIMEOUT_SETUP = 500;
    public static final int kDRIVE_AMPERAGE_LIMIT_PEAK = 50;
    public static final int kDRIVE_AMPERAGE_LIMIT_CONTINUOUS = 35;
    public static final int kBeamBreakPort = 0;

    public static final int TOP_MOTOR_ID = 8;
    public static final boolean kTopReversedDefault = false;
    public static final int STALL_LIMIT = 45;
    public static final int kCurrentLimit = 60
    ;
    public static final int LEFT_MOTOR_ID = 2;
    public static final int RIGHT_MOTOR_ID = 1;
    public static final boolean kLeftReversedDefault = true;
    public static final boolean kRightReversedDefault = !kLeftReversedDefault;
    public static final String kShuffleboardTab = "Testing";
    public static final int LEFT_MOTOR2_ID = 0;
    public static final int RIGHT_MOTOR2_ID = 0;

    // since the encoder is build into the motor we need to account for gearing
	private static final double kWheelDiameterInches = 6.0;
	private static final double kInitialGear = 14.0 / 58.0 * 18.0 / 38.0;
	private static final double kHighGear = kInitialGear * 32.0 / 34.0;
	private static final double kLowGear = kInitialGear * 22.0 / 44.0;

	// all measurements are based on inches and seconds
	public static final double kHighDistancePerPulse = kWheelDiameterInches * Math.PI * kHighGear;
	public static final double kHighSpeedPerPulse = kHighDistancePerPulse / 60.0;
	public static final double kLowDistancePerPulse = kWheelDiameterInches * Math.PI * kLowGear;
	public static final double kLowSpeedPerPulse = kLowDistancePerPulse / 60.0;

	// experimentally determined (inches per encoder count)
	public static final double kEncoderDistanceRatio = 1.125753635;
	public static double kRightAngleTurnArcLength = 7.25 * Math.PI;
    public static final double kHighSpeedPerPulseEncoderRatio = kEncoderDistanceRatio / 60.0;

}
