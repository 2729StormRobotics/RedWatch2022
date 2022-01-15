// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private final ShuffleboardTab m_shooterTab;
  private final ShuffleboardLayout m_shooterStatus;

  public final CANSparkMax topMotor;

  public Shooter() {
    topMotor = new CANSparkMax(Constants.TOP_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);

    m_shooterTab = Shuffleboard.getTab(Constants.kShuffleBoardTab);
    m_shooterStatus = m_shooterTab.getLayout("Shooter Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position","TOP"));
  }

  public void shoot(double speed) {
    topMotor.set(speed);
  }

  public void stopShoot() {
    topMotor.set(0);
  }

  private void shufflebaordInit(){

  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}