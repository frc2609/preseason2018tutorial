package org.usfirst.frc.team2609.pneumatics;

import java.util.EnumMap;
import java.util.Map;

import org.usfirst.frc.team2609.utils.MapOperations;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import enums.PneumaticState;

public class Pneumatics {
	private DoubleSolenoid m_solenoid;
	private EnumMap<PneumaticState, DoubleSolenoid.Value> m_pneumaticMap;
	private PneumaticSensorInput m_sensors = null; // initialize as null so we can check if it exists
	private Map<DoubleSolenoid.Value, PneumaticState> m_reversePneumaticMap;
	public Pneumatics(DoubleSolenoid solenoid, EnumMap<PneumaticState, DoubleSolenoid.Value> pneumaticmap){
		this.m_solenoid = solenoid;
		this.m_pneumaticMap = pneumaticmap;
		this.m_reversePneumaticMap = MapOperations.invertMap(m_pneumaticMap);
		
	}
	public Pneumatics(DoubleSolenoid solenoid, EnumMap<PneumaticState, DoubleSolenoid.Value> pneumaticmap, PneumaticSensorInput sensors){
		this.m_solenoid = solenoid;
		this.m_pneumaticMap = pneumaticmap;
		this.m_reversePneumaticMap = MapOperations.invertMap(m_pneumaticMap);
		this.m_sensors = sensors;
	}

	public void setState(PneumaticState desiredState){
		m_solenoid.set(m_pneumaticMap.get(desiredState));
	}
	public PneumaticState getCurrentState(){
		if(m_sensors != null){
			return m_sensors.getStateFromSensors();
		}
//		else if(neutralAfterSet){
//			// get from history
//		}
		else{
			return m_reversePneumaticMap.get(m_solenoid.get());
		}
	}
	
	boolean neutralAfterSet = false;
}

