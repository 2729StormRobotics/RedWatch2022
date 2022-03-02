// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;
import java.util.function.BooleanSupplier;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static frc.robot.Constants.LightConstants.*;
import frc.robot.commands.togglePistons;
import frc.robot.commands.changeAlliance;
import frc.robot.commands.extendDown;
import frc.robot.commands.extendUp;
import frc.robot.commands.hangerRunMotors;
import frc.robot.commands.rotateBackward;
import frc.robot.commands.rotateForward;
import frc.robot.commands.setLights;

public class ControlPanel extends SubsystemBase {
  /** Creates a new ControlPanel. */
  private final ShuffleboardTab m_controlpanelTab;
  private final ShuffleboardLayout m_intakeStatus;
  private final ShuffleboardLayout m_drivetrainStatus;
  private final ShuffleboardLayout m_climbStatus;
  private final ShuffleboardLayout m_extendstatus;
  private final ShuffleboardLayout m_pivotstatus;
  private final ShuffleboardLayout m_lightstatus;
  private final ShuffleboardLayout m_shooterstatus;
  private final ShuffleboardLayout m_indexerstatus;

  private final NetworkTableEntry LeftExtendMotor;
  private final NetworkTableEntry RightExtendMotor;
  private final NetworkTableEntry LeftPivotMotor;
  private final NetworkTableEntry RightPivotMotor;
  private final NetworkTableEntry setLightColor;

  private final SimpleWidget ballColorWidget;
  private final BooleanSupplier isBallRed;
  private final BooleanSupplier isBallBlue;

  private final SimpleWidget m_allianceStatus;
  private final BooleanSupplier isRedTeam;
  private final BooleanSupplier isBlueTeam;

  /** Creates a control panel in Shuffleboard that displays all important information and controls.
   * Contains all shuffleboard related code. Close out of the shuffleboard window and reopen to 
   * reset all shuffleboard tabs and layouts.
   * @param m_drivetrain Drivetrain subsystem
   * @param m_climber Hanger subsystem
   */
  public ControlPanel(XboxController m_driver, XboxController m_weapons, Drivetrain m_drivetrain, Climber m_climber, Intake m_intake, Indexer m_indexer, Shooter m_shooter, Lights m_lights) {
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
    m_climbStatus = m_controlpanelTab.getLayout("Climb Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(4, 0)
      .withSize(2, 4);
    m_extendstatus = m_controlpanelTab.getLayout("Extend Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(6, 0)
      .withSize(2, 3);
    m_pivotstatus = m_controlpanelTab.getLayout("Pivot Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(8, 0)
      .withSize(2, 3);
    m_lightstatus = m_controlpanelTab.getLayout("Light Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(8, 3)
      .withSize(2, 1);
    m_shooterstatus = m_controlpanelTab.getLayout("Shooter Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(0, 4)
      .withSize(2, 1);
    m_indexerstatus = m_controlpanelTab.getLayout("Indexer Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"))
      .withPosition(2, 4)
      .withSize(2, 1);

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
    m_climbStatus.addNumber("Left Pivot", () -> m_climber.getLeftPivot());
    m_climbStatus.addNumber("Right Pivot", () -> m_climber.getRightPivot());

    m_climbStatus.add(new extendUp(m_climber)); // Moves the hanger up
    m_climbStatus.add(new extendDown(m_climber)); // Moves the hanger down

    m_climbStatus.addNumber("Gyro Angle", () -> m_climber.getGyroAngle()); // Shows what angle the robot is tilted [To be tested]
    m_climbStatus.add(new rotateForward(m_climber)); // Rotates the bot forward
    m_climbStatus.add(new rotateBackward(m_climber)); // Rotates the bot backwards

    // Indexer
    m_indexerstatus.addString("Bottom Ball", () -> m_indexer.getBottomBall() + "");
    m_indexerstatus.addString("Middle Ball", () -> m_indexer.getMiddleBall() + "");

    // // DEBUGGING values
    // m_climbStatus.addNumber("right extend get", () -> m_climber.m_climbRightExtend.get());
    // m_climbStatus.addNumber("right pivot get", () -> m_climber.m_climbRightPivot.get());

    // m_climbStatus.addNumber("left extend get", () -> m_climber.m_climbLeftExtend.get());
    // m_climbStatus.addNumber("left pivot get", () -> m_climber.m_climbLeftPivot.get());

    // m_climbStatus.addBoolean("left bumper", () -> m_weapons.getLeftBumper());
    // m_climbStatus.addBoolean("right bumper", () -> m_weapons.getRightBumper());



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

    ballColorWidget = m_intakeStatus.add("Ball Color", true);
    ballColorWidget.withProperties(Map.of("Color When True", "Black"));
    isBallRed = () -> m_intake.isRedBall();
    isBallBlue = () -> m_intake.isBlueBall();

    // Shows color values (RGB)
    m_intakeStatus.addNumber("R", () -> m_intake.m_detectedColor.red);
    m_intakeStatus.addNumber("G", () -> m_intake.m_detectedColor.green);
    m_intakeStatus.addNumber("B", () -> m_intake.m_detectedColor.blue);

    // Proximity to ball
    m_intakeStatus.addNumber("Ball Proximity", () -> m_intake.m_proximity);

    m_intakeStatus.addBoolean("Piston lowered?", () -> m_intake.isIntakeLowered());

    m_controlpanelTab.add("Toggle Pistons", new togglePistons(m_intake))
      .withPosition(6, 3)
      .withSize(2, 1);
    
    m_lightstatus.addNumber("Light Output", () -> m_lights.getCurrentLights());
    setLightColor = m_lightstatus.add("Light color code", kDefaultColor).getEntry();
    m_lightstatus.add("Change light color", new setLights(m_lights, () -> setLightColor.getDouble(kDefaultColor)));

    m_shooterstatus.addNumber("Flywheel Speed", () -> m_shooter.getEncoderVelocity(m_shooter.m_topEncoder));

    m_indexerstatus.addNumber("Indexer Set Speed", () -> m_indexer.m_bottomMotor.getMotorOutputPercent());
    
    m_allianceStatus = m_controlpanelTab.add("Alliance Status", true)
      .withProperties(Map.of("Color When True", "Red"))
      .withPosition(6, 4)
      .withSize(2, 1);
    m_controlpanelTab.add("Change Alliance", new changeAlliance(m_shooter))
      .withPosition(8, 4)
      .withSize(2, 1);
    isRedTeam = () -> m_shooter.isRedTeam();
    isBlueTeam = () -> m_shooter.isBlueTeam();

    // Automatically sets or changes Shuffleboard's current tab to Control Panel
    Shuffleboard.selectTab(Constants.kShuffleboardTab);
    
  }

@Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (isBallRed.getAsBoolean()) {
      ballColorWidget.withProperties(Map.of("Color When True", "Red"));
    } else if (isBallBlue.getAsBoolean()) {
      ballColorWidget.withProperties(Map.of("Color When True", "Blue"));
    } else {
      ballColorWidget.withProperties(Map.of("Color When True", "Black"));
    }

    if (isRedTeam.getAsBoolean()) {
      m_allianceStatus.withProperties(Map.of("Color When True", "Red"));
    } else if (isBlueTeam.getAsBoolean()) {
      m_allianceStatus.withProperties(Map.of("Color When True", "Blue"));
    }
  }
}
