package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    /** Same old tune. Put the robot together here. **/
    private final PWMVictorSPX m_leftmotor = new PWMVictorSPX(DriveConstants.kLeftMotorPort);
    private final PWMVictorSPX m_rightmotor = new PWMVictorSPX(DriveConstants.kRightMotorPort);
    private final DriveSubsystem m_drivesubsystem = new DriveSubsystem(m_leftmotor, m_rightmotor);
    private final GenericHID m_stick = new Joystick(OIConstants.kJoystickPort);
    private final Command m_autocommand;
    private final Command m_drivecommand;
    
    public RobotContainer() {
        /** Instantiate the commands and the subsystems and store them for later. **/
        m_autocommand = new AutoCommand(m_drivesubsystem);
        m_drivecommand = new DriveCommand(m_drivesubsystem, m_stick);
        m_drivesubsystem.setDefaultCommand(m_drivecommand);
    }
    /** We add a getter function so we can get the autonomous command in the Robot.java file **/
    public Command getAutonomousCommand() {
        return m_autocommand;
    }
}
