package behavioral.iteratorPattern.iteratorsPkg;

import behavioral.iteratorPattern.profilePkg.Profile;

public interface ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}