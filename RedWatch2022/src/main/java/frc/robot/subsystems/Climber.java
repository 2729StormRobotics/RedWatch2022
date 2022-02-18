// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.ClimberConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
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

  private final ShuffleboardTab m_climbTableTab;
  private final ShuffleboardLayout m_climbStatus;

  private static AHRS navx;
  AHRS ahrs;

  /**
   * Controls climbing mechanism
   * Requires 4 motors (2 leader 2 followers)
   * Controls arms that extend and retract and rotate
   */

  /** Creates a new Climber. 
   * @param Map */
  public Climber() {

    m_climbLeftExtend = new CANSparkMax(ClimberConstants.kClimberLeftFollowerExtendPort, MotorType.kBrushed);
    m_climbRightExtend = new CANSparkMax(ClimberConstants.kClimberRightExtendPort, MotorType.kBrushed);
    m_climbLeftPivot = new CANSparkMax(ClimberConstants.kClimberLeftPivotFollowerPort, MotorType.kBrushed);
    m_climbRightPivot = new CANSparkMax(ClimberConstants.kClimberRightPivotPort, MotorType.kBrushed);

    setMotor(m_climbLeftExtend);
    setMotor(m_climbRightExtend);
    setMotor(m_climbLeftPivot);
    setMotor(m_climbRightPivot);

    m_climbLeftExtend.follow(m_climbRightExtend);
    m_climbLeftPivot.follow(m_climbRightPivot);

    m_climbRightEncoder = m_climbRightExtend.getEncoder();

    encoderInit(m_climbRightEncoder);

    m_climbTableTab = Shuffleboard.getTab(ClimberConstants.kClimberTab);
    m_climbStatus = m_climbTableTab.getLayout("Climb Status", BuiltInLayouts.kList);

    try {
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex){
      DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
    }

  }

  public void turnMotor(CANSparkMax motor, boolean inverse) {
    if (inverse) {
      motor.set(-0.5);
    }
    else {
      motor.set(0.5);
    }
  }

  private void encoderInit(RelativeEncoder encoder) {
    encoder.setVelocityConversionFactor(ClimberConstants.kLowSpeedPerPulse);

    encoderReset(encoder);
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

  public void setMotor(CANSparkMax motor) {
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setInverted(true);
  }

  public double getGyroAngle(){
    return ahrs.getAngle();
  }

  public void resetGyroAngle(){
    ahrs.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
