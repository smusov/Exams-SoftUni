package cresla.core;

import cresla.factories.Factory;
import cresla.factories.ModuleFactory;
import cresla.factories.ReactorFactory;
import cresla.interfaces.Manager;
import cresla.interfaces.Module;
import cresla.interfaces.Reactor;
import cresla.repositores.Repository;

import java.util.List;

public class ManagerImpl implements Manager {

    private Repository repository;
    private ModuleFactory moduleFactory;
    private ReactorFactory reactorFactory;

    public ManagerImpl(Repository repository, ModuleFactory moduleFactory, ReactorFactory reactorFactory) {
        this.repository = repository;
        this.moduleFactory = moduleFactory;
        this.reactorFactory = reactorFactory;
    }

    @Override
    public String reactorCommand(List<String> arguments) {

        Reactor reactor = this.reactorFactory.createReactor(arguments);
        this.repository.addReactor(reactor.getId(),reactor);

        String reactorName = reactor.getClass().getSimpleName().replace("R"," R");

        return String.format("Created %s - %d",reactorName,reactor.getId());
    }

    @Override
    public String moduleCommand(List<String> arguments) {

        int reactorId = Integer.parseInt(arguments.get(1));

        Module module = this.moduleFactory.createModule(arguments);

        this.repository.addModule(reactorId,module);

        return String.format("Added %s - %d to Reactor - %d",arguments.get(2),module.getId(),reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        return this.repository.getReport(Integer.parseInt(arguments.get(1)));
    }

    @Override
    public String exitCommand(List<String> arguments) {
        return this.repository.getReport();
    }
}
