// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Camera extends SubsystemBase {
  /** Creates a new Camera. */

  public Camera() {
    // new Thread(() -> {
    //   UsbCamera camera = CameraServer.startAutomaticCapture();
    //   camera.setResolution(320, 240);
    //   camera.setFPS(60);  

    //   CvSink cvsink = CameraServer.getVideo();
    //   CvSource outputstream = CameraServer.putVideo("Robot Camera", 320, 240);

    //   Mat source = new Mat();
    //   Mat output = new Mat();

    //   while(!Thread.interrupted()) {
    //     if (cvsink.grabFrame(source) == 0) {
    //       continue;
    //     }
    //     Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
    //     outputstream.putFrame(output);
    //   }
    // }).start();

    CameraServer.getInstance().startAutomaticCapture();

  }
}

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }
