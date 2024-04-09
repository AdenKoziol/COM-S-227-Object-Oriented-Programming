package hw1;

/**
 * For this assignment you will implement one class, called CameraBattery, that models a 
removable and rechargeable camera battery. The battery can be charged both directly by the 
camera when connect to a USB port and by an external "wall wart" battery charger. However, 
the battery can only be in one place at a time, either connected to the camera, connected to the 
external charger, or disconnect for any device. The battery has a maximum capacity to which it 
can be charged. The rate of charge when connect to the camera is fixed, while the rate of charge 
when connected to the external charger is determined by the charger setting. The charger setting 
is a number between 0 inclusive and NUM_CHARGER_SETTINGS exclusive. The charger 
setting is set by the user repeatedly pressing a single settings button. Each press the setting 
increases by one. However, when the maximum setting is reached the next button press puts it 
back to setting 0. 
 * @author Aden Koziol 
 * */

public class CameraBattery 
{
	
	/**the number of external charger settings */
	public static final int NUM_CHARGER_SETTINGS = 4;
	/**the default charge rate */
	public static final double CHARGE_RATE = 2.0;
	/**the default camera power consumption */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;
	
	/**holds the battery capacity */
	private double batterycapacity;
	/**holds the battery charge */
	private double batteryCharge;
	/**holds the battery drain */
	private double drainage;
	/**holds the battery charge */
	private double chargeage;
	/**temporary variable used to store the battery charge before it was drained */
	private double batteryTemp;
	/**tells if battery is in camera or not */
	private double batteryIn;
	/**the setting the charger is on */
	private int chargerSetting = 0;
	/**holds the power consumption */
	private double camPowerConsumption = DEFAULT_CAMERA_POWER_CONSUMPTION;
	
	/**
	 * Constructs a new camera battery simulation. The battery should start disconnected from both the 
camera and the external charger. The starting battery charge and maximum charge capacity are 
given. If the starting charge exceeds the batteries capacity, the batteries charge is set to its 
capacity. The external charger starts at setting 0. 
	 * @param batteryStartingCharge
	 * The starting battery charge for the battery
	 * @param batteryCapacity
	 * The max charge that the battery can hold
	 */
	public CameraBattery(double batteryStartingCharge, double batteryCapacity)
	{
		batteryCharge = Math.min(batteryStartingCharge, batteryCapacity);
		batterycapacity = batteryCapacity;
	}
	
	/**
	 * Indicates the user has pressed the setting button one time on the external charger. The charge 
setting increments by one or if already at the maximum setting wraps around to setting 0. 
	 */
	public void buttonPress()
	{
		chargerSetting++;
		chargerSetting %= NUM_CHARGER_SETTINGS;
	}
	
	/**
	 * Charges the battery connected to the camera (assuming it is connected) for a given number of 
minutes. The amount of charging in milliamp-hours (mAh) is the number minutes times the 
CHARGE_RATE constant. However, charging cannot exceed the capacity of the battery 
connected to the camera, or no charging if the battery is not connected. The method returns the 
actual amount the battery has been charged. 
	 * @param minutes
	 * minutes that the camera was charged for
	 * @return amount that the battery charged
	 */
	public double cameraCharge(double minutes)
	{
		chargeage += Math.min(batterycapacity - batteryCharge, minutes * CHARGE_RATE) * batteryIn;
		return Math.min(batterycapacity - batteryCharge, minutes * CHARGE_RATE) * batteryIn;
	}
	
	/**
	 * Drains the battery connected to the camera (assuming it is connected) for a given number of 
minutes. The amount of drain in milliamp-hours (mAh) is the number of minutes times the 
cameras power consumption. However, the amount cannot exceed the amount of charge 
contained in the battery assuming it is connected to the camera, or no amount drain if the battery 
is not connected. The method returns the actual amount drain from the battery. 
	 * @param minutes
	 * minutes that battery was used
	 * @return amount of charge that was drained
	 */
	public double drain(double minutes)
	{
		batteryTemp = getBatteryCharge();
		drainage += (Math.min(getBatteryCharge(), (minutes * getCameraPowerConsumption()))) * batteryIn;
		return (Math.min(batteryTemp, minutes * getCameraPowerConsumption())) * batteryIn;
	}
	
	/**
	 * Charges the battery connected to the external charger (assuming it is connected) for a given 
number of minutes. The amount of charging in milliamp-hours (mAh) is the number minutes 
times the charger setting (a number between 0 inclusive and NUM_CHARGER_SETTINGS 
exclusive) the CHARGE_RATE constant. However, charging cannot exceed the capacity of the 
battery connected to the camera, or no charging if the battery is not connected. The method 
returns the actual amount the battery has been charged.   
	 * @param minutes
	 * amount of time the battery was charged for
	 * @return amount that the battery was charged
	 */
	public double externalCharge(double minutes)
	{
		chargeage += Math.min(batterycapacity - batteryCharge, minutes * CHARGE_RATE * chargerSetting);
		return Math.min(batterycapacity - batteryCharge, minutes * CHARGE_RATE * chargerSetting);
	}
	
	/**
	 * Reset the battery monitoring system by setting the total battery drain count back to 0. 
	 */
	public void resetBatteryMonitor()
	{
		drainage = 0;
	}
	
	/**
	 * Get the battery's capacity. 
	 * @return the amount of charge the battery can hold
	 */
	public double getBatteryCapacity()
	{
		return batterycapacity;
	}
	
	/**
	 * Get the battery's current charge. 
	 * @return the charge of the battery
	 */
	public double getBatteryCharge()
	{
		return Math.min(batterycapacity, Math.max(0, batteryCharge - drainage + chargeage));
	}
	
	/**
	 * Get the current charge of the camera's battery. 
	 * @return the charge of the camera
	 */
	public double getCameraCharge()
	{
		return Math.min(batterycapacity, Math.max(0, batteryCharge - drainage + chargeage)) * batteryIn;
	}
	
	/**
	 * Get the power consumption of the camera. 
	 * @return camera power consumption
	 */
	public double getCameraPowerConsumption()
	{
		return camPowerConsumption;
	}
	
	/**
	 * Get the external charger setting. 
	 * @return the setting the charger is on
	 */
	public int getChargerSetting()
	{
		return chargerSetting;
	}
	
	/**
	 * Get the total amount of power drained from the battery since the last time the battery monitor 
was started or reset. 
	 * @return the total amount of battery drained
	 */
	public double getTotalDrain()
	{
		return drainage;
	}
	
	/**
	 * Move the battery to the external charger. Updates any variables as needed to represent the move.
	 */
	public void moveBatteryExternal()
	{
		batteryIn = 0;
	}
	
	/**
	 * Move the battery to the camera. Updates any variables as needed to represent the move. 
	 */
	public void moveBatteryCamera()
	{
		batteryIn = 1;
	}
	
	/**
	 * Remove the battery from either the camera or external charger. Updates any variables as needed 
to represent the removal. 
	 */
	public void removeBattery()
	{
		batteryIn = 0;
	}
	
	/**
	 * Set the power consumption of the camera. 
	 * @param cameraPowerConsumption
	 * given power consumption
	 */
	public void setCameraPowerConsumption(double cameraPowerConsumption)
	{
		camPowerConsumption = cameraPowerConsumption;
	}
}