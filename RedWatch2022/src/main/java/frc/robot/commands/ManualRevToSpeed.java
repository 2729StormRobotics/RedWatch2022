// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

public class ManualRevToSpeed extends CommandBase {
  Shooter m_shooter;
  Lights m_lights;
  Double m_rpm;
  /** Creates a new ManualRevToSpeed. */
  public ManualRevToSpeed(double rpm, Shooter shooter, Lights lights) {
    m_shooter = shooter;
    m_lights = lights;
    m_rpm = rpm;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.topMotor.set(0);
    m_shooter.encoderReset(m_shooter.m_topEncoder);
    m_lights.RevFlyWheelYellow();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.topMotor.set(m_rpm);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.encoderReset(m_shooter.m_topEncoder);
    m_lights.resetLights();
    m_shooter.topMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}