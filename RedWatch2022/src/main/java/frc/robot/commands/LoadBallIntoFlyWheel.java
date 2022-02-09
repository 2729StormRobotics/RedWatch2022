// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Lights;

public class LoadBallIntoFlyWheel extends CommandBase {
  /** Creates a new LoadBall. */
  private final Indexer m_indexer;
  private final Lights m_lights;

  public LoadBallIntoFlyWheel(Indexer indexer, Lights lights) {
    m_indexer = indexer;
    m_lights = lights;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_indexer.load(0.0);
    m_lights.MaxSpeedFlyWheel();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Run the motor continuously
    m_indexer.load(Constants.kIndexerSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexer.load(0.0);
    m_lights.resetLights();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // stop when beam break is broken
    if (!m_indexer.isBallPresent()) {
      return true;
    }
    return false;
  }
}