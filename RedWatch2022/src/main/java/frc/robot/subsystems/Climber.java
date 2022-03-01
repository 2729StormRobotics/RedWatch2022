// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

public class Climber extends SubsystemBase {
  
  public final CANSparkMax m_climbLeftExtend;
  public final CANSparkMax m_climbRightExtend;
  public final CANSparkMax m_climbLeftPivot;
  public final CANSparkMax m_climbRightPivot;

  public final RelativeEncoder m_climbLeftEncoder;
  public final RelativeEncoder m_climbRightEncoder;

  public final RelativeEncoder m_pivotLeftEncoder;
  public final RelativeEncoder m_pivotRightEncoder;

  private static AHRS m_ahrs;

  /**
   * Controls climbing mechanism
   * Requires 4 motors (2 leader 2 followers)
   * Controls arms that extend and retract and rotate
   */

  // Creates a new Climber. 
  public Climber() {
    m_climbLeftExtend = new CANSparkMax(kClimberLeftFollowerExtendPort, MotorType.kBrushless);
    m_climbRightExtend = new CANSparkMax(kClimberRightExtendPort, MotorType.kBrushless);
    m_climbLeftPivot = new CANSparkMax(kClimberLeftPivotFollowerPort, MotorType.kBrushless);
    m_climbRightPivot = new CANSparkMax(kClimberRightPivotPort, MotorType.kBrushless);

    // setMotor(motor, INVERSE);
    setMotor(m_climbLeftExtend, false);
    setMotor(m_climbRightExtend, true);
    setMotor(m_climbLeftPivot, false);
    setMotor(m_climbRightPivot, true);

    // m_climbLeftExtend.follow(m_climbRightExtend, true); // false for opposite; true for same direction
    // m_climbLeftPivot.follow(m_climbRightPivot, true);

    m_climbRightEncoder = m_climbRightExtend.getEncoder();
    m_climbLeftEncoder = m_climbLeftExtend.getEncoder();

    m_pivotRightEncoder = m_climbRightPivot.getEncoder();
    m_pivotLeftEncoder = m_climbLeftPivot.getEncoder();

    positionEncoderInit(m_climbRightEncoder);
    positionEncoderInit(m_climbLeftEncoder);
    pivotEncoderInit(m_pivotRightEncoder);
    pivotEncoderInit(m_pivotLeftEncoder);

    try {
      m_ahrs = new AHRS(SPI.Port.kMXP);
    } 
    catch (RuntimeException ex){
      DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
    }
  }

  public void changeMode(String mode) {

  }

  public void turnMotor(CANSparkMax motor, boolean inverse) {
    if (inverse) {
      motor.set(-0.1);
    }
    else {
      motor.set(0.1);
    }
  }

  private void positionEncoderInit(RelativeEncoder encoder) {
    encoder.setPositionConversionFactor(kDistancePerRevolution);

    encoderReset(encoder);
  }

  private void pivotEncoderInit(RelativeEncoder encoder) {
    encoder.setPositionConversionFactor(kAnglePerRevolution);
  }

  public void encoderReset(RelativeEncoder encoder) {
    encoder.setPosition(0);
  }

  public double getLeftDistance() {
    return -m_climbLeftEncoder.getPosition();
  }

  public double getRightDistance() {
    return -m_climbRightEncoder.getPosition();
  }

  public double getLeftPivot() {
    return -m_pivotLeftEncoder.getPosition();
  }

  public double getRightPivot() {
    return -m_pivotRightEncoder.getPosition();
  }

  public void setMotor(CANSparkMax motor, boolean inverse) {
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setInverted(inverse);
  }

  public double getGyroAngle(){
    return m_ahrs.getAngle();
  }

  public void resetGyroAngle(){
    m_ahrs.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
