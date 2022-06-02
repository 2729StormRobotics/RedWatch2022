// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

public class Meltdown extends CommandBase {
  private final Shooter shooter;
  /** Creates a new Meltdown. */
  public Meltdown(Climber m_climber, Drivetrain m_drivetrain, Indexer m_indexer, Intake m_intake, Lights m_lights, Shooter m_shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = m_shooter;
    addRequirements(m_climber, m_drivetrain, m_indexer, m_intake, m_lights, m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    new StopFlywheel(shooter);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    new StopFlywheel(shooter);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
