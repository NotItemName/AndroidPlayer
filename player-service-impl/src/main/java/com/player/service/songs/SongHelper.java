package com.player.service.songs;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.gagravarr.tika.FlacParser;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author Николай
 */
@Component
public class SongHelper {

    public Metadata getMetadataFromSong(InputStream input) throws IOException {
        InputStream bufferedInputStream = new BufferedInputStream(input);
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new FlacParser();
        ParseContext parseCtx = new ParseContext();
        try {
            parser.parse(bufferedInputStream, handler, metadata, parseCtx);
            input.close();
        } catch (SAXException | TikaException e) {
            e.printStackTrace();
        }

        return metadata;
    }
}
