// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;

import static frc.robot.Constants.IntakeConstants.*;
import static frc.robot.Constants.ColorConstants.*;
import static frc.robot.Constants.LightConstants.*;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

public class Intake extends SubsystemBase {

  private final TalonSRX m_intakeMotor;
  private final DoubleSolenoid m_intakePistons;

  private final ColorSensorV3 m_colorSensor;

  private Color m_detectedColor;
  private int proximity;

  private final ShuffleboardTab m_controlPanelTab;
  private final ShuffleboardLayout m_controlPanelStatus;

  private final NetworkTable m_intakeTable;
  private final NetworkTableEntry m_intakeStatus;
  private final ShuffleboardTab m_shuffleboardTab;
  private final ShuffleboardLayout m_lightValues;

  private final Spark m_ledDriver;
  private final Timer m_timeToSpeed = new Timer();

  /**
   * Controls the intake mechanism.
   * Talon motors are required
   */

  /** Creates a new Intake. */
  public Intake() {
    m_shuffleboardTab = Shuffleboard.getTab(Constants.kShuffleboardTab);
    m_lightValues = m_shuffleboardTab.getLayout("Color Values", BuiltInLayouts.kList);

    m_ledDriver = new Spark(0);
    resetLights();

    // Creates motor and piston variables
    m_intakeMotor = new TalonSRX(kIntakeMotorPort);
    m_intakePistons = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, kIntakeRaiseChannel, kIntakeLowerChannel);//The type of hopper (Replace CTREPCM With correct)

    // Resets to defaults and sets to idle mode and makes inverted
    m_intakeMotor.configPeakCurrentDuration(kDRIVE_AMPERAGE_PEAK_DURATION, kCAN_TIMEOUT_SETUP);
    m_intakeMotor.configPeakCurrentLimit(kDRIVE_AMPERAGE_LIMIT_PEAK, kCAN_TIMEOUT_SETUP);
    m_intakeMotor.configContinuousCurrentLimit(kDRIVE_AMPERAGE_LIMIT_CONTINUOUS, kCAN_TIMEOUT_SETUP);
    m_intakeMotor.enableCurrentLimit(true);
    m_intakeMotor.setInverted(true);

    // Initializes network table
    m_intakeTable = NetworkTableInstance.getDefault().getTable("Intake");
    m_intakeStatus = m_intakeTable.getEntry("Intake Running");

    m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    m_controlPanelTab = Shuffleboard.getTab(kShuffleboardTab);
    m_controlPanelStatus = m_controlPanelTab.getLayout("Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));

    shuffleboardInit();

  }

  // Initalizes shuffleboard
  private void shuffleboardInit() {
    // Displays color detected as a color box
    m_controlPanelStatus.addBoolean("Red", () -> m_detectedColor.red > m_detectedColor.blue && m_detectedColor.red >= 0.3);
    m_controlPanelStatus.addBoolean("Blue", () -> m_detectedColor.blue > m_detectedColor.red && m_detectedColor.blue >= 0.3);

    // Shows color values (RGB)
    m_controlPanelStatus.addNumber("R", () -> m_detectedColor.red);
    m_controlPanelStatus.addNumber("G", () -> m_detectedColor.green);
    m_controlPanelStatus.addNumber("B", () -> m_detectedColor.blue);

    // Proximity to ball
    m_controlPanelStatus.addNumber("Ball Proximity", () -> proximity);

  }

  // Sets disabled color 
  public void setDisabledColor() {
    m_ledDriver.set(kDisabled);
  }

  // Turns off LEDs
  public void setOff() {
    m_ledDriver.set(kLightsOff);
  }

  // Sets LEDs to red
  public void intakeRed() {
    m_ledDriver.set(kRedBall);
  }

  // Sets LEDs to blue
  public void intakeBlue() {
    m_ledDriver.set(kBlueBall);
  }

  // Sets LEDs to default color
  public void resetLights() {
    m_ledDriver.set(kDefaultColor);
  }

  
  public double getCurrentLights() {
    return m_ledDriver.get();
  }

  public void setGiven(double color) {
    m_ledDriver.set(color);
  }

  // Runs intake
  public void runIntake(double speed) {
    m_intakeStatus.setBoolean(true);
    m_intakeMotor.set(ControlMode.PercentOutput, speed);

  }

  // Rotates motors for intake at kIntakeMotorSpeed
  public void intake() {
    runIntake(kIntakeMotorSpeed);

  }

  // Rotates motors for intake at kEjectMotorSpeed
  public void eject() {
    runIntake(kEjectMotorSpeed);

  }

  // Stops intake
  public void stopIntake() {
    m_intakeMotor.set(ControlMode.PercentOutput, 0);
    m_intakeStatus.setBoolean(false);

  }

  // Raises intake back to raised position
  public void raiseIntake() {
    m_intakePistons.set(kIntakeRaiseValue);

  }

  // Lowers intake to lowered position to pick up balls
  public void lowerIntake() {
    m_intakePistons.set(kIntakeLowerValue);

  }

  // Toggles between lowering and raising intake
  public void toggleIntakePistons() {
    if (isIntakeLowered()) {
      raiseIntake();
    }

    else {
      lowerIntake();
    }
  }

  // Checks if intake is lowered or raised
  public boolean isIntakeLowered() {
    if (m_intakePistons.get() == kIntakeLowerValue) {
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Collects color data and proximity on repeat
    m_detectedColor = m_colorSensor.getColor();
    proximity = m_colorSensor.getProximity();
  }
}
