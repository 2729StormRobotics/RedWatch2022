// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import com.analog.adis16470.frc.ADIS16470_IMU;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DrivetrainConstants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  // Declare motor objects
  public final com.revrobotics.CANSparkMax leftMotor;
  public final com.revrobotics.CANSparkMax leftMotor2;
  public final com.revrobotics.CANSparkMax rightMotor;
  public final com.revrobotics.CANSparkMax rightMotor2;

  // Declare encoders
  public final RelativeEncoder m_leftEncoder;
  public final RelativeEncoder m_rightEncoder;

  // TODO: use these | Declare PID controllers
  // private final SparkMaxPIDController m_pidControllerLeft;
  // private final SparkMaxPIDController m_pidControllerRight;

  // Declare DifferentialDrive & DifferentialDrive Odometry
  private final DifferentialDrive m_drive;

  // Declare navX
  AHRS m_ahrs;

  public Drivetrain() {
    // initialize motors
    leftMotor = new com.revrobotics.CANSparkMax(kLeftMotorPort, MotorType.kBrushless);
    leftMotor2 = new com.revrobotics.CANSparkMax(kLeftMotor2Port, MotorType.kBrushless);
    rightMotor = new com.revrobotics.CANSparkMax(kRightMotorPort, MotorType.kBrushless);
    rightMotor2 = new com.revrobotics.CANSparkMax(kRightMotor2Port, MotorType.kBrushless);

    // reset motors to preset settings
    motorInit(leftMotor, kLeftReversedDefault);
    motorInit(leftMotor2, kLeftReversedDefault);
    motorInit(rightMotor, kRightReversedDefault);
    motorInit(rightMotor2, kRightReversedDefault);

    // create MotorControllerGroups to link left and right side motors
    final MotorControllerGroup rightControllerGroup = new MotorControllerGroup(rightMotor, rightMotor2);
    final MotorControllerGroup leftControllerGroup = new MotorControllerGroup(leftMotor, leftMotor2);

      // TODO: use these PID controllers 
    // m_pidControllerLeft = leftMotor.getPIDController();
    // m_pidControllerRight = rightMotor.getPIDController();

    // set encoders
    m_leftEncoder = leftMotor.getEncoder();
    m_rightEncoder = rightMotor.getEncoder();

    // initialize DifferentialDrive
    m_drive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);

    // initialize NavX if detected
    try {
      m_ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex){
      DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
    }
  }

  /**
   * Resets the default motor settings
   * 
   * @param motor which motor
   * @param invert whether to invert the motors. Left side is inverted by default
   */
  public void motorInit(CANSparkMax motor, boolean invert) {
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kCoast);
    motor.setSmartCurrentLimit(kCurrentLimit);
    motor.setSmartCurrentLimit(kStallLimit);
    motor.setInverted(invert);

    encoderInit(motor.getEncoder());
  }

  /**
   * Set conversion factor and velocity factor based on gear ratio
   * 
   * @param encoder
   */
  private void encoderInit(RelativeEncoder encoder) {
    encoder.setPositionConversionFactor(kDistancePerRevolution);
    encoder.setVelocityConversionFactor(kSpeedPerRevolution);
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
    return m_leftEncoder.getPosition();
  }

  public double getRightDistance() {
    return m_rightEncoder.getPosition();
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
    return m_ahrs.getAngle();
  }

  public double getGyroPitch() {
    return m_ahrs.getPitch();
  }

  public void resetGyroAngle() {
    m_ahrs.reset();
  }

  public static double sqaureInput(double input) {
    return Math.copySign(input * input, input);
  }

  public static double inputDeadzone(double input) {
    if (Math.abs(input) < kControllerDeadzone) {
      return 0.0;
    }
    return input;
  }
  
  public static boolean isTriggerPressed(double trigger) {
    return trigger > 0.95;
  }

  /** The Tank Drive mode is used to control each side of the drivetrain
   *  independently (usually with an individual joystick axis controlling each).
   * 
   * @param leftPower Speed of the robot's left side
   * @param rightPower Speed of the robot's right side
   * @param squareInputs Decreases the input sensitivity at low speeds
   */
  public void tankDrive(double leftPower, double rightPower, boolean squareInputs) {
      m_drive.tankDrive(-leftPower, -rightPower, squareInputs);
  }

  /**
   * Curvature drive method. The forward/reverse should be inverted to match the robot
   * 
   * @param stickY The robot's speed along the X axis
   * @param stickX The curvature; clockwise is positive
   * @param stickButton Allows turn in place
   */
  public void curvatureDrive(double stickY, double stickX, boolean stickButton) {
    m_drive.curvatureDrive(-stickY, stickX, stickButton);
  }

  /**
   * Arcade drive method. The forward/reverse should be inverted to match the robot
   * 
   * @param speed Speed of the robot
   * @param turn The turn; clockwise is positive
   * @param squareInputs Decreases the input sensitivity at low speeds
   */
  public void arcadeDrive(double speed, double turn, boolean squareInputs) {
    m_drive.arcadeDrive(-speed, turn, squareInputs);
  }

  public void stopDrive() {
    m_drive.tankDrive(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}