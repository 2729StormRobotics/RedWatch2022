// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
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

  public final XboxController m_weapons = new XboxController(Constants.IOPorts.kWeaponsController);

  /** Creates a new LoadThenShoot. */
  public ShootingRoutine(Indexer indexer, Shooter shooter, Lights lights, Vision vision, Drivetrain drivetrain) {


    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      // aligns robot to vision target
      new VisionAlign(drivetrain, vision),

      // unsure 
      // new TurnAngle(Indexer.getOffset(), drivetrain),

      // revs flywheel
      new RevToSpeed(vision.getRPM(), shooter, lights),

      // runs indexer until beam is broken
      new LoadBallIntoMiddle(indexer),

      // indexer runs until ball is launched
      new LoadBallIntoFlyWheel(indexer, shooter, lights));
  }

    /**
     * Alternative method to shoot if limelight is not present. Uses a preset RPM
     * @param indexer indexer subystem
     * @param shooter shooter subsystem
     * @param lights lights subsystem
     * @param drivetrain drive subsystem
     * @param rpm rpm of the flywheel
     */
  public ShootingRoutine(Indexer indexer, Shooter shooter, Lights lights, Drivetrain drivetrain, double rpm)  {

    addCommands(
      new RevToSpeed(rpm, shooter, lights),
      new LoadBallIntoMiddle(indexer),
      new LoadBallIntoFlyWheel(indexer, shooter, lights));
  }
   // if the same button is pressed, command will end
   @Override
   public boolean isFinished() {
     return (m_weapons.getYButtonPressed());
   }
}
