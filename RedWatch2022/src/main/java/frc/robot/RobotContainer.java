// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ShootCargo;
import frc.robot.commands.curvatureDrive;
import frc.robot.commands.differentialDrive;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Shooter m_shooter;

  private final Drivetrain m_drivetrain;

  private final XboxController m_driver = new XboxController(Constants.kDriverController);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // m_indexer = new Indexer();
    m_shooter = new Shooter();

    // Set up drivetrain
    m_drivetrain = new Drivetrain();
    m_drivetrain.setDefaultCommand(
      new curvatureDrive(() -> m_driver.getLeftY() / 2.0, () -> -m_driver.getRightX() / 2.0, m_driver, m_drivetrain));

    // Set up Control Panel
    new ControlPanel(m_drivetrain);

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
