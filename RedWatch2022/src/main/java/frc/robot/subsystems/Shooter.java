// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public final com.revrobotics.CANSparkMax topMotor;
  public final RelativeEncoder m_topEncoder;

  public double flyWheelSpeedAfterRev = 0;
  public double increment = 0;
  public double motorPower = 0;

  public String teamColor = "Red";

  /**
   * Shooter subsystem controls flywheel
   */

  public Shooter() {
    // initialize motor
    topMotor = new com.revrobotics.CANSparkMax(Constants.ShooterConstants.kTopMotorPort, MotorType.kBrushless);
    motorInit(topMotor, Constants.ShooterConstants.kTopReversedDefault);
    

    // initialize encoder
    m_topEncoder = topMotor.getEncoder();
  }

  // sets defaults for topMotor
  public void motorInit(CANSparkMax motor, boolean invert){
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kCoast);
    motor.setSmartCurrentLimit(Constants.ShooterConstants.kCurrentLimit);
    motor.setInverted(invert);
    motor.setSmartCurrentLimit(Constants.ShooterConstants.kStallLimit);

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
  public void shoot (double speed){
    topMotor.set(speed);
  }

  // get rpm
  public double getEncoderVelocity(RelativeEncoder encoder) {
    return encoder.getVelocity();
  }

  public double getFlyWheelSpeedAfterRev() {
    return flyWheelSpeedAfterRev;
  }

  public void changeAlliance() {
    if (teamColor == "Red") {
      teamColor = "Blue";
    } else if (teamColor == "Blue") {
      teamColor = "Red";
    }
  }

  private String getAlliance() {
    return teamColor;
  }

  public boolean isRedTeam() {
    return getAlliance() == "Red";
  }

  public boolean isBlueTeam() {
    return getAlliance() == "Blue";
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}