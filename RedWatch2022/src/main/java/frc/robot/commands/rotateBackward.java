// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class rotateBackward extends CommandBase {

  private final Climber m_climber;

  /**
   * Pivots or rotates the bot backward
   * @param climber Hanger subsystem
   */
  public rotateBackward(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climber = climber;
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
    m_climber.turnMotor(m_climber.m_climbRightPivot, true);
    m_climber.turnMotor(m_climber.m_climbLeftPivot, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.resetGyroAngle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if (m_climber.getGyroAngle() >= 45) {
    //   return true;
    // }
    // else {
    //   return false;
    // }
    return false;
  }
}