package behavioral.iteratorPattern.socialNetworksPkg;

import behavioral.iteratorPattern.iteratorsPkg.ProfileIterator;

public interface SocialNetwork {
    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);
}