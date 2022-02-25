// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeEject;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.IntakeStop;
import frc.robot.commands.IntakeToggle;
import frc.robot.commandgroups.Traverse;
import frc.robot.commands.hangerControl;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.commands.curvatureDrive;

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

  private final Drivetrain m_drivetrain;

  private final XboxController m_driver = new XboxController(Constants.kDriverController);
  private final XboxController m_weapons = new XboxController(Constants.kWeaponsController);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_lights = new Lights();
    m_intake = new Intake();
    m_indexer = new Indexer();
    m_shooter = new Shooter();

    m_climber = new Climber();
    m_climber.setDefaultCommand(new hangerControl(() -> m_weapons.getLeftY(), () -> m_weapons.getRightY(), () -> m_weapons.getLeftBumper(), () -> m_weapons.getRightBumper(), m_climber));

    // Set up drivetrain
    m_drivetrain = new Drivetrain();
    m_drivetrain.setDefaultCommand(
      new curvatureDrive(() -> m_driver.getLeftY() / 2.0, () -> m_driver.getRightX() / 2.0, m_driver, m_drivetrain));

    // Set up Control Panel
    new ControlPanel(m_driver, m_weapons, m_drivetrain, m_climber, m_intake, m_lights);

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
    // A button loads ball in
    // X ejects ball
    // B shoots ball high
    // Y shoots ball low
    new JoystickButton(m_driver, Button.kA.value).whenPressed(new IntakeToggle(m_intake));
    new JoystickButton(m_driver, Button.kB.value).whenPressed(new IntakeEject(m_intake));
    new JoystickButton(m_driver, Button.kX.value).whenPressed(new IntakeRun(m_intake));
    new JoystickButton(m_driver, Button.kY.value).whenPressed(new IntakeStop(m_intake));
    
    // new JoystickButton(m_driver, Button.kA.value).whenPressed(new LoadBall(m_indexer));
    // new JoystickButton(m_driver, Button.kX.value).whileHeld(new EjectBall(m_indexer));
    // new JoystickButton(m_driver, Button.kB.value).whileHeld(new ShootCargo(Constants.kHighShootSpeed, m_shooter));
    // new JoystickButton(m_driver, Button.kY.value).whileHeld(new ShootCargo(Constants.kLowShootSpeed, m_shooter));
    new JoystickButton(m_weapons, m_weapons.getPOV(0)).whileHeld(new Traverse(m_climber));
    // new JoystickButton(m_driver, m_driver.getPOV(180)).whileHeld(new extendDown(m_climber));
    // new JoystickButton(m_driver, m_driver.getPOV(270)).whileHeld(new rotateBot(m_climber));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
