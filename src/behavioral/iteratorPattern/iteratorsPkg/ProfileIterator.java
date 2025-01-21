package behavioral.iteratorPattern.iteratorsPkg;

import behavioral.iteratorPattern.profilePkg.Profile;

// Iterator
public interface ProfileIterator {

    boolean hasNext();

    Profile getNext();

    void reset();
}