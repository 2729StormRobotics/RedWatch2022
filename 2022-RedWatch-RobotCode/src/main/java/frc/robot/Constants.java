// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.util.Color;

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

    public static final class IntakeConstants {
        
        public static final int kIntakeMotorPort = 1;
        public static final int kIntakeRaiseChannel = 2;
        public static final int kIntakeLowerChannel = 3;
        public static final double kIntakeMotorSpeed = 10;
        public static final double kEjectMotorSpeed = -10;
        public static final Value kIntakeRaiseValue = Value.kForward;
        public static final Value kIntakeLowerValue = Value.kReverse;

    }

    public static final class HopperConstants{ 
        public static final int kHopperMotorPort = 5;
        public static final double kHopperMotorSpeed = 0.25; 

        public static final String kShuffleboardTab = "Testing";
    }

    public static final class ColorConstants {
        public static final int colorPort = 2;
        public static final Color kNoColor = ColorMatch.makeColor(0.0, 0.0, 0.0);
        public static final Color kRedTarget = ColorMatch.makeColor(0.476, 0.376, 0.15);
        public static final Color kBlueTarget = ColorMatch.makeColor(0.18, 0.568, 0.249);
        public static final String kShuffleboardTab = "Testing";
    }

}
