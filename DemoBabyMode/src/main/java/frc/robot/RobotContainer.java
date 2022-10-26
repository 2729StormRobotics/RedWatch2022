// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.net.PortForwarder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.curvatureDrive;
import static frc.robot.Constants.IOPorts.*;
import static frc.robot.Constants.LightConstants.*;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Intake m_intake;
  private final Shooter m_shooter;
  // private final Camera m_camera;

  private final Drivetrain m_drivetrain;

  private final XboxController m_driver = new XboxController(kDriverController);
  public final XboxController m_weapons = new XboxController(kWeaponsController);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // m_camera = new Camera();
    m_intake = new Intake();
    m_shooter = new Shooter();

    m_drivetrain = new Drivetrain();


    // Set up Control Panel

    /**
     * Default command for the climbers. 
     * Left Joystick for telescoping arms, right for pivoting.
     * Holding down the bumpers allows for individual control of the corresponding telescoping arm
     */

    /**
     * Default command for the drivetrain
     * Uses curvatureDrive method similar to arcade
     * Left joystick for speed, right for turning
     * Holding down left trigger allows turning in place
     */ 
    m_drivetrain.setDefaultCommand(
      new curvatureDrive(() -> m_driver.getLeftY() / 1.25, () -> m_driver.getRightX() / 1.75, () -> Drivetrain.isTriggerPressed(m_driver.getLeftTriggerAxis()), m_drivetrain));

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
    /**
     * Button mappings for the weapons controller. Currently set to:
     * X (while held) runs the intake motors
     * B (when pressed) runs the index motor until the beambreak is broken
     * Y (when pressed) runs the index then shoot command (index -> rev launcher based on distance -> shoot ball)
     * A (while held) reverses the index motor to eject the ball
     */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // return new AutoRoute1(m_drivetrain, m_shooter, m_intake, m_indexer, m_lights, m_vision); // one ball auto off tarmac
    return new curvatureDrive(() -> m_driver.getLeftY() / 1.25, () -> m_driver.getRightX() / 1.75, () -> Drivetrain.isTriggerPressed(m_driver.getLeftTriggerAxis()), m_drivetrain);
  }
}