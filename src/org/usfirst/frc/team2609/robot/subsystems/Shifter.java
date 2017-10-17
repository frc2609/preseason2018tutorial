package org.usfirst.frc.team2609.robot.subsystems;

import java.util.EnumMap;

import org.usfirst.frc.team2609.pneumatics.Pneumatics;
import org.usfirst.frc.team2609.robot.RobotMap;
import enums.PneumaticState;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import enums.ShifterState;

/**
 *
 */
public class Shifter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private EnumMap<PneumaticState, DoubleSolenoid.Value> shifterMap = new EnumMap<PneumaticState, DoubleSolenoid.Value>(PneumaticState.class);
	private EnumMap<ShifterState, PneumaticState> humanShifterMap = new EnumMap<ShifterState, PneumaticState>(ShifterState.class);
	private Pneumatics m_shifter;
	private Shifter m_instance = null;
	public Shifter(){
		shifterMap.put(PneumaticState.EXTENDED, DoubleSolenoid.Value.kForward);
		shifterMap.put(PneumaticState.RETRACTED, DoubleSolenoid.Value.kReverse);
		shifterMap.put(PneumaticState.MIDDLE, DoubleSolenoid.Value.kOff);

		humanShifterMap.put(ShifterState.HIGH, PneumaticState.RETRACTED);
		humanShifterMap.put(ShifterState.LOW, PneumaticState.EXTENDED);
		humanShifterMap.put(ShifterState.NEUTRAL, PneumaticState.MIDDLE);
		
		this.m_shifter = new Pneumatics(RobotMap.driveShifter, shifterMap);
	}
	public Shifter getInstance(){
		if(m_instance == null){
			m_instance = new Shifter();
			return m_instance;
		}else{
			return m_instance;
		}
	}
	
	public void setShifterState(ShifterState desiredState){
		m_shifter.setState(humanShifterMap.get(desiredState));
	}

    public void initDefaultCommand() {
		RobotMap.driveShifter.set(DoubleSolenoid.Value.kOff);
    }
}