// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

public class RevToSpeed extends CommandBase {
  Lights m_lights;
  Shooter m_shooter;
  double m_motorPower = 0;
  double currentRPM = 0;
  double m_TargetRPM;
  double m_increment = 0;
  double m_error = 0;
  boolean m_finished = false;

  /** Creates a new RevToSpeed. */
  public RevToSpeed(double rpm, Shooter shooter, Lights lights) {

    m_TargetRPM = rpm * 2;
    m_shooter = shooter;
    m_lights = lights;

    double m_motorPower = 0;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter, m_lights);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // m_shooter.topMotor.set(0);
    m_shooter.encoderReset(m_shooter.m_topEncoder);
    m_motorPower = 0;
    currentRPM = 0;
    m_increment = 0;
    m_error = 0;
    m_finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Find the difference between target rpm and current rpm, and increment motor speed based on difference
    // If difference is lower, increment will be lower. If difference is negative, increment is negative
    currentRPM = m_shooter.getEncoderVelocity(m_shooter.m_topEncoder);
    m_error = m_TargetRPM - currentRPM;

    m_increment = m_error / 170000.0;
    m_shooter.increment = m_increment;
    m_motorPower += m_increment;
    m_shooter.motorPower = m_motorPower;

    m_shooter.topMotor.set(m_motorPower);

    if (Math.abs(m_error) <= 20) {
      m_finished = true;
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.flyWheelSpeedAfterRev = m_motorPower;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // finishes when rpm is within 20 rpm of target
    return m_finished;
  }
}