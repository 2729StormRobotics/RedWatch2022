// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RevFlywheel extends PIDCommand {
  private final Shooter m_shooter;
  private final Lights m_lights;

  /**
   * PID command that will rev the flywheel to a given RPM.
   * Meant to be run while ball is in between flywheel and indexer wheels.
   * Second command in the shooting process.
   * LEDs will turn yellow while flywheel is reving up.
   * Command ends when flywheel reaches max RPM and should immediately run ShootBall and 
   * LoadBallIntoFlyWheel synchronously.
   * @param rpm desired rpm flywheel will reach
   * @param shooter shooter subsystem
   * @param lights lights subsystem
   */

  /** Creates a new ShootBall. */
  public RevFlywheel(double rpm, Shooter shooter, Lights lights) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kFlyWheelPID, 0, 0),
        // This should return the measurement
        () -> shooter.getEncoderVelocity(shooter.m_topEncoder),
        // This should return the setpoint (can also be a constant)
        () -> rpm,
        // This uses the output
        output -> {
          // Use the output here
          shooter.topMotor.set(MathUtil.clamp(output, -1,1);
        });

        m_shooter = shooter;
        m_lights = lights;
        getController().setTolerance(Constants.kFlyWheelTolerance);


    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  @Override
  public void initialize() {
    m_shooter.encoderReset(m_shooter.m_topEncoder);
    m_lights.RevFlyWheelYellow();
    
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    m_shooter.encoderReset(m_shooter.m_topEncoder);
    m_lights.MaxSpeedFlyWheel();
  }
  
}
