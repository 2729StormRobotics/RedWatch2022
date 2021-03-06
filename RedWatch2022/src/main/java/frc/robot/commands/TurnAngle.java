// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
turn to a desired angle using a gyroscope and PID controller
*/

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.Constants.DrivetrainConstants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnAngle extends PIDCommand {
  private final Drivetrain m_drivetrain;
  /** Creates a new TurnDistanceGyro. */
  public TurnAngle(double targetAngle, Drivetrain drivetrain) {
    super(
        // The controller that the command will use
        // PID values experimentally determined
        new PIDController(kTurnAngleP, kTurnAngleI, kTurnAngleD),
        // This should return the measurement
        () -> drivetrain.getGyroAngle(),
        // This should return the setpoint (can also be a constant)
        () -> targetAngle,
        // This uses the output
        output -> {
          // Use the output here
          // turns clockwise if targetvalue is positive
          // turns counterclockwise if targetvalue is negative
        
          // drivetrain.arcadeDrive(0, output, false);
          // drivetrain.tankDrive(-output, output, true);
          drivetrain.curvatureDrive(0, output, true);
        });
        m_drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    getController().setTolerance(kTurnAngleTolerace, kTurnSpeedTolerance);
    addRequirements(drivetrain);
      }
    // Configure additional PID options by calling `getController` here.
  

 // Called when the command is initially scheduled.
 // command manually added
  @Override
  public void initialize() {
    // reset gyro angle
     m_drivetrain.resetGyroAngle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
  
  // command manually added
  @Override
  public void end(boolean interrupted) {
    // reset gyro angle
    super.end(interrupted);
     m_drivetrain.resetGyroAngle();
  }
}