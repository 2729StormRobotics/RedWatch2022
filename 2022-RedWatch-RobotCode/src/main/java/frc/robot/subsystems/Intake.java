// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import static frc.robot.Constants.IntakeConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {

  private final CANSparkMax m_intakeMotor;
  private final DoubleSolenoid m_intakePistons;

  private final NetworkTable m_intakeTable;
  private final NetworkTableEntry m_intakeStatus;

  /** Creates a new Intake. */
  public Intake() {

    m_intakeMotor = new CANSparkMax(kIntakeMotorPort, MotorType.kBrushed);
    m_intakePistons = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, kIntakeRaiseChannel, kIntakeLowerChannel);//The type of hopper (Replace CTREPCM With correct)

    m_intakeMotor.restoreFactoryDefaults();
    m_intakeMotor.setIdleMode(IdleMode.kCoast);
    m_intakeMotor.setInverted(true);

    m_intakeTable = NetworkTableInstance.getDefault().getTable("Power Cells");
    m_intakeStatus = m_intakeTable.getEntry("Intake Running");
  }

  public void runIntake(double speed) {
    
    m_intakeStatus.setBoolean(true);
    m_intakeMotor.set(speed);

  }

  public void intake() {

    runIntake(kIntakeMotorSpeed);

  }

  public void eject() {

    runIntake(kEjectMotorSpeed);

  }

  public void stopIntake() {
    
    m_intakeMotor.set(0);
    m_intakeStatus.setBoolean(false);

  }

  public void raiseIntake() {
    
    m_intakePistons.set(kIntakeRaiseValue);

  }

  public void lowerIntake() {

    m_intakePistons.set(kIntakeLowerValue);

  }

  public void toggleIntakePistons() {

    if (isIntakeLowered()) {

      raiseIntake();;

    }

    else {

      lowerIntake();

    }

  }

  public boolean isIntakeLowered() {

    if (m_intakePistons.get() == kIntakeLowerValue) {
      
      return true;

    }

    else {

      return false;

    }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
