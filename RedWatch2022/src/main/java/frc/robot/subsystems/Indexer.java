// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.IndexerConstants.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Indexer extends SubsystemBase {

  // Spark used for index motor
  public final CANSparkMax m_bottomMotor;

  // Talon used for index motor
  // public static TalonSRX m_bottomMotor = new TalonSRX(Constants.IndexerConstants.kIndexMotorPort);

  private final DigitalInput m_ballDector;
  public static char[] ballPositions = new char[2];

  /** Creates a new Indexer. */
  public Indexer() {
    m_ballDector = new DigitalInput(kBeamBreakPort);
    m_bottomMotor = new com.revrobotics.CANSparkMax(kIndexMotorPort, MotorType.kBrushless);
    motorInit(m_bottomMotor, false);
    ballPositions[0] = ' ';
    ballPositions[1] = ' ';
  }

  public void motorInit(CANSparkMax motor, boolean invert){
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kCoast);
    motor.setSmartCurrentLimit(kCurrentLimit);
    motor.setInverted(invert);
    motor.setSmartCurrentLimit(kStallLimit);
  }

  public void load (double speed){
    //speed in percent
    m_bottomMotor.set(speed);
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