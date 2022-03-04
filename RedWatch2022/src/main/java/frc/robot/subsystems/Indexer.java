// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

public class Indexer extends SubsystemBase {

  public final com.revrobotics.CANSparkMax bottomMotor;
  // Talon used for index motor
  public static TalonSRX m_bottomMotor = new TalonSRX(Constants.IndexerConstants.kIndexMotorPort);
  private final DigitalInput m_ballDector;
  public static char[] ballPositions = new char[2];

  /** Creates a new Indexer. */
  public Indexer() {
    m_ballDector = new DigitalInput(Constants.IndexerConstants.kBeamBreakPort);
    bottomMotor = new com.revrobotics.CANSparkMax(Constants.ShooterConstants.kTopMotorPort, MotorType.kBrushless);
    motorInit(bottomMotor, Constants.ShooterConstants.kTopReversedDefault);
    ballPositions[0] = ' ';
    ballPositions[1] = ' ';
  }

  public void motorInit(CANSparkMax motor, boolean invert){
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kCoast);
    motor.setSmartCurrentLimit(Constants.ShooterConstants.kCurrentLimit);
    motor.setInverted(invert);
    motor.setSmartCurrentLimit(Constants.ShooterConstants.kStallLimit);
  }

  public void load (double speed){
    //speed in percent
    m_bottomMotor.set(ControlMode.PercentOutput, speed);
  }

  public boolean isBallPresent(){
    //checks for ball
    return !m_ballDector.get();
  }

  public char getBottomBall () {
    return ballPositions[0];
  }

  public char getMiddleBall () {
    return ballPositions[1];
  }

  public static double getOffset () {
    if (ballPositions[0] == 'R') {
      return 45;
    }
    return 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}