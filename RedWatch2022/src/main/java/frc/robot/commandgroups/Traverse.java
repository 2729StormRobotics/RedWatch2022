// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.extendDown;
import frc.robot.commands.extendUp;
import frc.robot.commands.rotateBackward;
import frc.robot.commands.rotateForward;
import frc.robot.subsystems.Climber;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Traverse extends SequentialCommandGroup {
  /** Creates a new traverse. */
  public Traverse(Climber climber) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    super(
      new extendUp(climber),
      new extendDown(climber),
      new rotateForward(climber, 45),
      new extendUp(climber),
      new rotateBackward(climber, 10)
    );
    
    addCommands();
  }
}
