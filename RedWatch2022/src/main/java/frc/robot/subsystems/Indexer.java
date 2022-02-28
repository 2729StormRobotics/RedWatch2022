// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.IndexerConstants.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Indexer extends SubsystemBase {

  // Talon used for index motor
  public final TalonSRX m_bottomMotor;
  private final DigitalInput m_ballDector;

  /** Creates a new Indexer. */
  public Indexer() {
    m_ballDector = new DigitalInput(kBeamBreakPort);
    m_bottomMotor = new TalonSRX(kIndexMotorPort);

    motorInit(m_bottomMotor);
  }

  private void motorInit(TalonSRX m_motor){
    m_motor.configPeakCurrentDuration(kDriveAmperagePeakDuration, kCanTimeoutSetup);
    m_motor.configPeakCurrentLimit(kDriveAmperageLimitPeak, kCanTimeoutSetup);
    m_motor.configContinuousCurrentLimit(kDriveAmperageLimitContinuous, kCanTimeoutSetup);
    m_motor.enableCurrentLimit(true);
  }

  public void load(double speed){
    // speed in percent
    m_bottomMotor.set(ControlMode.PercentOutput, speed);
  }

  public boolean isBallPresent(){
    // checks for ball
    return !m_ballDector.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}