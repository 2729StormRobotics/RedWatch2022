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
import frc.robot.commands.togglePistons;

public class ControlPanel extends SubsystemBase {
  /** Creates a new ControlPanel. */
  private final ShuffleboardTab m_controlpanelTab;
  private final ShuffleboardLayout m_drivetrainStatus;
  private final ShuffleboardLayout m_indexerStatus;
  private final ShuffleboardLayout m_intakeStatus;

  /** Creates a control panel in Shuffleboard that displays all important information and controls.
   * Contains all shuffleboard related code.
   * @param m_drivetrain Drivetrain subsystem
   * @param m_iIndexer Indexer subsystem
   */
  public ControlPanel(Drivetrain m_drivetrain, Indexer m_Indexer, Intake m_intake) {
    // Create Control Panel tab in Shuffleboard
    m_controlpanelTab = Shuffleboard.getTab(Constants.kShuffleboardTab);

    // Creates layouts for each subsystem
    m_drivetrainStatus = m_controlpanelTab.getLayout("Drivetrain Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(0, 0)
      .withSize(2, 4);
    m_intakeStatus = m_controlpanelTab.getLayout("Intake Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(2, 0)
      .withSize(2, 4);
    m_indexerStatus = m_controlpanelTab.getLayout("Indexer Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(4, 0)
      .withSize(2, 1);

    // Creates the values that will be contained in each layout

    m_drivetrainStatus.addNumber("Left Speed", () -> m_drivetrain.getLeftSpeed());
    m_drivetrainStatus.addNumber("Right Speed", () -> m_drivetrain.getRightSpeed());
    m_drivetrainStatus.addNumber("Left Position", () -> m_drivetrain.getLeftDistance());
    m_drivetrainStatus.addNumber("Right Position", () -> m_drivetrain.getRightDistance());
    m_drivetrainStatus.addNumber("Angle", () -> m_drivetrain.getGyroAngle());
    m_drivetrainStatus.addNumber("Pitch", () -> m_drivetrain.getGyroPitch());

    m_intakeStatus.addBoolean("Red", () -> m_intake.m_detectedColor.red > m_intake.m_detectedColor.blue && m_intake.m_detectedColor.red >= 0.3);
    m_intakeStatus.addBoolean("Blue", () -> m_intake.m_detectedColor.blue > m_intake.m_detectedColor.red && m_intake.m_detectedColor.blue >= 0.3);

    // Shows color values (RGB)
    m_intakeStatus.addNumber("R", () -> m_intake.m_detectedColor.red);
    m_intakeStatus.addNumber("G", () -> m_intake.m_detectedColor.green);
    m_intakeStatus.addNumber("B", () -> m_intake.m_detectedColor.blue);

    // Proximity to ball
    m_intakeStatus.addNumber("Ball Proximity", () -> m_intake.proximity);

    m_intakeStatus.addBoolean("Piston lowered?", () -> m_intake.isIntakeLowered());

    m_indexerStatus.addBoolean("Ball present?", () -> m_Indexer.isBallPresent());

    m_controlpanelTab.add("Toggle Pistons", new togglePistons(m_intake))
      .withPosition(4, 1)
      .withSize(2, 1);
    
    // Automatically sets or changes Shuffleboard's current tab to Control Panel
    Shuffleboard.selectTab(Constants.kShuffleboardTab);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
