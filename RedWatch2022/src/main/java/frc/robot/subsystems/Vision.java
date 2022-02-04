// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
/* Create variables for the different values given from the limelight */

// Offset along x axis in degrees
private double m_xOffset; // Positive values mean that the target is to the right of the camera

// Offset along y axis in degrees
private double m_yOffset; // Positive values mean that the target is above the camera

// Is a target in the frame of the camera
private double m_targetValue; // 1 if target is detected, 0 if not

// Create a network table for the limelight
private final NetworkTable m_limelightTable;

// Create new network table entries for target detection
private final NetworkTableEntry m_targetDistance;
private final NetworkTableEntry m_targetDetection;
private final NetworkTableEntry m_targetOffset;

private final NetworkTableEntry m_tx;
private final NetworkTableEntry m_ty;
private final NetworkTableEntry m_tv;

  /** Creates a new Vision. */
  public Vision() {
    // Gets the network table for the limelight
    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

    // Resets the default settings and pipelines to the limelight
    setPipeline(kDefaultPipeline);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
