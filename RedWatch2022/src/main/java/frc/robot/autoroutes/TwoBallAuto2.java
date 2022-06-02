// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autoroutes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commandgroups.IntakeIndex;
import frc.robot.commandgroups.ShootingRoutine;
import frc.robot.commands.AutoForward;
import frc.robot.commands.IntakeLower;
import frc.robot.commands.IntakeRaise;
import frc.robot.commands.TurnAngle;
import frc.robot.commands.VisionAlign;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

// USED ON THE TARMAC FACING THE WALL

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallAuto2 extends SequentialCommandGroup {
  /** Creates a new TwoBallAuto. */
  public TwoBallAuto2(Drivetrain drivetrain, Shooter shooter, Intake intake, Indexer indexer, Lights lights, Vision vision) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      //shoot ball 1
      new IntakeLower(intake),
      new WaitCommand(0.5),
      new TurnAngle(20, drivetrain),
      new ShootingRoutine(indexer, shooter, lights, vision.getRPM()),
      new TurnAngle(160, drivetrain),

      //ball 2
      new IntakeIndex(drivetrain, intake, indexer, 47.0),
      new IntakeRaise(intake),  
      new AutoForward(-40, drivetrain),
      new TurnAngle(-160, drivetrain),
      new VisionAlign(drivetrain, vision),
      new VisionAlign(drivetrain, vision),
      // new ShootingRoutine(indexer, shooter, lights, 2600)
      new ShootingRoutine(indexer, shooter, lights, vision.getRPM())

      // //hub
      // // new AutoForward(-67.5, drivetrain),
      // new TurnAngle(237, drivetrain),
      // new AutoForward(74, drivetrain),
      // new ShootingRoutine(indexer, shooter, lights, 2000)
    );
  }
}
 