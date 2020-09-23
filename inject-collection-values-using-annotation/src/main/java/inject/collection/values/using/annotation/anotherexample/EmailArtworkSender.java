package inject.collection.values.using.annotation.anotherexample;

import org.springframework.stereotype.Service;

@Service
public class EmailArtworkSender implements ArtworkSender {
    @Override
    public void sendArtwork(String artworkPath, Recipient recipient) {
        System.out.println("send using email");
    }

    @Override
    public String getFriendlyName() {
        return "Electric Mail";
    }

    @Override
    public String getShortName() {
        return "email";
    }
}
