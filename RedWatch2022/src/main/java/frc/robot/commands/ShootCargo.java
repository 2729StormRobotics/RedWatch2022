// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// later add calculation for speed from vision

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

/*
THIS IS NOT A PID COMMAND
*/




public class ShootCargo extends CommandBase {
  /** Creates a new ShootCargo. */
  private final Shooter m_shooter;
  private final Lights m_lights;
  private double m_speed;

  public ShootCargo(double speed, Shooter shooter, Lights lights) {
    m_speed = speed;
    m_shooter = shooter; 
    m_lights = lights;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //stops shooting and resets encoder
      m_shooter.shoot(0.0);
      m_shooter.encoderReset(m_shooter.m_topEncoder);
      m_lights.RevFlyWheelYellow();
    
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //shoots at speed
    m_shooter.shoot(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.shoot(0.0);
    m_shooter.encoderReset(m_shooter.m_topEncoder);
    m_lights.MaxSpeedFlyWheel();
  }

  // Returns true when the command should end.
  // Shooter will run while B button is held
  @Override
  public boolean isFinished() {
    //ends command if topencoder is over desired value
    /*
    if (m_shooter.getTopDistance(m_shooter.m_topEncoder) > 100){ 
      return true;
    }
    */ 
    return false;
  }
}