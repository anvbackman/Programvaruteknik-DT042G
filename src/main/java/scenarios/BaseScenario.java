package scenarios;
import java.util.List;

import creator.Mission;

/**
 * The base scenario class is the parent class for all scenarios. It contains the mission and the encounters for the mission.
 * It also contains the execute method which is used to execute the encounters.
 * This will eventually be changed to only generate 1 scenario at a time, but we need to create the gameEngine first.
 * @author Martin Roos Eriksson
 */
public abstract class BaseScenario {

    /**
     * The overarching mission for the scenario.
     */
    private Mission mission;

    /**
     * The encounter generator for the scenario.
     */
    protected EncounterGenerator encounterGenerator;

    /**
     * The list of encounters for the scenario, will eventually be changed to only contain 1 encounter at a time.
     */
    protected List<Encounter> encounters;

    /**
     * Constructor for the base scenario class.
     * @param mission the mission for the scenario.
     */
    public BaseScenario(Mission mission) {
        this.mission = mission;
        encounterGenerator = new EncounterGenerator(mission);
        encounters = generateEncounters();
    }

    /**
     * Generates the encounters for the scenario.
     * @return a list of encounters.
     */
    protected List<Encounter> generateEncounters() {
        return encounterGenerator.generateEncounters();
    }

    /**
     * Executes the encounters for the scenario.
     * This will eventually be changed to only execute 1 encounter at a time.
     */
    public void execute() {
        for (Encounter encounter : encounters) {
            encounter.execute();
        }
    }

    /**
     * Gets the mission for the scenario.
     * @return the mission for the scenario.
     */
    public Mission getMission() {
        return mission;
    }

    /**
     * Sets the mission for the scenario.
     * @param mission the mission for the scenario.
     */
    public void setMission(Mission mission) {
        this.mission = mission;
    }

    /**
     * Gets the encounters for the scenario.
     * @return the encounters for the scenario.
     */
    public List<Encounter> getEncounters() {
        return encounters;
    }

}