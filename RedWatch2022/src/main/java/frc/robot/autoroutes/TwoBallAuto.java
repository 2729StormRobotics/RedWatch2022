// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autoroutes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commandgroups.ShootingRoutine;
import frc.robot.commands.AutoForward;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.LoadBallIntoMiddle;
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
      new AutoForward(45, drivetrain),
      new TurnAngle(180, drivetrain),
      new AutoForward(45, drivetrain),
      new TurnAngle(-22.5, drivetrain),
      new AutoForward(70, drivetrain),
      new ShootingRoutine(indexer, shooter, lights, 2000),
      new ShootingRoutine(indexer, shooter, lights, 2000)

    );
  }
}
