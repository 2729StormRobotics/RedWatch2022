// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

public class ConsistentSpinFlyWheel extends CommandBase {

  Shooter m_shooter;
  Lights m_lights;
  Indexer m_indexer;
  /** Creates a new ConsistentSpinFlyWheel. */
  public ConsistentSpinFlyWheel(Shooter shooter, Indexer indexer, Lights lights) {
    m_shooter = shooter;
    m_lights = lights;
    m_indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_lights.MaxSpeedFlyWheel();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.topMotor.set(m_shooter.getFlyWheelSpeedAfterRev());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.encoderReset(m_shooter.m_topEncoder);
    m_lights.resetLights();
    m_shooter.topMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !m_indexer.isBallPresent();
  }
}
