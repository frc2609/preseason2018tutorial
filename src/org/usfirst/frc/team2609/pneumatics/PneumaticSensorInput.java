package org.usfirst.frc.team2609.pneumatics;

import java.util.EnumMap;
import java.util.Map;

import org.usfirst.frc.team2609.utils.MapOperations;

import edu.wpi.first.wpilibj.DigitalInput;
import enums.PneumaticState;;

public class PneumaticSensorInput {
	private Map<boolean[], PneumaticState> m_sensorMap;
	private Map<PneumaticState,boolean[]> m_reverseSensorMap;
	private DigitalInput[] m_sensors;
	private boolean m_sensorMismatch = false; // boolean for when sensor->state matching is faulty
	
	public PneumaticSensorInput(DigitalInput[] sensors, Map<boolean[], PneumaticState> sensorMap){
		this.m_sensorMap = sensorMap;
		// sensor[n].get() -> PneumaticState
		this.m_sensors = sensors;
		m_reverseSensorMap = MapOperations.invertMap(sensorMap);
//		if(sensors.length != sensorMap.size()){
//			for(int i=0; i<10; i++){
//				System.out.println("!!!!!!!!!!!!!!!!!!!SENSOR MAPPING MISMATCH!!!!!!!!!!!!!!!!!!!");
//			}
//			this.m_sensorMismatch= true;
//		}
		// TODO: Uncomment this when 3 sensor feedback is added
		// sensorMap should always be a size of 3
	}
	public PneumaticState getStateFromSensors(){ // later a getTimedState can also be added
		if(m_sensorMismatch){
			for(int i=0; i<10; i++){
				System.out.println("!!!!!!!!!!!!!!!!!!!SENSOR MAPPING MISMATCH!!!!!!!!!!!!!!!!!!!");
			}
			return PneumaticState.MIDDLE; // return middle in hopes of relying timers
		}else{
			boolean[] sensorReadouts = m_reverseSensorMap.get(PneumaticState.MIDDLE); // initialize in middle state!
			for(int i=0; i<m_sensors.length; i++){
				sensorReadouts[i] = m_sensors[i].get();
			}
			return m_sensorMap.get(sensorReadouts);
		}
	}
	
}
