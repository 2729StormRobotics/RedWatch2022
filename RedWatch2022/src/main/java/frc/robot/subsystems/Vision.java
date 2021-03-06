// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import static frc.robot.Constants.VisionConstants.*;

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

private boolean m_aligning = false;

  /** Creates a new Vision. */
  public Vision() {
    // Gets the network table for the limelight
    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(2);

    // Resets the default settings and pipelines to the limelight
    setPipeline(kDefaultPipeline);

    // Initialize the network table entries for distance and target detection to default values
    m_targetDistance = m_limelightTable.getEntry("Target Distance");
    m_targetDetection = m_limelightTable.getEntry("Target Detection");
    m_targetOffset = m_limelightTable.getEntry("Target Offset");

    m_tx = m_limelightTable.getEntry("tx");
    m_ty = m_limelightTable.getEntry("ty");
    m_tv = m_limelightTable.getEntry("tv");
    
    // Start the camera server for the driver camera
    CameraServer.startAutomaticCapture("Driver Camera", 0);
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
   * Returns a value of the offset on the x-axis of the camera to the target in degrees
   * Negative values mean the target is to the left of the camera
   */
  public double getXOffset() {
    return m_xOffset;
  }

  /**
   * Returns a value of the offset on the y-axis of the camera to the target in degrees
   * Negative values mean the target is below the camera
   */
  public double getYOffset() {
    return m_yOffset;
  }

  /**
   * Returns true if a target is detected
   */
  public boolean isTargetDetected() {
    return (m_targetValue > 0.0);
  }

  /**
   * Returns true if the target is within a range of the center crosshair of the camera
   */
  public boolean isTargetCentered() {
    return (isTargetDetected() && (m_xOffset > -1) && (m_xOffset < 1));
  }

  /**
   * Calculates the total angle by adding the mounting angle with the y-axis
   * offset angle of the limelight in degrees
   */
  public double getTargetAngle() {
    return (kLimelightAngle + m_yOffset);
  }

  /**
   * Return the distance from the limelight to the target in inches (floor distance)
   */
  public double getTargetDistance() {
    //return (kHubHeight - kLimelightHeight) / Math.tan(Math.toRadians(getTargetAngle())) - kLimelightToShooter;
    return ((kHubHeight - kLimelightHeight) / Math.tan(Math.toRadians(getTargetAngle())));
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
    m_limelightTable.getEntry("stream").setNumber(1); // picture in picture for driver cam
  }

  /**
   * Returns the value of the pipeline from the network table
   * 
   * @return pipelineValue
   */
  public double getPipeline() {
    return m_limelightTable.getEntry("pipeline").getDouble(0.0);
  }
  
  public void updateLimelight() {
    // Updates the values of the limelight from the network table
    m_xOffset = m_tx.getDouble(0.0);
    m_yOffset = m_ty.getDouble(0.0);
    m_targetValue = m_tv.getDouble(0.0);

    // Updates the values of the math for target distance and value to the network table
    m_targetDistance.setDouble(getTargetDistance());
    m_targetDetection.setBoolean(isTargetDetected());
    m_targetOffset.setDouble(getXOffset());
  }

  public void setAligning() {
    m_aligning = true;
  }

  public void setDoneAligning() {
    m_aligning = false;
  }

  public boolean isAligning() {
    return m_aligning;
  }

  // TODO: Test conversion factor
  // gets needed rpm for shooter
  public double getRPM() {
    double distance = getTargetDistance();
    double angle = Math.toRadians(kShooterAngle);
    double velocity = Math.sqrt((16.087 * Math.pow(distance, 2)) /
      ( Math.pow(Math.cos(angle), 2) * 
      (-8.67 + (distance * Math.tan(angle)))) );
    //TODO: Check constant 0.8 with testing
    double rpm = 0.71 * (velocity / kFlywheelRadius) * kRadsToRPM;

    if (rpm > 2750.0) {
      return 2750.0;
    }
    else if (rpm < 1000.0) {
      return 1000.0;
    }
    else {
      return rpm;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateLimelight();
  }
}