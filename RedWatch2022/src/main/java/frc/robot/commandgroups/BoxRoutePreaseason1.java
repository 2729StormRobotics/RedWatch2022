// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import java.util.function.DoubleSupplier;

import java.util.function.BooleanSupplier;
import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.AutoForward;
import frc.robot.commands.curvatureDrive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BoxRoutePreaseason1 extends SequentialCommandGroup {
  /** Creates a new BoxRoutePreaseason1. */
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_stickY;
  private final DoubleSupplier m_stickX;
  private final BooleanSupplier m_turnInPlace;
 
 
  public BoxRoutePreaseason1(DoubleSupplier stickX, DoubleSupplier stickY, BooleanSupplier turnInPlace, Drivetrain subsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
        // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = subsystem;
    m_stickY = stickY;
    m_stickX = stickX;
    m_turnInPlace = turnInPlace;
    
    
        //This moves the robot forwards 60 inches
        new AutoForward(60, m_drivetrain);
        //This line hard coded the value -127 for the x axis of the joystick this would turn it left
        new curvatureDrive(0.0 ,-127.0, turnInPlace, m_drivetrain);
        new AutoForward(60, m_drivetrain);
        new curvatureDrive(0.0,-127.0, turnInPlace, m_drivetrain);
        new AutoForward(60, m_drivetrain);
        new curvatureDrive(0.0,-127.0, turnInPlace, m_drivetrain);
        new AutoForward(60, m_drivetrain);
        new curvatureDrive(0.0,-127.0, turnInPlace, m_drivetrain);
            
        addRequirements(m_drivetrain);
        addCommands();
  }
}
