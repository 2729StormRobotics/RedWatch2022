// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// diagram https://docs.google.com/document/d/183NxfY5ICa27UjnuzRZbAkt0sO0djNCv9FSNS1d3WBo/edit?usp=sharing

package frc.robot.autoroutes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import static frc.robot.Constants.AutoRouteConstants.*;

import frc.robot.commandgroups.IntakeIndex;
import frc.robot.commandgroups.IntakeMove;
import frc.robot.commandgroups.ShootingRoutine;
import frc.robot.commandgroups.ShootingRoutineDouble;
import frc.robot.commands.AutoForward;
import frc.robot.commands.IntakeLower;
import frc.robot.commands.IntakeRaise;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.LoadBallIntoMiddle;
import frc.robot.commands.TurnAngle;
import frc.robot.commands.VisionAlign;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoRoute1 extends SequentialCommandGroup {
  /** Creates a new AutoRoute1. */
  public AutoRoute1(Drivetrain drivetrain, Shooter shooter, Intake intake, Indexer indexer, Lights lights, Vision vision) {
    super(
      new IntakeLower(intake),
      new WaitCommand(0.5),
      new TurnAngle(0, drivetrain),
      new VisionAlign(drivetrain, vision),
      new VisionAlign(drivetrain, vision),
      new VisionAlign(drivetrain, vision),
      new ShootingRoutine(indexer, shooter, lights, vision.getRPM()),
      new TurnAngle(143, drivetrain),
      

      //tarmac -> ball 2
      new IntakeIndex(drivetrain, intake, indexer, 46.695), // 40.695
      new LoadBallIntoMiddle(indexer),
      new AutoForward(-15, drivetrain),

// 107
      //ball 2 -> ball 3
      new TurnAngle(97, drivetrain),
      new IntakeMove(drivetrain, intake, indexer, 90),
      new IntakeRaise(intake),
      new TurnAngle(110, drivetrain),
      new VisionAlign(drivetrain, vision),
      new VisionAlign(drivetrain, vision),
      new AutoForward(39, drivetrain),
      new VisionAlign(drivetrain, vision),
      new VisionAlign(drivetrain, vision),
      new ShootingRoutineDouble(indexer, shooter, lights, vision.getRPM())
      // new ShootingRoutine(indexer, shooter, lights, vision.getRPM()),
      // new ShootingRoutine(indexer, shooter, lights, vision.getRPM())
      
      // new VisionAlign(drivetrain, vision),
      // new ShootingRoutine(indexer, shooter, lights, 1000)

      // new TurnAngle(drivetrain.getGyroAngle() + -32.25 - 10.792, drivetrain)
      // new AutoForward(159.865, drivetrain)

    );

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
}
