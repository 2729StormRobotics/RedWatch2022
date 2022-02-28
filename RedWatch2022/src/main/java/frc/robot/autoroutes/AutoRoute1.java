// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autoroutes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import static frc.robot.Constants.AutoRouteConstants.*;

import frc.robot.commandgroups.IndexThenShoot;
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
public class AutoRoute1 extends SequentialCommandGroup {
  /** Creates a new AutoRoute1. */
  public AutoRoute1(Drivetrain drivetrain, Shooter shooter, Intake intake, Indexer indexer, Lights lights ) {
    super(
      //FIXME:: ACCOUNT FOR SHOOTING WITH LIMELIGHT
      new IntakeRun(intake),
      new AutoForward(40.695 + kRobotLength/2.0, drivetrain),
      new IndexThenShoot(indexer, shooter, lights, 1500),

      //accounting for shooting: new TurnAngle(-drivetrain.getGyroAngle() + 90 + 32.25, drivetrain),
      new TurnAngle(90 + 32.25, drivetrain),
      new AutoForward(117.101, drivetrain),
      new LoadBallIntoMiddle(indexer),

      //accounting for shooting: new TurnAngle(-drivetrain.getGyroAngle() - 32.25 - 10.792, drivetrain),
      new TurnAngle(-32.25 - 10.792, drivetrain),
      new AutoForward(159.865, drivetrain),
      new IndexThenShoot(indexer, shooter, lights, 1500)

    );
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
}
