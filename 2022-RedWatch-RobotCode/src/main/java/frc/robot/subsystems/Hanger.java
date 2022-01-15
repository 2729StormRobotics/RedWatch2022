// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.Constants;

public class Hanger extends SubsystemBase {
  /** Creates a new Hanger. */
  public final CANSparkMax arm1;
  private final RelativeEncoder m_encoder;
  private boolean m_retracted = true;

  private final ShuffleboardTab m_climberTab;
  private final ShuffleboardLayout m_climberStatus;


  public Hanger() {
    arm1 = new CANSparkMax(Constants.kArm1MotorPort, CANSparkMax.MotorType.kBrushless);
    m_encoder = arm1.getEncoder();

    m_climberTab = Shuffleboard.getTab(Constants.kShuffleBoardTab);
    m_climberStatus = m_climberTab.getLayout("Climber Status", BuiltInLayouts.kList).withProperties(Map.of("Label Position", "TOP"));

    shuffleboardInit();

  }

  private void motorInit(CANSparkMax motor, boolean invert){
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setInverted(invert);
    encoderInit(motor.getEncoder());
  }

  private void encoderInit(RelativeEncoder encoder){
    encoder.setPositionConversionFactor(Constants.kDistancePerPulse);
    encoder.setVelocityConversionFactor(Constants.kSpeedPerPulse);
    encoderReset(encoder);

  }

  private void encoderReset(RelativeEncoder encoder){
    encoder.setPosition(0);
  }

  public void climb(double speed){
    arm1.set(-speed);
    }
  
  public void climbUp(){
    climb(Constants.kClimbUpSpeed);
  }

  public void climbDown(){
    climb(Constants.kClimbDownSpeed);
  }

  public void stopClimb(){
    climb(0.0);
  }

  private double getHeight() {
    return m_encoder.getPosition();
  }

  private double getSpeed() {
    return m_encoder.getVelocity();
  }

  public boolean atMaxHeight() {
    return getHeight() > Constants.kMaxHeight;
  }

  public boolean atMinHeight() {
    return getHeight() < 0;
  }

  private void shuffleboardInit(){
    m_climberStatus.addNumber("Height", () -> getHeight()).withWidget(BuiltInWidgets.kNumberBar)
    .withProperties(Map.of("Min", -1)).withProperties(Map.of("Max", 23));
    m_climberStatus.addNumber("Speed", () -> getSpeed());
    m_climberStatus.addBoolean("Fully Retracted", () -> m_retracted);
  }


  @Override
  public void periodic() {
    if (getHeight() > 1){
      m_retracted = false;
    }else {
      m_retracted = true;
    }

    // This method will be called once per scheduler run
  }
}
