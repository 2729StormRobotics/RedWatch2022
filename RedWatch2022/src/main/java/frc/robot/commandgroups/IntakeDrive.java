// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeRun;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.autoroutes.AutoRoute4;
import frc.robot.commands.AutoForward;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeDrive extends SequentialCommandGroup {
  /** Creates a new IntakeDrive. */
  public IntakeDrive(Drivetrain drivetrain, Shooter shooter, Intake intake, Indexer indexer, Lights lights, Vision vision) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new IntakeRun(intake),
                new AutoRoute4(drivetrain, shooter, intake, indexer, lights, vision));
  }
}
