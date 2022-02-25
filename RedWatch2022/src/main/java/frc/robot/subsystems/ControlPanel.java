// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.RevToSpeed;

public class ControlPanel extends SubsystemBase {
  /** Creates a new ControlPanel. */
  private final ShuffleboardTab m_controlpanelTab;
  private final ShuffleboardLayout m_indexerStatus;
  private final ShuffleboardLayout m_shooterStatus;
  private final ShuffleboardLayout m_lightStatus;

  private final NetworkTableEntry RevRPM;

  public ControlPanel(Shooter m_shooter, Indexer m_iIndexer, Lights m_lights) {
    m_controlpanelTab = Shuffleboard.getTab(Constants.kShuffleboardTab);
    m_indexerStatus = m_controlpanelTab.getLayout("Indexer Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(1, 1)
      .withSize(1, 1);
    m_shooterStatus = m_controlpanelTab.getLayout("Shooter Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(0, 0)
      .withSize(1, 2);
    m_lightStatus = m_controlpanelTab.getLayout("Light Jawndess", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(1, 0)
      .withSize(1, 1);

    m_indexerStatus.addBoolean("Is ball present?", () -> m_iIndexer.isBallPresent());

    m_lightStatus.addNumber("Light Output", () -> m_lights.getCurrentLights());

    m_shooterStatus.addNumber("Encoder Velocity", () -> m_shooter.getEncoderVelocity(m_shooter.m_topEncoder));
    m_shooterStatus.addNumber("Increment", () -> m_shooter.increment);
    m_shooterStatus.addNumber("Motor Power", () -> m_shooter.motorPower);

    RevRPM = m_controlpanelTab.add("Rev RPM", 2200)
      .withPosition(2, 0)
      .withSize(2, 1)
      .getEntry();
    m_controlpanelTab.add("Run Rev RPM", new RevToSpeed(RevRPM.getDouble(0), m_shooter, m_lights))
      .withPosition(2, 1)
      .withSize(2, 1);
    
    // Automatically sets or changes Shuffleboard's current tab to Control Panel
    Shuffleboard.selectTab(Constants.kShuffleboardTab);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
