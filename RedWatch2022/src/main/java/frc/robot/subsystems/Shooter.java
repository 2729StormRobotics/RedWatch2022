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

  public Shooter() {
    //init motor
    topMotor = new com.revrobotics.CANSparkMax(Constants.TOP_MOTOR_ID, MotorType.kBrushless);
    motorInit(topMotor, Constants.kTopReversedDefault);
    topMotor.setSmartCurrentLimit(Constants.STALL_LIMIT);
    topMotor.setIdleMode(IdleMode.kBrake);

    m_topEncoder = topMotor.getEncoder();
  }

  //sets defaults for topMotor
  public void motorInit(CANSparkMax motor, boolean invert){
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setSmartCurrentLimit(Constants.kCurrentLimit);
    motor.setInverted(invert);

    //resets encoder
    encoderInit(motor.getEncoder());
  }

  private void encoderInit(RelativeEncoder encoder){
    //might add more stuff here later
    encoderReset(encoder);
  }

  public void encoderReset(RelativeEncoder encoder){
    encoder.setPosition(0);
  }
  
  public double getTopDistance(RelativeEncoder encoder){
    return encoder.getPosition();
  }

  //spinny motor
  public void shoot (double speed){
    topMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}