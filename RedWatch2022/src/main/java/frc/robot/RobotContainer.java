// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoForward;
import frc.robot.commands.IndexEject;
import frc.robot.commands.IntakeAdjust;
import frc.robot.commands.IntakeMove;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.IntakeStop;
import frc.robot.commands.IntakeToggle;
import frc.robot.commands.IndexEject;
import frc.robot.commands.LoadBallIntoMiddle;
import frc.robot.commands.RevFlywheel;
import frc.robot.commands.RunFlywheel;
import frc.robot.commands.PartyMode;
import frc.robot.commands.TurnAngle;
import frc.robot.commands.VisionAlign;
import frc.robot.autoroutes.AutoRoute4;
import frc.robot.autoroutes.TwoBallAuto;
import frc.robot.commandgroups.EjectCargo;
import frc.robot.commandgroups.IntakeDriveReal;
import frc.robot.commandgroups.ShootingRoutine;
import frc.robot.commandgroups.Traverse;
import frc.robot.commands.hangerControl;
import frc.robot.commands.setLights;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.commands.curvatureDrive;
import static frc.robot.Constants.IOPorts.*;
import static frc.robot.Constants.LightConstants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Intake m_intake;
  private final Climber m_climber;
  private final Indexer m_indexer;
  private final Shooter m_shooter;
  private final Lights m_lights;
  private final Vision m_vision;
  private final Camera m_camera;

  private final Drivetrain m_drivetrain;

  private final XboxController m_driver = new XboxController(kDriverController);
  public final XboxController m_weapons = new XboxController(kWeaponsController);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_camera = new Camera();
    m_intake = new Intake();
    m_indexer = new Indexer();
    m_shooter = new Shooter();
    m_climber = new Climber();

    m_drivetrain = new Drivetrain();
    m_vision = new Vision();

    m_lights = new Lights(m_climber, m_intake, m_indexer, m_shooter, m_vision);

    // Set up Control Panel
    new ControlPanel(m_driver, m_weapons, m_drivetrain, m_climber, m_intake, m_indexer, m_shooter, m_lights);

    /**
     * Default command for the climbers. 
     * Left Joystick for telescoping arms, right for pivoting.
     * Holding down the bumpers allows for individual control of the corresponding telescoping arm
     */
    m_climber.setDefaultCommand(
      new hangerControl(() -> m_weapons.getLeftY(), () -> m_weapons.getRightY(), () -> m_weapons.getLeftBumper(), () -> m_weapons.getRightBumper(), m_climber));

    /**
     * Default command for the drivetrain
     * Uses curvatureDrive method similar to arcade
     * Left joystick for speed, right for turning
     * Holding down left trigger allows turning in place
     */ 
    m_drivetrain.setDefaultCommand(
      new curvatureDrive(() -> m_driver.getLeftY() / 1.5, () -> m_driver.getRightX() / 1.75, () -> Drivetrain.isTriggerPressed(m_driver.getLeftTriggerAxis()), m_drivetrain));

    m_intake.setDefaultCommand(
      new IntakeAdjust(() -> Intake.isTriggerPressed(m_weapons.getLeftTriggerAxis()), () -> Intake.isTriggerPressed(m_weapons.getRightTriggerAxis()), m_intake));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /**
     * Button mappings for the weapons controller. Currently set to:
     * A (when pressed) toggles the intake up/down
     * Y (while held) aligns the robot to a vision target
     * A (when pressed) drives the robot forward a set distance
     */
    // new JoystickButton(m_driver, Button.kA.value).whenPressed(new IntakeToggle(m_intake));
    new JoystickButton(m_driver, Button.kY.value).whenPressed(new VisionAlign(m_drivetrain, m_vision));
    new JoystickButton(m_driver, Button.kB.value).whenPressed(new AutoForward(50, m_drivetrain));
    new JoystickButton(m_driver, Button.kA.value).whenPressed(new TurnAngle(180, m_drivetrain));
    new JoystickButton(m_driver, Button.kStart.value).whenPressed(new PartyMode(m_lights));
    /**
     * Button mappings for the weapons controller. Currently set to:
     * X (while held) runs the intake motors
     * B (when pressed) runs the index motor until the beambreak is broken
     * Y (when pressed) runs the index then shoot command (index -> rev launcher based on distance -> shoot ball)
     * A (while held) reverses the index motor to eject the ball
     */
    new JoystickButton(m_weapons, Button.kStart.value).whenPressed(new IntakeToggle(m_intake));
    new JoystickButton(m_weapons, Button.kX.value).whenPressed(new IntakeRun(m_intake, 2));
    // new JoystickButton(m_weapons, Button.kBack.value).whileHeld(new EjectCargo(m_intake, m_indexer));
    new JoystickButton(m_weapons, Button.kB.value).whenPressed(new LoadBallIntoMiddle(m_indexer));
    // new JoystickButton(m_weapons, Button.kY.value).whenPressed(new ShootingRoutine(m_indexer, m_shooter, m_lights, m_vision, m_drivetrain));
    // new JoystickButton(m_weapons, Button.kA.value).whileHeld(new RunFlywheel(m_shooter));
     new JoystickButton(m_weapons, Button.kA.value).whenPressed(new ShootingRoutine(m_indexer, m_shooter, m_lights, 2700)); // low shot 1000, high 2300 from fender
    // new JoystickButton(m_weapons, Button.kA.value).whileHeld(new RevFlywheel(m_shooter, 2300)); // low shot 1000, high 2300 from fender
    // new JoystickButton(m_weapons, Button.kY.value).whenPressed(new ShootingRoutine(m_indexer, m_shooter, m_lights, m_vision.getRPM())); // high shot
    new JoystickButton(m_weapons, Button.kY.value).whenPressed(new IntakeMove(m_drivetrain, m_intake, 45));



    // new JoystickButton(m_weapons, Button.kY.value).whenPressed(new AutoRoute4(m_drivetrain, m_shooter, m_intake, m_indexer, m_lights, m_vision));
    // new JoystickButton(m_driver, Button.kA.value).whenPressed(new LoadBall(m_indexer));
    // new JoystickButton(m_driver, Button.kX.value).whileHeld(new EjectBall(m_indexer));
    // new JoystickButton(m_driver, Button.kB.value).whileHeld(new ShootCargo(Constants.kHighShootSpeed, m_shooter));
    // new JoystickButton(m_driver, Button.kY.value).whileHeld(new ShootCargo(Constants.kLowShootSpeed, m_shooter));

    // new JoystickButton(m_weapons, m_weapons.getPOV(0)).whileHeld(new Traverse(m_climber));
    // new JoystickButton(m_driver, m_driver.getPOV(180)).whileHeld(new extendDown(m_climber));
    // new JoystickButton(m_driver, m_driver.getPOV(270)).whileHeld(new rotateBot(m_climber));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new IntakeDriveReal(m_drivetrain, m_shooter, m_intake, m_indexer, m_lights, m_vision); // one ball auto off tarmac
  }
}
