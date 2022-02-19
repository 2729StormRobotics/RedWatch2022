// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.ConsistentSpinFlyWheel;
import frc.robot.commands.LoadBallIntoFlyWheel;
import frc.robot.commands.ShootBall;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LoadIntoFlywheelThenShoot extends ParallelCommandGroup {

  /**
   * runs ShootBall and LoadBallIntoFlyWheel command in parallel.
   * allows flywheel to continiously run while indexer motor pushes up ball
   * @param indexer indexer subsystem
   * @param shooter shooter subsystem
   * @param lights lights subsystem
   * @param rpm wanted rpm of flywheel
   */

  /** Creates a new LoadIntoFlywheelThenShoot. */
  public LoadIntoFlywheelThenShoot(Indexer indexer, Shooter shooter, Lights lights, double rpm) {
    super(
      new ShootBall(shooter, indexer, lights),
      new LoadBallIntoFlyWheel(indexer, lights)
    );
    addCommands();
  }
}
