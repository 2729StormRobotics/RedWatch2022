// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.commands.AutoForward;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.LoadBallIntoMiddle;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeMove extends ParallelDeadlineGroup {
  /** Creates a new IntakeMove. */
  public IntakeMove(Drivetrain drivetrain, Intake intake, Indexer indexer, double distance) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(new AutoForward(distance, drivetrain));
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new IntakeRun(intake),
                new LoadBallIntoMiddle(indexer)
                );
  }
}
