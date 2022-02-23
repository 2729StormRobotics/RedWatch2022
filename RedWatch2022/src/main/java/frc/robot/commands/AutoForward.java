// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


/*
command to drive forward a certain distance using encoders and 
PID controller
 */

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoForward extends PIDCommand {
  private final Drivetrain m_drivetrain;

  /** Creates a new AutoForwardPID. */
  public AutoForward(double distance, Drivetrain drivetrain) {
    super(
        // The controller that the command will use
        // experimentrally determined values
        new PIDController(Constants.DrivetrainConstants.AutoForwardP, Constants.DrivetrainConstants.AutoForwardI, Constants.DrivetrainConstants.AutoForwardD),
        // This should return the measurement
        () -> drivetrain.getAverageDistance(),
        // This should return the setpoint (can also be a constant)
        () -> distance,
        // This uses the output
        output -> {
          // Use the output here
          // drive forward
          drivetrain.tankDrive(-output, -output, false);
          
        });

        m_drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(Constants.DrivetrainConstants.PositionTolerace, Constants.DrivetrainConstants.VelocityTolerance);


  }

  // Called when the command is initially scheduled.
  // manually added command
  @Override
  public void initialize() {
    m_drivetrain.resetAllEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }

  // manually added end function
  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    m_drivetrain.resetAllEncoders();
  }
}