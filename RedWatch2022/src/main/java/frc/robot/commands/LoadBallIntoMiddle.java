// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.IndexerConstants.*;
import frc.robot.subsystems.Indexer;

public class LoadBallIntoMiddle extends CommandBase {
  /** Creates a new LoadBall. */
  private final Indexer m_indexer;

  /**
   * Spins the indexer wheel such that the ball will be positioned
   * between the flywheel and the indexer wheel (when the beam break is broken).
   * This is the first command in the shooting process.
   * @param indexer indexer subsystem
   */

  public LoadBallIntoMiddle(Indexer indexer) {
    m_indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Run the motor continuously
    m_indexer.load(-kIndexerSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexer.load(0.0);
    Indexer.ballPositions[1] = Indexer.ballPositions[0];
    Indexer.ballPositions[0] = ' ';
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_indexer.isBallPresent();
    }
  }