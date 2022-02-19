// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanel extends SubsystemBase {
  /** Creates a new ControlPanel. */
  private final ShuffleboardTab m_controlpanelTab;
  private final ShuffleboardLayout m_drivetrainStatus;
  private final ShuffleboardLayout m_indexerStatus;

  public ControlPanel(Indexer m_iIndexer) {
    m_controlpanelTab = Shuffleboard.getTab(Constants.kShuffleboardTab);
    m_drivetrainStatus = m_controlpanelTab.getLayout("Drivetrain Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));
    m_indexerStatus = m_controlpanelTab.getLayout("Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));

    // m_drivetrainStatus.addNumber("Left Speed", () -> m_drivetrain.getLeftSpeed());
    // m_drivetrainStatus.addNumber("Right Speed", () -> m_drivetrain.getRightSpeed());
    // m_drivetrainStatus.addNumber("Left Position", () -> m_drivetrain.getLeftDistance());
    // m_drivetrainStatus.addNumber("Right Position", () -> m_drivetrain.getRightDistance());
    // m_drivetrainStatus.addNumber("Angle", () -> m_drivetrain.getGyroAngle());
    // m_drivetrainStatus.addNumber("Altitude", () -> m_drivetrain.getGyroPitch());

    m_indexerStatus.addBoolean("Is ball present?", () -> m_iIndexer.isBallPresent());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
