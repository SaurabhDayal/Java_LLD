package behavioral.mediatorPattern.colleaguePkg;

import behavioral.mediatorPattern.mediatorPkg.AirlineMediator;

public interface Colleague {

    void setMediator(AirlineMediator mediator);

    void notifyMediator(String event);

    void registerWithMediator();
}
