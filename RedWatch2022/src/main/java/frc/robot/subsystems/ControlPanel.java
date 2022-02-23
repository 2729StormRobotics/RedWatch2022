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
import frc.robot.commands.hangerRunMotors;
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
   * Contains all shuffleboard related code. Close out of the shuffleboard window and reopen to 
   * reset all shuffleboard tabs and layouts.
   * @param m_drivetrain Drivetrain subsystem
   * @param m_climber Hanger subsystem
   */
  public ControlPanel(Drivetrain m_drivetrain, Climber m_climber) {
    // Create Control Panel tab in Shuffleboard
    m_controlpanelTab = Shuffleboard.getTab(Constants.kShuffleboardTab);

    // Creates layouts for each subsystem
    m_drivetrainStatus = m_controlpanelTab.getLayout("Drivetrain Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(0, 0)
      .withSize(2, 4);
    m_climbStatus = m_controlpanelTab.getLayout("Climb Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(2, 0)
      .withSize(2, 4);
    m_extendstatus = m_controlpanelTab.getLayout("Extend Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(4, 0)
      .withSize(2, 3);
    m_pivotstatus = m_controlpanelTab.getLayout("Pivot Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(6, 0)
      .withSize(2, 3);

    // Creates the values that will be contained in each layout

    // Drivetrain
    m_drivetrainStatus.addNumber("Left Speed", () -> m_drivetrain.getLeftSpeed()); // How fast the robot is
    m_drivetrainStatus.addNumber("Right Speed", () -> m_drivetrain.getRightSpeed());
    m_drivetrainStatus.addNumber("Left Position", () -> m_drivetrain.getLeftDistance()); // How far the robot is
    m_drivetrainStatus.addNumber("Right Position", () -> m_drivetrain.getRightDistance());
    m_drivetrainStatus.addNumber("Angle", () -> m_drivetrain.getGyroAngle()); // What direction the robot is facing
    m_drivetrainStatus.addNumber("Pitch", () -> m_drivetrain.getGyroPitch()); // How high the robot is [To be tested]
    
    // Hanger
    m_climbStatus.addNumber("Left Distance", () -> m_climber.getLeftDistance()); // How far the hangers are
    m_climbStatus.addNumber("Right Distance", () -> m_climber.getRightDistance());
    m_climbStatus.add(new extendUp(m_climber)); // Moves the hanger up
    m_climbStatus.add(new extendDown(m_climber)); // Moves the hanger down

    m_climbStatus.addNumber("Gyro Angle", () -> m_climber.getGyroAngle()); // Shows what angle the robot is tilted [To be tested]
    m_climbStatus.add(new rotateForward(m_climber)); // Rotates the bot forward
    m_climbStatus.add(new rotateBackward(m_climber)); // Rotates the bot backwards

    // m_climbStatus.addNumber("right extend get", () -> m_climber.m_climbRightExtend.get());
    // m_climbStatus.addNumber("right pivot get", () -> m_climber.m_climbRightPivot.get());

    // m_climbStatus.addNumber("left extend get", () -> m_climber.m_climbLeftExtend.get());
    // m_climbStatus.addNumber("left pivot get", () -> m_climber.m_climbLeftPivot.get());

    // Manual control of the hangers
    LeftExtendMotor = m_extendstatus.add("Left Extend Speed", 0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1))
    .getEntry();
    RightExtendMotor = m_extendstatus.add("Right Extend Speed", 0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1))
    .getEntry();
    m_extendstatus.add("Run Extender", new hangerRunMotors(() -> LeftExtendMotor.getDouble(0), () -> RightExtendMotor.getDouble(0), m_climber.m_climbLeftExtend, m_climber.m_climbRightExtend, m_climber));

    // Manual control of the pivots
    LeftPivotMotor = m_pivotstatus.add("Left Pivot Speed", 0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1))
    .getEntry();
    RightPivotMotor = m_pivotstatus.add("Right Pivot Speed", 0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1))
    .getEntry();
    m_pivotstatus.add("Run Pivot", new hangerRunMotors(() -> LeftPivotMotor.getDouble(0), () -> RightPivotMotor.getDouble(0), m_climber.m_climbLeftPivot, m_climber.m_climbRightPivot, m_climber));

    // Automatically sets or changes Shuffleboard's current tab to Control Panel
    Shuffleboard.selectTab(Constants.kShuffleboardTab);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
