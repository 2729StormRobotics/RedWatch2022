// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
command to drive at a certain power for left and right motors
*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class differentialDrive extends CommandBase {

  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_leftSpeed;
  private final DoubleSupplier m_rightSpeed;

  /** Controls the robot's drivetrain or movement subsystem
   * @param leftSpeed Left thumbstick Y axis
   * @param rightSpeed Right thumbstick Y axis
   * @param subsystem Drivetrain subsystem
   */
  public differentialDrive(DoubleSupplier leftSpeed, DoubleSupplier rightSpeed, Drivetrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = subsystem;
    m_leftSpeed = leftSpeed;
    m_rightSpeed = rightSpeed;

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.stopDrive();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // drive with speeds of the parameter
    m_drivetrain.tankDrive(m_leftSpeed.getAsDouble(), m_rightSpeed.getAsDouble(), true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // stop drive
    m_drivetrain.stopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // command only stops when another is called
    return false;
  }
}