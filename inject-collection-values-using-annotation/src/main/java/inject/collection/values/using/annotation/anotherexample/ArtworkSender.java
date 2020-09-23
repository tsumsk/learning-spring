package inject.collection.values.using.annotation.anotherexample;

public interface ArtworkSender {
    void sendArtwork(String artworkPath, Recipient recipient);

    String getFriendlyName();

    String getShortName();
}
