// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import static frc.robot.Constants.ShooterConstants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RevFlywheel extends PIDCommand {
  /** Creates a new RevFlywheel. */
  public RevFlywheel(Vision vision, Shooter shooter) {

    super(
        // The controller that the command will use
        new PIDController(kLauncherP, kLauncherI, kLauncherD),
        // This should return the measurement
        () -> shooter.getEncoderVelocity(shooter.m_topEncoder) * 2.0,
        // This should return the setpoint (can also be a constant)
        () -> vision.getRPM(),
        // This uses the output
        output -> {
          shooter.shoot(output);
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(vision, shooter);
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(100);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
