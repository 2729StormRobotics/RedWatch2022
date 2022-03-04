// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autoroutes;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import static frc.robot.Constants.AutoRouteConstants.*;

import frc.robot.commandgroups.IndexWhileMoving;
import frc.robot.commandgroups.ShootingRoutine;
import frc.robot.commands.AutoForward;
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

// diagram https://docs.google.com/document/d/183NxfY5ICa27UjnuzRZbAkt0sO0djNCv9FSNS1d3WBo/edit?usp=sharing
public class FiveBallAuto extends SequentialCommandGroup {
  /** Creates a new AutoRoute1. */
  public FiveBallAuto(Drivetrain drivetrain, Shooter shooter, Intake intake, Indexer indexer, Lights lights, Vision vision) {
    super(
      new IntakeRun(intake),
      
      //tarmac to ball 2
      new AutoForward(40.695 + 5, drivetrain),
      //shoot balls 1 & 2
      new VisionAlign(drivetrain, vision),
      new ShootingRoutine(indexer, shooter, lights, vision.getRPM()),
      new ShootingRoutine(indexer, shooter, lights, vision.getRPM()),
      
      //ball 2 to ball  
      new TurnAngle(-drivetrain.getGyroAngle() + 90 + 32.25, drivetrain),
      // new TurnAngle(90 + 32.25, drivetrain),
      new AutoForward(117.101, drivetrain),
      //shoot ball 3
      new VisionAlign(drivetrain, vision),
      new ShootingRoutine(indexer, shooter, lights, vision.getRPM()),

      //ball 3 to balls 4 & 5
      new TurnAngle(-drivetrain.getGyroAngle() + -32.25 - 10.792, drivetrain),
      //allows robot to index ball while moving forward
      new IndexWhileMoving(drivetrain, indexer, 159.865),
      new WaitCommand(0.75), //waits for human player. Not sure if this works, coded on github

      //balls 4 & 5 to tarmac
      new TurnAngle(171, drivetrain),
      new AutoForward(191.602, drivetrain),
      //shoot balls 4 & 5
      new VisionAlign(drivetrain, vision),
      new ShootingRoutine(indexer, shooter, lights, vision.getRPM()),
      new ShootingRoutine(indexer, shooter, lights, vision.getRPM())
    );
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
}
