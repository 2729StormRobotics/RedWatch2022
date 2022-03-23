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
    public static final double kLimelightHeight = 33.0; //TODO: Rough estimate... get more exact distance
    public static final double kLimelightAngle = 40.0; //TODO: Rough estimate... get more exact
    public static final double kLimelightToShooter = 4.5;  // TODO: Distance from limelight to shooter
    public static final double kHubHeight = 104.0;
    public static final double kShooterAngle = 80.5;

    public static final int kDefaultPipeline = 0;

		public static final double kAutoAlignP = 0.011; //TODO: Testing to find this value- 0.025
		public static final double kAutoAlignI = 0.025;  //TODO: Testing to find this value- 0
		public static final double kAutoAlignD = 0.0; //TODO: Testing to find this value

    public static final double kFlywheelRadius = 0.25; // flywheel radius in feet
    public static final double kRadsToRPM = 30 / Math.PI;  // Conversion factor from rad/s to RPM
    }

  public static final class ShuffleboardConstants {
    public static final String kShuffleboardTab = "Control Panel";
  }

  public static final class AutoRouteConstants {
    public static double kRobotLength = 67.0; //TODO: check later
  }

  public static final class ShooterConstants {
    public static final int kTopMotorPort = 3;
    public static final boolean kTopReversedDefault = false;
    public static final int kStallLimit = 45;
    public static final int kCurrentLimit = 60;
    public static final double kLauncherP = 0.001;
    public static final double kLauncherI = 0.0;
    public static final double kLauncherD = 0.0;
  }

  public static final class IndexerConstants {
    public static final int kIndexMotorPort = 6;
    public static final int kBeamBreakPort = 0;
    public static final int kDriveAmperagePeakDuration = 100;
    public static final int kCanTimeoutSetup = 500;
    public static final int kDriveAmperageLimitPeak = 50;
    public static final int kDriveAmperageLimitContinuous = 35;
    public static final double kIndexerSpeed = 0.5;
    public static final int kStallLimit = 45;
    public static final int kCurrentLimit = 60;
  }

	public static final class IntakeConstants {
    public static final int kIntakeMotorPort = 8;
    public static final int kIntakePiston1 = 4;
    public static final int kIntakePiston2 = 6;
    public static final double kIntakeMotorSpeed = 0.70;
    public static final double kEjectMotorSpeed = -0.70;
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
    public static final double kControllerDeadzone = 0.05;

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
    public static final double kTurnAngleP = 0.01;
    public static final double kTurnAngleTolerace = 8.0;
    public static final double kTurnSpeedTolerance = 5.0;
    public static final double kAutoForwardI = 0.0001;
    public static final double kAutoForwardP = 0.009;
    public static final double kAutoForwardD = 0.00;
    public static final double kVelocityTolerance = 0.2;
    public static final double kPositionTolerace = 5.0;
  }
   
  public static class LightConstants {
    public static final double kDisabled = 0.43; // Breathing color 1 + 2
    public static final double kDefaultColor = 0.93; //TODO: Find what we want default to be (same as disabled?)
    public static final double kLightsOff = 0.99; // Black
    public static final double kRedStopped = -0.17; // Breath red -- red alliance robot stopped
    public static final double kBlueStopped = -.15; // Breaht blue -- blue alliance robot stopped
    public static final double kRed = 0.61; // Solid red
    public static final double kBlue = 0.87; // Solid blue
    public static final double kBad = 0.61; // Used for a bad orientation - color red
    public static final double kParty = -0.99; // Rainbow party
    public static final double kAligning = -0.07; // Strobe gold when aligning
    public static final double kRevving = 0.67; // Solid gold when revving
    public static final double kShooting = 0.77; // Green for when shooting
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
