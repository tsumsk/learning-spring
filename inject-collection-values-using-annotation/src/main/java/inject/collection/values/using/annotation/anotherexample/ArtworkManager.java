package inject.collection.values.using.annotation.anotherexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkManager {
    @Autowired
    private List<ArtworkSender> senders;

    public void send() {
        senders.forEach(e -> e.sendArtwork("artwork", new Recipient() {}));
    }
}
