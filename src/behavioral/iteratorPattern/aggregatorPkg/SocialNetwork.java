package behavioral.iteratorPattern.aggregatorPkg;

import behavioral.iteratorPattern.iteratorsPkg.ProfileIterator;

// Iterable Collection
public interface SocialNetwork {

    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);
}