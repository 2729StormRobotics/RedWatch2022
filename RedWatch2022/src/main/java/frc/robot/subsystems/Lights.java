// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
// https://www.youtube.com/watch?v=wMdkM2rr1a4

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import static frc.robot.Constants.LightConstants.*;

public class Lights extends SubsystemBase {
 
  private static final double kDisabled = 0;
  private final Spark m_ledDriver;
  private char m_currentColor = ' ';

  private final Drivetrain m_drivetrain;
  private final Climber m_climber;
  private final Intake m_intake;
  private final Indexer m_indexer;
  private final Shooter m_shooter;
  private final Vision m_vision;

  //TODO: Change before each match
  private int m_alliance = 0; // 0 = red, 1 = blue

  private boolean m_partyMode = false;
  private boolean m_shooting = false;

  /** Creates a new Lights. */
  public Lights(Drivetrain drivetrain, Climber climber, Intake intake, Indexer indexer, Shooter shooter, Vision vision) {
    m_ledDriver = new Spark(kBlinkinDriverPort);

    m_drivetrain = drivetrain;
    m_climber = climber;
    m_intake = intake;
    m_indexer = indexer;
    m_shooter = shooter;
    m_vision = vision;

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

  public void setRed() {
    m_ledDriver.set(kRed);
  }

  public void setRedStopped() {
    m_ledDriver.set(kRedStopped);
  }

  public void setBlue() {
    m_ledDriver.set(kBlue);
  }

  public void setBlueStopped() {
    m_ledDriver.set(kBlueStopped);
  }

  public void resetLights() {
    m_ledDriver.set(kDefaultColor);
  }

  public double getCurrentLights() {
    return m_ledDriver.get();
  }

  public void setGiven(double color) {
    m_ledDriver.set(color);
  }

  public void maxSpeedFlyWheel() {
    m_shooting = true;
  }

  public void togglePartyMode() {
    m_partyMode = !m_partyMode;
  }

  public void party() {
    m_ledDriver.set(kParty);
  }

  public void aligning() {
    m_ledDriver.set(kAligning);
  }

  public void revving() {
    m_ledDriver.set(kRevving);
  }

  public void shooting() {
    m_ledDriver.set(kShooting);
  }

  @Override
  public void periodic() {

   if (m_partyMode) {
     party();
   }
   //ROBOT INTAKES BALLS STROBE 2 SECONDS?
   else if (m_vision.isAligning()) {
     aligning();
   }
   else if (m_shooter.isRevving()) {
     revving();
   }
   else if (m_shooting) {
    shooting();
    new WaitCommand(1.5);
    m_shooting = false;
   }
   else if ((m_alliance == 0) && (m_drivetrain.getAverageSpeed() > 5)) {
    setRedStopped();
   }
   else if ((m_alliance == 1) && (m_drivetrain.getAverageSpeed() > 5)) {
    setRedStopped();
   }
   else if (m_alliance == 0) {
     setRed();
   }
   else if (m_alliance == 1) {
     setBlue();
   }
  }

}
