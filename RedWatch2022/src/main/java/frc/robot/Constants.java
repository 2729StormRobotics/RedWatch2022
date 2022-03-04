// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
	public static final class VisionConstants {
    public static final double kLimelightHeight = 32.0; //TODO: Rough estimate... get more exact distance
    public static final double kLimelightAngle = 0.0; //TODO: Once limelight is mounted measure angle of limelight from horizontal to limelight
    public static final double kHubHeight = 104.0;

    public static final int kDefaultPipeline = 0;

		public static final double kAutoAlignP = 0.03; //TODO: Testing to find this value
		public static final double kAutoAlignI = 0.09; //TODO: Testing to find this value
		public static final double kAutoAlignD = 0.0; //TODO: Testing to find this value

    public static final double kFlywheelRadius = 0.25; // flywheel radius in feet
    public static final double kRadsToRPM = 30 / Math.PI;  // Conversion factor from rad/s to RPM
    }

  public static final String kShuffleboardTab = "Control Panel";

  public static final class AutoRouteConstants {
    public static double kRobotLength = 67.0; //TODO: check later
  }

  public static final class ShooterConstants {
    public static final int kTopMotorPort = 3;
    public static final boolean kTopReversedDefault = false;
    public static final int kStallLimit = 45;
    public static final int kCurrentLimit = 60;
  }

  public static final class IndexerConstants {
    public static final int kIndexMotorPort = 6;
    public static final int kBeamBreakPort = 0;
    public static final int kDriveAmperagePeakDuration = 100;
    public static final int kCanTimeoutSetup = 500;
    public static final int kDriveAmperageLimitPeak = 50;
    public static final int kDriveAmperageLimitContinuous = 35;
    public static final double kIndexerSpeed = 0.9;
  }

	public static final class IntakeConstants {
    public static final int kIntakeMotorPort = 8;
    public static final int kIntakePiston1 = 4;
    public static final int kIntakePiston2 = 6;
    public static final double kIntakeMotorSpeed = 0.5;
    public static final double kEjectMotorSpeed = -0.5;
    public static final Value kIntakeRaiseValue = Value.kForward;
    public static final Value kIntakeLowerValue = Value.kReverse;
    public static final int kDriveAmperagePeakDuration = 100;
    public static final int kCanTimeoutSetup = 500;
    public static final int kDriveAmperageLimitPeak = 50;
    public static final int kDriveAmperageLimitContinuous = 35;
  }

  public static final class IOPorts {
    public static final int kDriverController = 1;
    public static final int kWeaponsController = 2;
  }

  public static class DrivetrainConstants {
    // based off PDP ports and the REV Spark Client. 0 is reassigned as 15.
    public static final int kLeftMotorPort = 12;
    public static final int kRightMotorPort = 15;
    public static final int kLeftMotor2Port = 14;
    public static final int kRightMotor2Port = 1;

    // since the encoder is build into the motor we need to account for gearing
    public static final double kWheelDiameter = 6.0;
    public static final double kGearRatio = 1.0 / 12.0;
    public static final double kDistancePerRevolution = kWheelDiameter * kGearRatio * 3.14;
    public static final double kSpeedPerRevolution = kDistancePerRevolution / 60.0;

    public static final int kCurrentLimit = 60;
    public static final boolean kLeftReversedDefault = true;
    public static final boolean kRightReversedDefault = !kLeftReversedDefault;

    public static final int kStallLimit = 45;
    public static final double kTurnAngleD = 0.0;
    public static final double kTurnAngleI = 0.0;
    public static final double kTurnAngleP = 0.0;
    public static final double kTurnAngleTolerace = 5.0;
    public static final double kTurnSpeedTolerance = 5.0;
    public static final double kAutoForwardI = 0.0;
    public static final double kAutoForwardP = 0.0029;
    public static final double kAutoForwardD = 0.0;
    public static final double kVelocityTolerance = 5.0;
    public static final double kPositionTolerace = 5.0;
  }
   
  public static class LightConstants {
    public static final double kDisabled = 0.0; //TODO: Find what color we want for this and its value
    public static final double kLightsOff = 0.99; // Black
    public static final double kRedBall = -0.11; // Strobe red -- intake red ball //0.61 is solid red
    public static final double kBlueBall = -.09; // Strobe blue -- intake blue ball //0.87 is solid blue
    public static final double kBad = 0.61; // Used for a bad orientation - color red
    public static final double kDefaultColor = 0.93; //TODO: Find what we want default to be (same as disabled?)
    public static final double kCorrect = 0.75; // Defines Dark Green
    public static final double kClimbSuccess = 0.97; // Rainbow party
    public static final int kBlinkinDriverPort = 0;
  }
   
  public static final class ClimberConstants {
		public static final int kClimberLeftFollowerExtendPort = 13;
		public static final int kClimberRightExtendPort = 2;
		public static final int kClimberLeftPivotFollowerPort = 9;
		public static final int kClimberRightPivotPort = 10;
    // pivoting gearbox = 1:125 
    public static final double kTelescopingGearRatio = 1.0 / 16.0;
    public static final double kPivotingGearRatio = 1.0 / 125.0;

    public static final double kAnglePerRevolution = kPivotingGearRatio * 3.14;
    public static final double kDistancePerRevolution = kTelescopingGearRatio * (7.0 / 8.0) * 3.14; //TODO: put in gear ratio for the climbers!!!
		public static final double kClimberRightSize = 12.0;
	}
}
