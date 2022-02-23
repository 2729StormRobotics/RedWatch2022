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
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

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
    leftMotor = new com.revrobotics.CANSparkMax(Constants.Ports.LEFT_MOTOR_ID, MotorType.kBrushless);
    leftMotor2 = new com.revrobotics.CANSparkMax(Constants.Ports.LEFT_MOTOR2_ID, MotorType.kBrushless);
    rightMotor = new com.revrobotics.CANSparkMax(Constants.Ports.RIGHT_MOTOR_ID, MotorType.kBrushless);
    rightMotor2 = new com.revrobotics.CANSparkMax(Constants.Ports.RIGHT_MOTOR2_ID, MotorType.kBrushless);

    motorInit(leftMotor, Constants.DrivetrainConstants.kLeftReversedDefault);
    motorInit(leftMotor2, Constants.DrivetrainConstants.kLeftReversedDefault);
    motorInit(rightMotor, Constants.DrivetrainConstants.kRightReversedDefault);
    motorInit(rightMotor2, Constants.DrivetrainConstants.kRightReversedDefault);

    leftMotor.setSmartCurrentLimit(Constants.DrivetrainConstants.STALL_LIMIT);
    rightMotor.setSmartCurrentLimit(Constants.DrivetrainConstants.STALL_LIMIT);
    leftMotor2.setSmartCurrentLimit(Constants.DrivetrainConstants.STALL_LIMIT);
    rightMotor2.setSmartCurrentLimit(Constants.DrivetrainConstants.STALL_LIMIT);

    leftMotor.setIdleMode(IdleMode.kBrake);
    leftMotor2.setIdleMode(IdleMode.kBrake);
    rightMotor.setIdleMode(IdleMode.kBrake);
    rightMotor2.setIdleMode(IdleMode.kBrake);

    // leftMotor2.follow(leftMotor);
    // rightMotor2.follow(rightMotor);
    final MotorControllerGroup rightControllerGroup = new MotorControllerGroup(rightMotor, rightMotor2);
    final MotorControllerGroup leftControllerGroup = new MotorControllerGroup(leftMotor, leftMotor2);


    m_leftEncoder = leftMotor.getEncoder();
    m_rightEncoder = rightMotor.getEncoder();

    m_drive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);

    try {
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex){
      DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
    }
    
  }

  public void motorInit(CANSparkMax motor, boolean invert) {
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setSmartCurrentLimit(Constants.DrivetrainConstants.kCurrentLimit);
    motor.setInverted(invert);

    encoderInit(motor.getEncoder());
  }

  private void encoderInit(RelativeEncoder encoder) {
    // set conversion factor and velocity factor for high gear
    encoder.setPositionConversionFactor(Constants.DrivetrainConstants.kDistancePerRevolution);
    encoder.setVelocityConversionFactor(Constants.DrivetrainConstants.kSpeedPerRevolution);
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

  /** The Tank Drive mode is used to control each side of the drivetrain
   *  independently (usually with an individual joystick axis controlling each).
   * @param leftPower Speed of the robot's left side
   * @param rightPower Speed of the robot's right side
   * @param squareInputs Decreases the input sensitivity at low speeds
   */
  public void tankDrive(double leftPower, double rightPower, boolean squareInputs) {
    if (m_reverseDrive) {
      m_drive.tankDrive(leftPower/2, rightPower/2, squareInputs);
    }
    else {
      m_drive.tankDrive(leftPower/2, rightPower/2, squareInputs); 
    }
  }

  public void arcadeDrive(double speed, double turn, boolean squareInputs) {
    m_drive.arcadeDrive(speed, turn, squareInputs);
  }

    public void stopDrive() {
      m_drive.tankDrive(0, 0);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}