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

    public static int kRobotLength = 67; // check later
    public static final String kShuffleboardTab = "Control Panel";

    public static final class ShooterConstants {
        public static final int TOP_MOTOR_ID = 3;
        public static final boolean kTopReversedDefault = false;
        public static final int STALL_LIMIT = 45;
        public static final int kCurrentLimit = 60;
    }

    public static final double kHighShootSpeed = 0.5;
    public static final double kLowShootSpeed = 0.25;

    public static final class IndexerConstants {
        public static final int kIndexMotorPort = 6;
        public static final int kBeamBreakPort = 1;
        public static final String kShuffleboardTab = "Indexer";
        public static final int kDRIVE_AMPERAGE_PEAK_DURATION = 100;
        public static final int kCAN_TIMEOUT_SETUP = 500;
        public static final int kDRIVE_AMPERAGE_LIMIT_PEAK = 50;
        public static final int kDRIVE_AMPERAGE_LIMIT_CONTINUOUS = 35;
        public static final double kIndexerSpeed = .250;
    }

    public static final int kCurrentLimit = 60;
    public static final boolean kLeftReversedDefault = true;
    public static final boolean kRightReversedDefault = !kLeftReversedDefault;
    public static final int STALL_LIMIT = 45;

	public static final class IntakeConstants {
        public static final int kIntakeMotorPort = 8;
        public static final int kIntakePiston1 = 4;
        public static final int kIntakePiston2 = 6;
        public static final double kIntakeMotorSpeed = 0.35;
        public static final double kEjectMotorSpeed = -0.45;
        public static final Value kIntakeRaiseValue = Value.kForward;
        public static final Value kIntakeLowerValue = Value.kReverse;
		public static final int kDRIVE_AMPERAGE_PEAK_DURATION = 100;
        public static final int kCAN_TIMEOUT_SETUP = 500;
        public static final int kDRIVE_AMPERAGE_LIMIT_PEAK = 50;
        public static final int kDRIVE_AMPERAGE_LIMIT_CONTINUOUS = 35;
    }

    public static final class ColorConstants {
        public static final String kShuffleboardTab = "Color";
    }

    public static final class IOPorts {
        public static final int kDriverController = 1;
        public static final int kWeaponsController = 2;
    }

    public static class DrivetrainConstants {
        // based off PDP ports and the REV Spark Client. 0 is reassigned as 15.
        public static final int LEFT_MOTOR_ID = 12;
        public static final int RIGHT_MOTOR_ID = 15;
        public static final int LEFT_MOTOR2_ID = 14;
        public static final int RIGHT_MOTOR2_ID = 1;

        // since the encoder is build into the motor we need to account for gearing
        public static final double kWheelDiameter = 6.0;
        public static final double kGearRatio = 1.0 / 13.0;
        public static final double kDistancePerRevolution = kWheelDiameter * kGearRatio;
        public static final double kSpeedPerRevolution = kDistancePerRevolution / 60.0;

        public static final int kCurrentLimit = 60;
        public static final boolean kLeftReversedDefault = true;
        public static final boolean kRightReversedDefault = !kLeftReversedDefault;

        public static final int STALL_LIMIT = 45;
        public static final double TurnAngleD = 0;
        public static final double TurnAngleI = 0;
        public static final double TurnAngleP = 0;
        public static final double TurnAngleTolerace = 5;
        public static final double TurnSpeedTolerance = 5;
        public static final double AutoForwardI = 0;
        public static final double AutoForwardP = 0;
        public static final double AutoForwardD = 0;
        public static final double VelocityTolerance = 5;
        public static final double PositionTolerace = 5;
    }
   
    public static class LightConstants {
		public static final double kDisabled = 0.0; //TODO: Find what color we want for this and its value
		public static final double kLightsOff = 0.99;
        public static final double kRedBall = 0.61;
        public static final double kBlueBall = 0.87;
        public static final double kDefaultColor = 0.93; //TODO: Find what we want default to be (same as disabled?)
        public static final double kCorrect = 0.75; //Defines Dark Green
        public static final double kBad = 0.61; //Defines Red for bad orrientation
        public static final int kBlinkinDriverPort = 0;
    }
    
    public static final class ClimberConstants {
		public static final int kClimberLeftFollowerExtendPort = 13;
		public static final int kClimberRightExtendPort = 2;
		public static final int kClimberLeftPivotFollowerPort = 9;
		public static final int kClimberRightPivotPort = 10;
		public static final String kClimberTab = "Climber Status";
		
        public static final double kDistancePerRevolution = 0; //TODO: put in gear ratio for the climbers

		public static final double kClimberRightSize = 12;

	}
}
