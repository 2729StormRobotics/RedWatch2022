// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
command to drive at a certain power for left and right motors
*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class hangerControl extends CommandBase {

  private final Climber m_climber;
  private final DoubleSupplier m_leftSpeed;
  private final DoubleSupplier m_rightSpeed;
  private final BooleanSupplier m_leftBumper;
  private final BooleanSupplier m_rightBumper;

  /**
   * Controls specific motors of the Hanger subsystem.
   * Pressing left bumper allows separate control of left and right hanger motors.
   * Pressing right bumper allows separate control of left and right pivot motors.
   * @param leftSpeed Left thumbstick Y axis
   * @param rightSpeed Right thumbstick Y axis
   * @param leftBumper Left bumper button
   * @param rightBumper Right bumper button
   * @param subsystem Hanger subsystem
   */
  public hangerControl(DoubleSupplier leftSpeed, DoubleSupplier rightSpeed, BooleanSupplier leftBumper, BooleanSupplier rightBumper, Climber subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climber = subsystem;
    m_leftSpeed = leftSpeed;
    m_rightSpeed = rightSpeed;
    m_leftBumper = leftBumper;
    m_rightBumper = rightBumper;

    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climber.m_climbLeftExtend.stopMotor();
    m_climber.m_climbRightExtend.stopMotor();
    m_climber.m_climbRightExtend.stopMotor();
    m_climber.m_climbRightPivot.stopMotor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_leftBumper.getAsBoolean()) { // controls left side
      m_climber.m_climbLeftExtend.set(m_leftSpeed.getAsDouble() / 1.25);
      // m_climber.m_climbLeftPivot.set(m_leftSpeed.getAsDouble() / 4);
    } else if (m_rightBumper.getAsBoolean()) { // controls right side
      m_climber.m_climbRightExtend.set(m_rightSpeed.getAsDouble() / 1.25);
      // m_climber.m_climbRightPivot.set(m_rightSpeed.getAsDouble() / 4);
    } else { // controls hanger and pivot
      // Hanger motors
      m_climber.m_climbRightExtend.set(m_leftSpeed.getAsDouble() / 1.25);
      m_climber.m_climbLeftExtend.set(m_leftSpeed.getAsDouble() / 1.25);
      
      // Pivot motors
      m_climber.m_climbRightPivot.set(m_rightSpeed.getAsDouble() / 4);
      m_climber.m_climbLeftPivot.set(m_rightSpeed.getAsDouble() / 4);
    }
  }

  // Called once the command ends or is interrupted.
  // TODO: Check if m_climbRightExtend needs to be switched to m_climbLeftPivot
  @Override
  public void end(boolean interrupted) {
    m_climber.m_climbLeftExtend.stopMotor();
    m_climber.m_climbLeftExtend.stopMotor();
    m_climber.m_climbRightExtend.stopMotor();
    m_climber.m_climbRightPivot.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // command only stops when another is called
    return false;
  }
}
