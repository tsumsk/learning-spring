package inject.collection.values.using.annotation.anotherexample;

import org.springframework.stereotype.Service;

@Service
public class FtpArtworkSender implements ArtworkSender {
    @Override
    public void sendArtwork(String artworkPath, Recipient recipient) {
        System.out.println("send using ftp");
    }

    @Override
    public String getFriendlyName() {
        return "File Transfer Protocol";
    }

    @Override
    public String getShortName() {
        return "ftp";
    }
}
