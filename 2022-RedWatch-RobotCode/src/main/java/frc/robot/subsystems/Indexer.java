// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Indexer extends SubsystemBase {

  public final CANSparkMax bottomMotor;
  public final DigitalInput ballDetect;

  private final ShuffleboardTab m_indexerTab;
  private final ShuffleboardLayout m_indexerConditions; 

  /** Creates a new Indexer. */
  public Indexer() {

    bottomMotor = new CANSparkMax(Constants.BOTTOM_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    ballDetect = new DigitalInput(Constants.kBeamBreak);

    m_indexerTab = Shuffleboard.getTab(Constants.kSuffleboardTab);
    m_indexerConditions = m_indexerTab.getLayout("Cargo", BuiltInLayouts.kList)
      .withProperties(Map.of("Label postion", "TOP"));
      
    shuffleboardInit();
  }

  public boolean ballDetected() {
    return ballDetect.get();
  }

  public void load() {
    while (!ballDetected()) {
      bottomMotor.set(Constants.kIndexerSpeed);
    }
    bottomMotor.set(0);
  }

  public void stop() {
    bottomMotor.set(0);
  }

  private void shuffleboardInit(){
    m_indexerConditions.addBoolean("Cargo Held",() -> ballDetected());

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
