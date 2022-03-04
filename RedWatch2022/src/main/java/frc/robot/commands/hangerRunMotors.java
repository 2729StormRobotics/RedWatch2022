// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
command to drive at a certain power for left and right motors
*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class hangerRunMotors extends CommandBase {

  private final Climber m_climber;
  private final DoubleSupplier m_leftSpeed;
  private final DoubleSupplier m_rightSpeed;
  private final CANSparkMax m_leftMotor;
  private final CANSparkMax m_rightMotor;

  /** Controls specific motors of the Hanger subsystem
   * @param leftSpeed Left thumbstick Y axis
   * @param rightSpeed Right thumbstick Y axis
   * @param leftMotor Left Spark Max motor
   * @param rightMotor Right Spark Max motor
   * @param subsystem Hanger subsystem
   */
  public hangerRunMotors(DoubleSupplier leftSpeed, DoubleSupplier rightSpeed, CANSparkMax leftMotor, CANSparkMax rightMotor, Climber subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climber = subsystem;
    m_leftSpeed = leftSpeed;
    m_rightSpeed = rightSpeed;
    m_leftMotor = leftMotor;
    m_rightMotor = rightMotor;

    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_leftMotor.stopMotor();
    m_rightMotor.stopMotor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // move motors of hanger and pivot
    m_leftMotor.set(m_leftSpeed.getAsDouble());
    m_rightMotor.set(m_rightSpeed.getAsDouble());
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // stop hanger and pivot
    m_leftMotor.stopMotor();
    m_rightMotor.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // command only stops when another is called
    return false;
  }
}