// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autoroutes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commandgroups.ShootingRoutine;
import frc.robot.commands.AutoForward;
import frc.robot.commands.TurnAngle;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallAuto extends SequentialCommandGroup {
  /** Creates a new TwoBallAuto. */
  public TwoBallAuto(Drivetrain drivetrain, Shooter shooter, Intake intake, Indexer indexer, Lights lights) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      //shoot ball 1
      new ShootingRoutine(indexer, shooter, lights, 2000),

      //ball 2
      new TurnAngle(159, drivetrain),
      new AutoForward(67.5, drivetrain),

      //hub
      // new AutoForward(-67.5, drivetrain),
      new TurnAngle(237, drivetrain),
      new AutoForward(74, drivetrain),
      new ShootingRoutine(indexer, shooter, lights, 2000)
    );
  }
}
