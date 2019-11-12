package core;

import core.factories.MachineFactoryImpl;
import core.factories.PilotFactoryImpl;
import core.interfaces.MachineFactory;
import core.interfaces.MachinesManager;
import core.interfaces.PilotFactory;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import io.ConsoleReader;

import java.util.LinkedHashMap;
import java.util.Map;

public class Engine implements Runnable {

    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;
    private ConsoleReader reader;

    public Engine() {
        this.pilotFactory = new PilotFactoryImpl();
        this.machineFactory = new MachineFactoryImpl();
        this.pilots = new LinkedHashMap<>();
        this.machines = new LinkedHashMap<>();
        this.reader = new ConsoleReader();
    }

    @Override
    public void run() {
        MachinesManager machinesManager = new MachinesManagerImpl(this.pilotFactory, this.machineFactory, this.pilots, this.machines);

        String input;

        while (!"Over".equals(input = reader.readLine())) {

            String[] data = input.split("\\s+");

            String message = "";

            switch (data[0]) {
                case "Hire":
                    message = machinesManager.hirePilot(data[1]);
                    break;
                case "ManufactureTank":
                    message = machinesManager.manufactureTank(data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]));
                    break;
                case "ManufactureFighter":
                    message = machinesManager.manufactureFighter(data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]));
                    break;
                case "Engage":
                    message = machinesManager.engageMachine(data[1], data[2]);
                    break;
                case "Attack":
                    message = machinesManager.attackMachines(data[1], data[2]);
                    break;
                case "AggressiveMode":
                    message = machinesManager.toggleFighterAggressiveMode(data[1]);
                    break;
                case "DefenseMode":
                    message = machinesManager.toggleTankDefenseMode(data[1]);
                    break;
                case "Report":
                    message = machinesManager.pilotReport(data[1]);
                    break;
            }
            System.out.println(message);

        }
    }
}
