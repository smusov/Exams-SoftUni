package core;

import common.OutputMessages;
import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;

import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import entities.interfaces.Tank;

import java.util.Map;

import static common.OutputMessages.*;

public class MachinesManagerImpl implements MachinesManager {

    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;

    private String message;

    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory, Map<String, Pilot> pilots, Map<String, Machine> machines) {
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;
        this.pilots = pilots;
        this.machines = machines;
    }


    @Override
    public String hirePilot(String name) {

        if (!this.pilots.containsKey(name)) {

            this.pilots.put(name, this.pilotFactory.createPilot(name));
            message = String.format(OutputMessages.pilotHired, name);

        } else {

            message = String.format(OutputMessages.pilotExists, name);

        }
        return this.message;
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {

        if (!this.machines.containsKey(name)){
            this.machines.put(name,this.machineFactory.createTank(name,attackPoints,defensePoints));
            this.message = String.format(OutputMessages.tankManufactured,name,attackPoints,defensePoints);
        }else {
            this.message = String.format(OutputMessages.machineExists,name);
        }

        return this.message;
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {

        if (!this.machines.containsKey(name)){
            this.machines.put(name,this.machineFactory.createFighter(name,attackPoints,defensePoints));
            this.message = String.format(OutputMessages.fighterManufactured,name,attackPoints,defensePoints);
        }else {
            this.message = String.format(OutputMessages.machineExists,name);
        }

        return this.message;
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {

        if (this.pilots.containsKey(selectedPilotName)){
            if (this.machines.containsKey(selectedMachineName)){
                if (this.machines.get(selectedMachineName).getPilot()==null){

                    this.pilots.get(selectedPilotName).addMachine(this.machines.get(selectedMachineName));
                    this.machines.get(selectedMachineName).setPilot(this.pilots.get(selectedPilotName));
                    this.message = String.format(machineEngaged,selectedPilotName,selectedMachineName);

                }else {

                    this.message = String.format(machineHasPilotAlready,selectedMachineName);

                }
            }else {
                this.message = String.format(machineNotFound,selectedMachineName);
            }
        }else {
            this.message = String.format(OutputMessages.pilotNotFound,selectedPilotName);
        }

        return this.message;
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {

        if (this.machines.containsKey(attackingMachineName)){
            if (this.machines.containsKey(defendingMachineName)){

                double firstMachineAttackPoints = this.machines.get(attackingMachineName).getAttackPoints();
                double secondMachineDefencePoints = this.machines.get(defendingMachineName).getDefensePoints();

                double healthPoints = this.machines.get(defendingMachineName).getHealthPoints();
                this.message = String.format(attackSuccessful,defendingMachineName,attackingMachineName,healthPoints);

                if (firstMachineAttackPoints>secondMachineDefencePoints){

                    this.machines.get(defendingMachineName).setHealthPoints(healthPoints-firstMachineAttackPoints);

                    if (this.machines.get(defendingMachineName).getHealthPoints()<0){
                        this.machines.get(defendingMachineName).setHealthPoints(0);
                    }

                }

                this.machines.get(attackingMachineName).attack(defendingMachineName);

            }else {
                this.message = String.format(machineNotFound,defendingMachineName);
            }
        }else {
            this.message = String.format(machineNotFound,attackingMachineName);
        }

        return this.message;
    }

    @Override
    public String pilotReport(String pilotName) {

        if (this.pilots.containsKey(pilotName)){
            this.message = this.pilots.get(pilotName).report();
        }else {
            this.message = String.format(pilotNotFound,pilotName);
        }

        return this.message.trim();
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {

        if (this.machines.get(fighterName) instanceof Fighter){

            ((Fighter) this.machines.get(fighterName)).toggleAggressiveMode();
            this.message = String.format(fighterOperationSuccessful,fighterName);

        }else {
            this.message = String.format(notSupportedOperation,fighterName);
        }

        return this.message;
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {

        if (this.machines.get(tankName) instanceof Tank){

            ((Tank) this.machines.get(tankName)).toggleDefenseMode();
            this.message = String.format(tankOperationSuccessful,tankName);

        }else {
            this.message = String.format(notSupportedOperation,tankName);
        }

        return this.message;
    }
}
