// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;

import static frc.robot.Constants.IntakeConstants.*;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

public class Intake extends SubsystemBase {

  private final TalonSRX m_intakeMotor;
  private final DoubleSolenoid m_intakePiston1;
  private final DoubleSolenoid m_intakePiston2;

  private final ColorSensorV3 m_colorSensor;

  public Color m_detectedColor;
  public int proximity;

  private final NetworkTable m_intakeTable;
  private final NetworkTableEntry m_intakeStatus;

  /**
   * Controls the intake mechanism.
   * Talon motors are required.
   */
  public Intake() {
    // Creates motor and piston variables
    m_intakeMotor = new TalonSRX(kIntakeMotorPort);
    m_intakePiston1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, kIntakePiston1, kIntakePiston1 + 1); //The type of hopper (Replace CTREPCM With correct)
    m_intakePiston2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, kIntakePiston2, kIntakePiston2 + 1);

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
    m_intakePiston1.set(kIntakeRaiseValue);
    m_intakePiston2.set(kIntakeRaiseValue);
  }

  // Lowers intake to lowered position to pick up balls
  public void lowerIntake() {
    m_intakePiston1.set(kIntakeLowerValue);
    m_intakePiston2.set(kIntakeLowerValue);
  }

  // Toggles between lowering and raising intake
  public void toggleIntakePistons() {
    if (isIntakeLowered()) {
      raiseIntake();
    } else {
      lowerIntake();
    }
  }

  // Checks if intake is lowered or raised
  public boolean isIntakeLowered() {
    if (m_intakePiston1.get() == kIntakeLowerValue) {
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
