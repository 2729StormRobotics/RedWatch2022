// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;

public class EjectBall extends CommandBase {
  /** Creates a new EjectBall. */
  private final Indexer m_indexer;

  /**
   * Ejects the ball.
   * @param indexer Indexer subsystem
   */
  public EjectBall(Indexer indexer) {
    m_indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_indexer.load(0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  
  // Run the indexer motor in reverse while button is held to push out ball
  @Override
  public void execute() {
    m_indexer.load(-Constants.kIndexerSpeed);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexer.load(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}