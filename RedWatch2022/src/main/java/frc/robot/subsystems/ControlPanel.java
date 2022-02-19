// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.extendDown;
import frc.robot.commands.extendUp;
import frc.robot.commands.hangerDrive;
import frc.robot.commands.rotateBackward;
import frc.robot.commands.rotateForward;

public class ControlPanel extends SubsystemBase {
  /** Creates a new ControlPanel. */
  private final ShuffleboardTab m_controlpanelTab;
  private final ShuffleboardLayout m_drivetrainStatus;
  private final ShuffleboardLayout m_climbStatus;
  private final ShuffleboardLayout m_extendstatus;
  private final ShuffleboardLayout m_pivotstatus;

  private final NetworkTableEntry LeftExtendMotor;
  private final NetworkTableEntry RightExtendMotor;
  private final NetworkTableEntry LeftPivotMotor;
  private final NetworkTableEntry RightPivotMotor;

  /** Creates a control panel in Shuffleboard that displays all important information and controls.
   * Contains all shuffleboard related code.
   * @param m_drivetrain Drivetrain subsystem
   * @param m_iIndexer Indexer subsystem
   */
  public ControlPanel(Drivetrain m_drivetrain, Climber m_climber) {
    // Create Control Panel tab in Shuffleboard
    m_controlpanelTab = Shuffleboard.getTab(Constants.kShuffleboardTab);
    // Creates layouts for each subsystem
    m_drivetrainStatus = m_controlpanelTab.getLayout("Drivetrain Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));
    m_climbStatus = m_controlpanelTab.getLayout("Climb Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));
    m_extendstatus = m_controlpanelTab.getLayout("Extend Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));
    m_pivotstatus = m_controlpanelTab.getLayout("Pivot Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));

    // Creates the values that will be contained in each layout
    m_drivetrainStatus.addNumber("Left Speed", () -> m_drivetrain.getLeftSpeed());
    m_drivetrainStatus.addNumber("Right Speed", () -> m_drivetrain.getRightSpeed());
    m_drivetrainStatus.addNumber("Left Position", () -> m_drivetrain.getLeftDistance());
    m_drivetrainStatus.addNumber("Right Position", () -> m_drivetrain.getRightDistance());
    m_drivetrainStatus.addNumber("Angle", () -> m_drivetrain.getGyroAngle());
    m_drivetrainStatus.addNumber("Pitch", () -> m_drivetrain.getGyroPitch());

    // m_indexerStatus.addBoolean("Is ball present?", () -> m_Indexer.isBallPresent());
    
    m_climbStatus.addNumber("Left Distance", () -> m_climber.getLeftDistance());
    m_climbStatus.addNumber("Right Distance", () -> m_climber.getRightDistance());
    m_climbStatus.add(new extendUp(m_climber));
    m_climbStatus.add(new extendDown(m_climber));

    m_climbStatus.addNumber("Gyro Angle", () -> m_climber.getGyroAngle());
    m_climbStatus.add(new rotateForward(m_climber));
    m_climbStatus.add(new rotateBackward(m_climber));

    LeftExtendMotor = m_extendstatus.add("Left Extend Speed", 0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1))
    .getEntry();
    RightExtendMotor = m_extendstatus.add("Right Extend Speed", 0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1))
    .getEntry();
    m_extendstatus.add("Run Extender", new hangerDrive(() -> LeftExtendMotor.getDouble(0), () -> RightExtendMotor.getDouble(0), m_climber.m_climbLeftExtend, m_climber.m_climbRightExtend, m_climber));

    LeftPivotMotor = m_pivotstatus.add("Left Pivot Speed", 0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1))
    .getEntry();
    RightPivotMotor = m_pivotstatus.add("Right Pivot Speed", 0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1))
    .getEntry();
    m_pivotstatus.add("Run Pivot", new hangerDrive(() -> LeftPivotMotor.getDouble(0), () -> RightPivotMotor.getDouble(0), m_climber.m_climbLeftPivot, m_climber.m_climbRightPivot, m_climber));

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
