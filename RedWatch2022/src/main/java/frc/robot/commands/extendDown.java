// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Climber;

public class extendDown extends CommandBase {
  private final Climber m_climber;

  /**
   * Decreases the height of the hangers
   * @param subsystem Hanger subsystem
   */
  public extendDown(Climber subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climber = subsystem;
    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climber.encoderReset(m_climber.m_climbLeftEncoder);
    m_climber.encoderReset(m_climber.m_climbRightEncoder);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climber.turnMotor(m_climber.m_climbRightExtend, true);
    m_climber.turnMotor(m_climber.m_climbLeftExtend, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.encoderReset(m_climber.m_climbLeftEncoder);
    m_climber.encoderReset(m_climber.m_climbRightEncoder);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_climber.getRightDistance() > ClimberConstants.kClimberRightSize) {
      return true;
    } else {
      return false;
    }
  }
}
