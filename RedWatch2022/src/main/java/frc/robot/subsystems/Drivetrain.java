// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import com.analog.adis16470.frc.ADIS16470_IMU;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;


public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  public final com.revrobotics.CANSparkMax leftMotor;
  public final com.revrobotics.CANSparkMax leftMotor2;

  public final com.revrobotics.CANSparkMax rightMotor;
  public final com.revrobotics.CANSparkMax rightMotor2;

  public final RelativeEncoder m_leftEncoder;
  public final RelativeEncoder m_rightEncoder;

  private boolean m_highGear = true;


  private final DifferentialDrive m_drive;

  public boolean m_reverseDrive = false;
  AHRS ahrs;



  public Drivetrain() {
    leftMotor = new com.revrobotics.CANSparkMax(Constants.LEFT_MOTOR_ID, MotorType.kBrushless);
    leftMotor2 = new com.revrobotics.CANSparkMax(Constants.LEFT_MOTOR2_ID, MotorType.kBrushless);
    rightMotor = new com.revrobotics.CANSparkMax(Constants.RIGHT_MOTOR_ID, MotorType.kBrushless);
    rightMotor2 = new com.revrobotics.CANSparkMax(Constants.RIGHT_MOTOR2_ID, MotorType.kBrushless);

    motorInit(leftMotor, Constants.kLeftReversedDefault);
    motorInit(leftMotor2, Constants.kLeftReversedDefault);
    motorInit(rightMotor, Constants.kRightReversedDefault);
    motorInit(rightMotor2, Constants.kRightReversedDefault);

    leftMotor.setSmartCurrentLimit(Constants.STALL_LIMIT);
    rightMotor.setSmartCurrentLimit(Constants.STALL_LIMIT);
    leftMotor2.setSmartCurrentLimit(Constants.STALL_LIMIT);
    rightMotor2.setSmartCurrentLimit(Constants.STALL_LIMIT);

    leftMotor.setIdleMode(IdleMode.kBrake);
    leftMotor2.setIdleMode(IdleMode.kBrake);
    rightMotor.setIdleMode(IdleMode.kBrake);
    rightMotor2.setIdleMode(IdleMode.kBrake);

    leftMotor2.follow(leftMotor);
    rightMotor2.follow(rightMotor);

    m_leftEncoder = leftMotor.getEncoder();
    m_rightEncoder = rightMotor.getEncoder();

    m_drive = new DifferentialDrive(leftMotor, rightMotor);

    try {
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex){
      DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
    }
    
  }

  public void motorInit(CANSparkMax motor, boolean invert) {
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setSmartCurrentLimit(Constants.kCurrentLimit);
    motor.setInverted(invert);

    encoderInit(motor.getEncoder());
  }

  private void encoderInit(RelativeEncoder encoder) {
    // set conversion factor and velocity factor for high gear

      encoder.setPositionConversionFactor(Constants.kDistancePerRevolution);
      encoder.setVelocityConversionFactor(Constants.kSpeedPerRevolution);
    
    
    encoderReset(encoder);

  }

  public void resetAllEncoders(){
    encoderReset(m_rightEncoder);
    encoderReset(m_leftEncoder);
  }

  public void encoderReset(RelativeEncoder encoder) {
    encoder.setPosition(0);
  }

  public double getLeftDistance() {
    return -m_leftEncoder.getPosition();
  }

  public double getRightDistance() {
    return -m_rightEncoder.getPosition();
  }

  public double getLeftSpeed() {
    return -m_leftEncoder.getVelocity();
  }

  public double getRightSpeed() {
    return -m_rightEncoder.getVelocity();
  }

  public double getAverageDistance() {
    return (getRightDistance() + getLeftDistance())/2; 
  }

  public double getAverageSpeed() {
    return (getRightSpeed() + getLeftSpeed())/2;
  }
  
  public double getGyroAngle() {
    return ahrs.getAngle();
  }

  public double getGyroPitch() {
    return ahrs.getPitch();
  }

  public void resetGyroAngle(){
    ahrs.reset();
  }

  public void tankDrive(double leftPower, double rightPower, boolean squareInputs) {
    if (m_reverseDrive) {
      m_drive.tankDrive(leftPower/2, rightPower/2, squareInputs);
    }
    else {
      m_drive.tankDrive(leftPower/2, rightPower/2, squareInputs); 
    }
  }

    public void stopDrive() {
      m_drive.tankDrive(0, 0);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}