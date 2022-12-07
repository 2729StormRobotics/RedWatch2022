// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**This is supposed to allow the robot to travel in a square like shape with curved edges */

package frc.robot.commandgroups;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class BoxRoutePreseason extends CommandBase {
  
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_stickY;
  private final DoubleSupplier m_stickX;
  private final BooleanSupplier m_turnInPlace;

  /** Creates a new differentialDrive. */
  public curvatureDrive (0, -127, BooleanSupplier turnInPlace, Drivetrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = subsystem;
    m_stickY = stickY;
    m_stickX = stickX;
    m_turnInPlace = turnInPlace;


    //This moves the robot forwards 60 inches
    new AutoForwardPID(60, drivetrain));
    //This line hard coded the value -127 for the x axis of the joystick this would turn it left
    new curvatureDrive(0,-127,BooleanSupplier turnInPlace, Drivetrain subsystem);
    new AutoForwardPID(60, drivetrain));
    new curvatureDrive(0,-127,BooleanSupplier turnInPlace, Drivetrain subsystem);
    new AutoForwardPID(60, drivetrain));
    new curvatureDrive(0,-127,BooleanSupplier turnInPlace, Drivetrain subsystem);
    new AutoForwardPID(60, drivetrain));
    new curvatureDrive(0,-127,BooleanSupplier turnInPlace, Drivetrain subsystem);



    addRequirements(m_drivetrain);
    addCommands();

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
    m_drivetrain.curvatureDrive(Drivetrain.sqaureInput(m_stickY.getAsDouble()), Drivetrain.sqaureInput(m_stickX.getAsDouble()), m_turnInPlace.getAsBoolean());
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