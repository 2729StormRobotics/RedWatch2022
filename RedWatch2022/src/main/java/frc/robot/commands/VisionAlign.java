// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import static frc.robot.Constants.VisionConstants.*;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class VisionAlign extends PIDCommand {

  private final Drivetrain m_drivetrain;
  private final Vision m_vision;
  
  /** Creates a new VisionAlign. */
  public VisionAlign(Drivetrain drivetrain, Vision vision) {
    super(
        // The controller that the command will use
        new PIDController(kAutoAlignP, kAutoAlignI, kAutoAlignD),
        // This should return the measurement
        () -> vision.getXOffset(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          /**
           * If a target is detected when the command is called the robot will align
           * itself to the center of the closest target using tank drive.
           * If a target is not detected when the command is called the robot will 
           * spin until a target is detected
           */
          if (vision.isTargetDetected()) {
            //drivetrain.tankDrive(output, -output, false);
            drivetrain.arcadeDrive(0, output, false);
          }
          else {
            drivetrain.tankDrive(1, -1, false);
          }
        });

        m_drivetrain = drivetrain;
        m_vision = vision;

    addRequirements(m_drivetrain, m_vision);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
  }
}
