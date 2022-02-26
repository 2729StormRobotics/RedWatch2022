// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

<<<<<<< HEAD:RedWatch2022/src/main/java/frc/robot/commands/LoadBallIntoMiddle.java


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
=======
public class rotateBot extends CommandBase {

  private final Climber m_climber;

  /** Creates a new rotateBot. */
  public rotateBot(Climber subsystem) {
>>>>>>> Dev:RedWatch2022/src/main/java/frc/robot/commands/rotateBot.java
    // Use addRequirements() here to declare subsystem dependencies.
    m_climber = subsystem;
    addRequirements(m_climber);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climber.resetGyroAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
<<<<<<< HEAD:RedWatch2022/src/main/java/frc/robot/commands/LoadBallIntoMiddle.java
    // Run the motor continuously
    m_indexer.load(Constants.IndexerConstants.kIndexerSpeed);
=======
    m_climber.turnMotor(m_climber.m_climbRightPivot, false);
    m_climber.turnMotor(m_climber.m_climbLeftPivot, false);
>>>>>>> Dev:RedWatch2022/src/main/java/frc/robot/commands/rotateBot.java
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.resetGyroAngle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_climber.getGyroAngle() >= 45) {
      return true;
    }
    else {
      return false;
    }
  }
}