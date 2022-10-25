// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ShooterConstants.*;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public final com.revrobotics.CANSparkMax m_topMotor;
  public final RelativeEncoder m_topEncoder;

  public double m_flyWheelSpeedAfterRev = 0;
  public double m_increment = 0;
  public double m_motorPower = 0;

  public boolean m_revving = false;

  public String m_teamColor = "Red";

  /**
   * Shooter subsystem controls flywheel
   */

  public Shooter() {
    // initialize motor
    m_topMotor = new CANSparkMax(kTopMotorPort, MotorType.kBrushless);
    motorInit(m_topMotor, kTopReversedDefault);

    // initialize encoder
    m_topEncoder = m_topMotor.getEncoder();
  }

  // sets defaults for topMotor
  public void motorInit(CANSparkMax motor, boolean invert){
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kCoast);
    motor.setSmartCurrentLimit(kCurrentLimit);
    motor.setInverted(invert);
    motor.setSmartCurrentLimit(kStallLimit);

    //resets encoder
    encoderInit(motor.getEncoder());
  }

  // initialize encoder
  private void encoderInit(RelativeEncoder encoder){
    //might add more stuff here later
    encoderReset(encoder);
  }

  // reset encoder position
  public void encoderReset(RelativeEncoder encoder){
    encoder.setPosition(0);
  }
  
  // get encoder position
  public double getTopDistance(RelativeEncoder encoder){
    return encoder.getPosition();
  }

  // set motor speed
  public void shoot(double speed){
    m_topMotor.set(speed);
  }

  // get rpm
  public double getEncoderVelocity(RelativeEncoder encoder) {
    return encoder.getVelocity();
  }

  public double getFlyWheelSpeedAfterRev() {
    return m_flyWheelSpeedAfterRev;
  }

  public void changeAlliance() {
    if (m_teamColor == "Red") {
      m_teamColor = "Blue";
    } else if (m_teamColor == "Blue") {
      m_teamColor = "Red";
    }
  }

  public String getAlliance() {
    return m_teamColor;
  }

  public boolean isRedTeam() {
    return getAlliance() == "Red";
  }

  public boolean isBlueTeam() {
    return getAlliance() == "Blue";
  }

  public void setRevving() {
    m_revving = true;
  }

  public void setDoneRevving() {
    m_revving = false;
  }

  public boolean isRevving() {
    return m_revving;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}