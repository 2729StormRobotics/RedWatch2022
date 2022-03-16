// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class IntakeAdjust extends CommandBase {
  private final Intake m_intake;
  private final Indexer m_indexer;
  private final BooleanSupplier leftTriggerStatus;
  private final BooleanSupplier rightTriggerStatus;

  /** Creates a new IntakeAdjust. */
  public IntakeAdjust(BooleanSupplier leftTrigger, BooleanSupplier rightTrigger, Intake intake, Indexer indexer) {
    leftTriggerStatus = leftTrigger;
    rightTriggerStatus = rightTrigger;
    m_intake = intake;
    m_indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake, m_indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (leftTriggerStatus.getAsBoolean()) {
      m_intake.eject();
      m_indexer.load(0.5);
    } else if (rightTriggerStatus.getAsBoolean()) {
      m_intake.intake();
    } else if (leftTriggerStatus.getAsBoolean() && rightTriggerStatus.getAsBoolean()) {
      m_intake.stopIntake();
    } else {
      m_intake.stopIntake();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
