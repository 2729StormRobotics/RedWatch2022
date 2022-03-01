// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class curvatureDrive extends CommandBase {
  
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_stickY;
  private final DoubleSupplier m_stickX;
  private final BooleanSupplier m_turnInPlace;

  /** Creates a new differentialDrive. */
  public curvatureDrive(DoubleSupplier stickY, DoubleSupplier stickX, BooleanSupplier turnInPlace, Drivetrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = subsystem;
    m_stickY = stickY;
    m_stickX = stickX;
    m_turnInPlace = turnInPlace;

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.stopDrive();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // drive with speeds of the parameter
    m_drivetrain.curvatureDrive(m_stickY.getAsDouble(), m_stickX.getAsDouble(), m_turnInPlace.getAsBoolean());
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // stop drive
    m_drivetrain.stopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // command only stops when another is called
    return false;
  }
}
