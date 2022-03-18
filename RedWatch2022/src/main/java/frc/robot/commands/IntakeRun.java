// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeRun extends CommandBase {
  private final Timer m_timer = new Timer();
  private final Intake m_intake;
  private final double m_seconds;

  /** Creates a new IntakeRun. */
  public IntakeRun(Intake subsystem) {
    m_intake = subsystem;
    m_seconds = 10000;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake);
  }

  public IntakeRun(Intake subsystem, double seconds) {
    m_intake = subsystem;
    m_seconds = seconds;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();

    m_intake.lowerIntake();
    m_intake.intake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.stopIntake();

    m_timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.hasElapsed(m_seconds);
  }
}
