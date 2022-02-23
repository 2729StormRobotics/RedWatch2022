// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autoroutes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.AutoForward;
import frc.robot.commands.TurnAngle;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoRoute1 extends SequentialCommandGroup {
  /** Creates a new AutoRoute1. */
  public AutoRoute1(Drivetrain drivetrain, Shooter shooter) {
    super(
      new AutoForward(40.695 + Constants.kRobotLength/2, drivetrain),
      //intake
      //vision turn
      //shoot

      new TurnAngle(-drivetrain.getGyroAngle() + 90 + 32.25, drivetrain),
      new AutoForward(117.101, drivetrain),
      //intake
      //vision turn
      //shoot

      new TurnAngle(-drivetrain.getGyroAngle() - 32.25 - 10.792, drivetrain),
      new AutoForward(159.865, drivetrain)


    );
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
}
