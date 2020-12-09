package frc.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Now we have a subsystem in a separate file. It is not much different 
 * from when it was in the RobotContainer file, but now we have to 
 * pass the parts of the robot in as parameters to the constructor. We
 * store the objects we get in private variables. 
 *
 * Notice that we don't specifiy the types of for the Constructor as PWM contollers,
 * instead we use the more generic "SpeedController". In gerneral is it s a good 
 * idea to use the most generic, highest level class for these that allow you to 
 * get at the methods you need. That allows you to change the type of the controller
 * in the RobotContainer file without having to change anything in the subsystem file.
 * If you do need to do things differently, you can have more than one constructor, 
 * each with the specific types of parameters, and change the code there. 
 **/
public class DriveSubsystem extends SubsystemBase {
    private final SpeedController m_leftmotor;
    private final SpeedController m_rightmotor;
    private final DifferentialDrive m_diffdrive;

    public DriveSubsystem(final SpeedController leftmotor, final SpeedController rightmotor) {
        m_leftmotor = leftmotor;
        m_rightmotor = rightmotor;
        m_diffdrive = new DifferentialDrive(m_leftmotor, m_rightmotor);
    }
    /** 
     * We expose the lower level methods to the higher level callers. We use different types
     * in the parameters so that java will call whichever is appropriate. In this case
     * we can either call the method with a joystick to read, or values to set the motors to.
     * We need this ability for the autonomous command. 
     **/
    public void arcadeDrive(final double fwd, final double rot) {
        m_diffdrive.arcadeDrive(fwd, rot);
    }

    public void arcadeDrive(final GenericHID m_stick) {
        m_diffdrive.arcadeDrive(m_stick.getY(Hand.kLeft), m_stick.getX(Hand.kLeft));
    }
}
