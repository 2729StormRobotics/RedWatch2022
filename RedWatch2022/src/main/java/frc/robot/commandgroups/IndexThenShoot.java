// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.LoadBallIntoMiddle;
import frc.robot.commands.RevFlywheel;
import frc.robot.commands.RevToSpeed;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IndexThenShoot extends SequentialCommandGroup {

  /**
   * Runs the whole shooting process, starting with ball after intake
   * First, ball is loaded in between flywheel and indexer wheels
   * Next, flywheel is reved up to desired RPM
   * Lastly, flywheel pushes up ball to flywheel while it continues to run at desired RPM
   * @param indexer indexer subsystem
   * @param shooter shooter subsystem
   * @param lights lights subsystem
   * @param rpm wanted rpm of flywheel
   */

  /** Creates a new LoadThenShoot. */
  public IndexThenShoot(Indexer indexer, Shooter shooter, Lights lights, double rpm) {
    super(
      new LoadBallIntoMiddle(indexer),
      new RevToSpeed(rpm, shooter, lights),
      new LoadIntoFlywheelThenShoot(indexer, shooter, lights, rpm)
      
    );
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
}
