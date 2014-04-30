package com.player.service.songs;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.gagravarr.tika.FlacParser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.util.UUID;

/**
 * @author Николай
 */
public class SongHelper {

    public Metadata getMetadataFromSong(File file) throws IOException {
        InputStream input = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
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

    public File saveFileOnDisc(File song, String fileDirectory) throws IOException {

        File directory = new File(fileDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(fileDirectory + UUID.randomUUID().toString() + ".flac");

        byte[] buffer = new byte[8 * 1024];

        try (InputStream is = new FileInputStream(song)) {
            try (OutputStream output = new FileOutputStream(file)) {
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
        }

        return file;
    }
}
