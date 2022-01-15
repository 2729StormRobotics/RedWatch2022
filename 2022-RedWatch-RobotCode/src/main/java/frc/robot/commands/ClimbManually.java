// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Hanger;

public class ClimbManually extends CommandBase {
  private final Hanger m_climber;
  private final DoubleSupplier m_speed;

  /** Creates a new ClimbManually. */
  public ClimbManually(DoubleSupplier speed, Hanger subsystem) {
    m_climber = subsystem;
    m_speed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_climber.atMaxHeight() && -m_speed.getAsDouble() > 0){
      m_climber.stopClimb();
    }
    else if (m_climber.atMinHeight() && -m_speed.getAsDouble() < 0){ 
      m_climber.stopClimb();
    }
    else if (Math.abs(m_speed.getAsDouble()) < 0.05){
      m_climber.stopClimb();
    }
    else {
      m_climber.climb(m_speed.getAsDouble());
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.stopClimb();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
