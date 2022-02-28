// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.LoadBallIntoFlyWheel;
import frc.robot.commands.LoadBallIntoMiddle;
import frc.robot.commands.RevToSpeed;
import frc.robot.commands.TurnAngle;
import frc.robot.commands.VisionAlign;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootingRoutine extends SequentialCommandGroup {

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
  public ShootingRoutine(Indexer indexer, Shooter shooter, Lights lights, Vision vision, Drivetrain drivetrain) {
    super(
      new VisionAlign(drivetrain, vision),
      new TurnAngle(Indexer.getOffset(), drivetrain),
      new RevToSpeed(vision.getRPM(), shooter, lights),
      new LoadBallIntoMiddle(indexer),
      new LoadBallIntoFlyWheel(indexer, shooter, lights)
      
    );
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands();
  }
   // command manually added
   @Override
   public boolean isFinished() {
     return(RobotContainer.m_weapons.getYButtonPressed());
   }
}
