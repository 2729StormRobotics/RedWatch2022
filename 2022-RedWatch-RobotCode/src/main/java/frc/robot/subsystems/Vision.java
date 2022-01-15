// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
    /* Create variables for the different values given from the limelight */

    // Offset along x axis in degrees.
    private double m_xOffset; // Positive values mean that target is to the right of the camera

    // Offset along y axis in degrees
    private double m_yOffset; // Positive values mean that target is above the camera

    // Percentage of the image the target takes
    private double m_targetArea;

    // Is target detected
    private double m_targetValue; // 1 if target is detected, 0 if not

    // Create a network table for the limelight
    private final NetworkTable m_limelightTable;


  /** Creates a new Vision. */
  public Vision() {


    
  }
  
  public void updateLimelight() {
    // Updates the values of the limelight on the network table
    m_xOffset = m_limelightTable.getEntry("tx").getDouble(0.0);
    m_yOffset = m_limelightTable.getEntry("ty").getDouble(0.0);
    m_targetArea = m_limelightTable.getEntry("ta").getDouble(0.0);
    m_targetValue = m_limelightTable.getEntry("tv").getDouble(0.0);

  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
