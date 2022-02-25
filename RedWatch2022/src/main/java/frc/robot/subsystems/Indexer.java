// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Indexer extends SubsystemBase {

  //talon used for index motor
  public static TalonSRX m_bottomMotor = new TalonSRX(Constants.Ports.kIndexMotorPort);
  private final DigitalInput m_ballDector;

  /** Creates a new Indexer. */
  public Indexer() {
    m_ballDector = new DigitalInput(Constants.Ports.kBeamBreakPort);
    motorInit();
  }

  private void motorInit(){
    m_bottomMotor.configPeakCurrentDuration(Constants.IndexerConstants.kDRIVE_AMPERAGE_PEAK_DURATION, Constants.IndexerConstants.kCAN_TIMEOUT_SETUP);
    m_bottomMotor.configPeakCurrentLimit(Constants.IndexerConstants.kDRIVE_AMPERAGE_LIMIT_PEAK, Constants.IndexerConstants.kCAN_TIMEOUT_SETUP);
    m_bottomMotor.configContinuousCurrentLimit(Constants.IndexerConstants.kDRIVE_AMPERAGE_LIMIT_CONTINUOUS, Constants.IndexerConstants.kCAN_TIMEOUT_SETUP);
    m_bottomMotor.enableCurrentLimit(true);

  }

  public void load (double speed){
    //speed in percent
    m_bottomMotor.set(ControlMode.PercentOutput, speed);
  }

  public boolean isBallPresent(){
    //checks for ball
    return !m_ballDector.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
