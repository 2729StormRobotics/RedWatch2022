// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;

public class LoadBall extends CommandBase {
  /** Creates a new LoadBall. */
  private final Indexer m_indexer;

  /**
   * Loads the ball.
   * @param indexer Indexer subsystem
   */
  public LoadBall(Indexer indexer) {
    m_indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_indexer.load(0.0);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Run the motor continuously
    m_indexer.load(Constants.IndexerConstants.kIndexerSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexer.load(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // stop when beam break is broken
    if (m_indexer.isBallPresent()){
      return true;
    }
    return false;
  }
}