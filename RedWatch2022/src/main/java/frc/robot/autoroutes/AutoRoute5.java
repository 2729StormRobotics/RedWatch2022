// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autoroutes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import static frc.robot.Constants.AutoRouteConstants.*;
import frc.robot.commands.AutoForward;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoRoute5 extends SequentialCommandGroup {
  /** Creates a new AutoRoute5. */
  public AutoRoute5(Drivetrain drivetrain, Shooter shooter) {

    super(
      new AutoForward(kRobotLength + 20, drivetrain)
    );
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
}
