package behavioral.iteratorPattern.IterablePkg;

import behavioral.iteratorPattern.iteratorPkg.ProfileIterator;

// Iterable Collection
public interface SocialNetwork {

    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);
}