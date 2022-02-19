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
import frc.robot.commands.extendDown;
import frc.robot.commands.extendUp;

public class ControlPanel extends SubsystemBase {
  /** Creates a new ControlPanel. */
  private final ShuffleboardTab m_controlpanelTab;
  private final ShuffleboardLayout m_drivetrainStatus;
  private final ShuffleboardLayout m_indexerStatus;
  private final ShuffleboardLayout m_climbStatus;

  /** Creates a control panel in Shuffleboard that displays all important information and controls.
   * Contains all shuffleboard related code.
   * @param m_drivetrain Drivetrain subsystem
   * @param m_iIndexer Indexer subsystem
   */
  public ControlPanel(Drivetrain m_drivetrain, Indexer m_Indexer, Climber m_climber) {
    // Create Control Panel tab in Shuffleboard
    m_controlpanelTab = Shuffleboard.getTab(Constants.kShuffleboardTab);
    // Creates layouts for each subsystem
    m_drivetrainStatus = m_controlpanelTab.getLayout("Drivetrain Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));
    m_indexerStatus = m_controlpanelTab.getLayout("Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));
    m_climbStatus = m_controlpanelTab.getLayout("Climb Status", BuiltInLayouts.kList);
    // Creates the values that will be contained in each layout
    m_drivetrainStatus.addNumber("Left Speed", () -> m_drivetrain.getLeftSpeed());
    m_drivetrainStatus.addNumber("Right Speed", () -> m_drivetrain.getRightSpeed());
    m_drivetrainStatus.addNumber("Left Position", () -> m_drivetrain.getLeftDistance());
    m_drivetrainStatus.addNumber("Right Position", () -> m_drivetrain.getRightDistance());
    m_drivetrainStatus.addNumber("Angle", () -> m_drivetrain.getGyroAngle());
    m_drivetrainStatus.addNumber("Pitch", () -> m_drivetrain.getGyroPitch());

    m_indexerStatus.addBoolean("Is ball present?", () -> m_Indexer.isBallPresent());
    
    m_climbStatus.addNumber("Left Distance", () -> m_climber.getLeftDistance());
    m_climbStatus.addNumber("Right Distance", () -> m_climber.getRightDistance());
    m_climbStatus.add(new extendUp(m_climber));
    m_climbStatus.add(new extendDown(m_climber));

    m_climbStatus.addNumber("Gyro Angle", () -> m_climber.getGyroAngle());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
