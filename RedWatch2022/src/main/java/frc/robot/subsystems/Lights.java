// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
// https://www.youtube.com/watch?v=wMdkM2rr1a4

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;
import static frc.robot.Constants.LightConstants.*;

public class Lights extends SubsystemBase {
 
  private static final double kDisabled = 0;
  private final Spark m_ledDriver;
  private final Intake m_intake;
  private final Indexer m_indexer;
  private char m_currentColor = ' ';

  /** Creates a new Lights. */
  public Lights() {
    m_ledDriver = new Spark(kBlinkinDriverPort);
    m_intake = new Intake();  
    m_indexer = new Indexer();
    resetLights();
  }

  /* TODO: When robot takes in ball of each color
   * When robot is speeding up flywheel 
   * When ball comes out of c-shooter
   * When robot is hanging?
   * When robot has completed hanging
   * Auto?
   */

  public void setDisabledColor() {
    m_ledDriver.set(kDisabled);
  }

  public void setOff() {
    m_ledDriver.set(kLightsOff);
  }

  public void intakeRed() {
    m_ledDriver.set(kRedBall);
  }

  public void intakeBlue() {
    m_ledDriver.set(kBlueBall);
  }

  public void resetLights() {
    m_ledDriver.set(kDefaultColor);
  }

  public double getCurrentLights() {
    return m_ledDriver.get();
  }

  // Show's green when the bot is orientated correctly under the rung.
  public void goodOrientation() {
    m_ledDriver.set(kCorrect);
  }

  // Show's red when the bot is not orientated correctly under the rung. 
  public void badOrientation() {
    m_ledDriver.set(kBad);
  }

  public void setGiven(double color) {
    m_ledDriver.set(color);
  }

  public void MaxSpeedFlyWheel() {
    m_ledDriver.set(kCorrect); // LEDs turn green while shooting
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_currentColor = Indexer.ballPositions[1];
    if (m_currentColor == 'R') {
      intakeRed();
    }
    else if (m_currentColor == 'B') {
      intakeBlue();
    }
    else {
      resetLights();
    }
  }
}
