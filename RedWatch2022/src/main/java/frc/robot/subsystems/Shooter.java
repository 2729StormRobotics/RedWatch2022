// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public final com.revrobotics.CANSparkMax topMotor;
  public final RelativeEncoder m_topEncoder;

  private final ShuffleboardTab m_shooterTab;
  private final ShuffleboardLayout m_shooterTabStatus;

  /**
   * Shooter subsystem controls flywheel
   */

  public Shooter() {
    // initialize motor
    topMotor = new com.revrobotics.CANSparkMax(Constants.ShooterConstants.TOP_MOTOR_ID, MotorType.kBrushless);
    motorInit(topMotor, Constants.ShooterConstants.kTopReversedDefault);
    topMotor.setSmartCurrentLimit(Constants.ShooterConstants.STALL_LIMIT);
    topMotor.setIdleMode(IdleMode.kBrake);

    // initialize encoder
    m_topEncoder = topMotor.getEncoder();

    // initialize shuffleboard
    m_shooterTab = Shuffleboard.getTab(Constants.kShooterTab);
    m_shooterTabStatus = m_shooterTab.getLayout("Status", BuiltInLayouts.kList)
    .withProperties(Map.of("Label position", "TOP"));
    shuffleboardInit();
  }

  // sets defaults for topMotor
  public void motorInit(CANSparkMax motor, boolean invert){
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kCoast);
    motor.setSmartCurrentLimit(Constants.ShooterConstants.kCurrentLimit);
    motor.setInverted(invert);

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

  // adds rpm to shuffleboard
  private void shuffleboardInit() {
    m_shooterTabStatus.addNumber("Encoder Velocity", () -> getEncoderVelocity(m_topEncoder));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}