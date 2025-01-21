package behavioral.iteratorPattern.iteratorPkg;

import behavioral.iteratorPattern.profilePkg.Profile;

// Iterator
public interface ProfileIterator {

    boolean hasNext();

    Profile getNext();

    void reset();
}