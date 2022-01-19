// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import static frc.robot.Constants.ColorConstants.*;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;

public class ColorDetection extends SubsystemBase {
  private final ColorSensorV3 m_colorSensor;

  private Color m_detectedColor;
  private double m_IR;

  // private Color m_initalColor = kNoColor;
  // private Color m_previousColor = kNoColor;
  // private Color m_previousColorCheck = kNoColor;
  // private String m_currentColorString = "BLACK";
  // private String m_targetColorString = "BLACK";
  // private boolean m_currentColorAvailable = false;
  // private boolean m_readyToFindColor = false;
  // private boolean m_targetColorAvailable = false;
  // private boolean m_readyToCount = false;  
  

  // private int m_colorCheck = 0;
  // private Color m_currentColor = kNoColor;
  // private Color m_targetColor = kNoColor;

  private final ShuffleboardTab m_controlPanelTab;
  private final ShuffleboardLayout m_controlPanelStatus;
  private SuppliedValueWidget<Boolean> m_targetColorWidget;
  private SuppliedValueWidget<Boolean> m_currentColorWidget;

  // private final NetworkTable m_PartyTable;
  // private final NetworkTableEntry m_ControllPanelColorStatus;

  /** Creates a new Color. */
  public ColorDetection() {
    m_colorSensor = new ColorSensorV3(Port.kOnboard);
    
    // m_colorMatch = new ColorMatch();
    // m_colorMatch.addColorMatch(kRedTarget);
    // m_colorMatch.addColorMatch(kBlueTarget);

    Color m_detectedColor = m_colorSensor.getColor();
    double m_IR = m_colorSensor.getIR();

   


    // m_PartyTable = NetworkTableInstance.getDefault().getTable("Party Statuses");
    // m_ControllPanelColorStatus = m_PartyTable.getEntry("Color Detected");

    m_controlPanelTab = Shuffleboard.getTab(kShuffleboardTab);
    m_controlPanelStatus = m_controlPanelTab.getLayout("Control Panel Status", BuiltInLayouts.kList)
      .withProperties(Map.of("Label position", "TOP"));

      shuffleboardInit();
      
  }

  // public void detectInitialColor() {
  //   Color detectedColor = m_colorSensor.getColor();
  //   ColorMatchResult matchResult = m_colorMatch.matchClosestColor(detectedColor);

  //   if (matchResult.color == m_previousColorCheck) {
  //     m_colorCheck++;
  //   }
  //   else {
  //     m_colorCheck = 0;
  //   }

  //   if (m_colorCheck > 5) {
  //     m_currentColorAvailable = true;
  //     m_currentColor = matchResult.color;
  //   }

  //   if (m_currentColor == kRedTarget) {
  //      m_currentColorString = "RED";
  //   }
  //   else if (m_currentColor == kBlueTarget) {
  //     m_currentColorString = "BLUE";
  //   }
  //   else {
  //     m_currentColorAvailable = false;
  //     m_currentColorString = "BLACK";
  //   }
  // }

  // public boolean onTargetColor() {
  //   boolean onTargetColor = m_currentColor.equals(m_targetColor);
  //   m_ControllPanelColorStatus.setBoolean(onTargetColor);
  //   return onTargetColor;
  // }

  private void shuffleboardInit() {
    m_controlPanelStatus.addNumber("R",() -> m_detectedColor.red);

    // m_targetColorWidget = m_controlPanelStatus.addBoolean("Target Color", () -> m_targetColorAvailable)
    //     .withWidget(BuiltInWidgets.kBooleanBox);
    // m_targetColorWidget.withProperties(Map.of("colorWhenFalse", "Black"));
    // m_currentColorWidget = m_controlPanelStatus.addBoolean("Current Color", () -> m_currentColorAvailable)
    //     .withWidget(BuiltInWidgets.kBooleanBox);
    // m_currentColorWidget.withProperties(Map.of("colorWhenFalls", "Black"));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // m_targetColorWidget.withWidget(BuiltInWidgets.kBooleanBox).withProperties(Map.of("colorWhenTrue", m_targetColorString));
    // m_currentColorWidget.withWidget(BuiltInWidgets.kBooleanBox).withProperties(Map.of("colorWhenTrue", m_currentColorString));

  }
}
