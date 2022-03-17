// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lights;
import static frc.robot.Constants.LightConstants.*;

public class PartyMode extends CommandBase {
  private final Lights m_lights;
  private final double m_currentColor;

  /** Creates a new PartyMode. */
  public PartyMode(Lights lights) {
    m_lights = lights;
    m_currentColor = Math.round(m_lights.getCurrentLights() * 100.0) / 100.0;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_lights);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_currentColor != kParty) {
      m_lights.setGiven(kParty);
    }
    else {
      m_lights.setGiven(kDefaultColor);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
