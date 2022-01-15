// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.VisionConstants.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
    /* Create variables for the different values given from the limelight */

    // Offset along x axis in degrees.
    private double m_xOffset; // Positive values mean that target is to the right of the camera

    // Offset along y axis in degrees
    private double m_yOffset; // Positive values mean that target is above the camera

    // Is target detected
    private double m_targetValue; // 1 if target is detected, 0 if not

    // Create a network table for the limelight
    private final NetworkTable m_limelightTable;

    // Create new network table entries for target detection
    private final NetworkTableEntry m_targetDistance;
    private final NetworkTableEntry m_targetDetection; 
    private final NetworkTableEntry m_targetOffset;


  /** Creates a new Vision. */
  public Vision() {
    // Gets the network table for the limelight
    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

    // Reset the default settings and pipelines to the Limelight
    setPipeline(kDefaultPipeline);

    // Initialize the network table entries for distance and target detection to
    // default values
    m_targetDistance = m_limelightTable.getEntry("Target Distance");
    m_targetDetection = m_limelightTable.getEntry("Target Detection");
    m_targetOffset = m_limelightTable.getEntry("Target Offset");
  }

  // Sets the limelight to off
  public void disableLED() {
    m_limelightTable.getEntry("ledMode").setNumber(1);
  }

  // Sets the limelight to solid on
  public void enableLED() {
    m_limelightTable.getEntry("ledMode").setNumber(3);
  }

  /**
   * 
   * Returns a value of the offset on the x-axis of the camera to the target in
   * degrees. Negative values mean the target is to the left of the camera
   */
  public double getXOffset() {
    return m_xOffset;
  }

  /**
   * Returns true if a target is detected
   */
  public boolean isTargetDetected() {
    return (m_targetValue > 0.0);
  }

  // TODO: Test different values for offsets and update them
  /**
   * Returns true if the target is within a range of the center crosshair of the
   * camera
   */
  public boolean isTargetCentered() {
    return (isTargetDetected() && (m_xOffset > -1.5) && (m_xOffset < 1.5));
  }

  /**
   * Calculates the total angle by adding the mounting angle with the y-axis
   * offset angle of the limelight in degrees
   */
  public double getTargetAngle() {
    return (kLimelightAngle + m_yOffset);
  }

  // TODO: Check the math of this method depending on how we are computing distance and the 1' offset of the hub
  /**
   * Return the distance from the limelight to the target in inches (floor
   * distance)
   */
  public double getTargetDistance() {
    return (kHubHeight - kLimelightHeight) / Math.tan(Math.toRadians(getTargetAngle()));
  }

  /**
   * Chooses which pipeline to use on the limelight and prevents invalid values
   * from being sent
   * 
   * @param pipeline Which pipeline to use on the limelight (0-9)
   */
  public void setPipeline(int pipeline) {
    if (pipeline < 0) {
      pipeline = 0;
    } else if (pipeline > 9) {
      pipeline = 9;
    }

    m_limelightTable.getEntry("pipeline").setNumber(pipeline);
  }

  /**
   * Returns the value of the pipeline from the network table
   * 
   * @return pipelineValue
   */
  public double getPipeline() {
    double pipeline = m_limelightTable.getEntry("pipeline").getDouble(0.0);
    return pipeline;
  }
  
  public void updateLimelight() {
    // Updates the values of the limelight on the network table
    m_xOffset = m_limelightTable.getEntry("tx").getDouble(0.0);
    m_yOffset = m_limelightTable.getEntry("ty").getDouble(0.0);
    m_targetValue = m_limelightTable.getEntry("tv").getDouble(0.0);

    // Updates the values of the math for target distance and value to the network
    // table
    m_targetDistance.setDouble(getTargetDistance());
    m_targetDetection.setBoolean(isTargetDetected());
    m_targetOffset.setDouble(getXOffset());
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateLimelight();
  }
}
