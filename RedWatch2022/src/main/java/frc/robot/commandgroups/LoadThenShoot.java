// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.LoadBallIntoFlyWheel;
import frc.robot.commands.LoadBallIntoMiddle;
import frc.robot.commands.ShootBall;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LoadThenShoot extends SequentialCommandGroup {
  /** Creates a new LoadThenShoot. */
  public LoadThenShoot(Indexer indexer, Shooter shooter, Lights lights, double rpm) {
    super(
      new LoadBallIntoMiddle(indexer),
      new ShootBall(rpm, shooter, lights),
      new LoadBallIntoFlyWheel(indexer, lights)
      
    );
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
}
