package behavioral.mediatorPattern.mediatorPkg;

import behavioral.mediatorPattern.colleaguePkg.Colleague;


public interface AirlineMediator {

    void notify(Colleague sender, String event);

    void addColleague(Colleague colleague);
}
