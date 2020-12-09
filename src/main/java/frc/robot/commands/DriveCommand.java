package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This time our drive command is a little more complex. We need to be able 
 * to use the drive when we want to drive with the joystick, but we also 
 * make it so it can be called by the autonomous command. That way we 
 * don't have to duplicate the code in both places. 
 **/
public class DriveCommand extends CommandBase {
    private final DriveSubsystem m_subsystem;
    private final GenericHID m_stick;
    private double fwd;
    private double rot;
    
    /** 
     * Here is what we do when we drive with a joystick. We pass in the 
     * subsystem we want to use and the joystick and we put them into 
     * private variables. We add the subsystem we got to the list
     * of requirements so the scheduler knows what subsystems we 
     * need. And finally we zero out the old values from autonmous, 
     * if any. We don't want them hanging around causing confusion.
     **/
    public DriveCommand(DriveSubsystem subsystem, GenericHID stick) {
        m_subsystem = subsystem;
        m_stick = stick;
        addRequirements(subsystem);
        fwd = 0.0;
        rot = 0.0;
    };
    /**
     * In this case we do the same thing as we did above, but instead of a 
     * joystick, we get the two constants we want to set the motor to. Notice
     * that we do not make them final, in case we want to make more than one
     * with different values. Since in our autonomous command we want to 
     * run the motors at a constant speed for a period of time, we just 
     * set the variables and leave them there. The sussystem itself will 
     * set the motors each cycle which handles the safety watchdog timer.
     **/
    public DriveCommand(DriveSubsystem subsystem, double fwd, double rot) {
        this.fwd = fwd;
        this.rot = rot;
        m_stick = null;
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }
    /**
     * Here is our execute method. When the scheduler runs the command it calls the execute
     * method. We choose which form of arcade drive depending on how this particular object
     * was instantiated, with contants or a joystick. 
     **/
    @Override
    public void execute() {
        if (m_stick != null) {
            m_subsystem.arcadeDrive(m_stick);
        } else {
            m_subsystem.arcadeDrive(fwd,rot);
        }
    }
    /**
     * The scheduler checks at the end of each cycle to see if the command says that it is 
     * finished running. In this case the command runs forever until something else tells it 
     * to stop. Since we run forever the method the scheduler calls always returns false. 
     * If we had some condition we wanted to use, once the condition has been reached we 
     * just return true and the scheduler will cancel the command.
     **/
    @Override 
    public boolean isFinished() {   
        return false;
    }
}
