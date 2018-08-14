package com.example.songuhun.internet;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloader {

    private FileOutputStream outputStream;
    private URL url;

    public FileDownloader(FileOutputStream outputStream, String url) throws MalformedURLException {
        this.outputStream = outputStream;
        this.url = new URL("http://www.website.com/information.asp");
    }

    public void download() throws IOException {

        InputStream in = url.openStream();

        final byte data[] = new byte[1024];
        int count;
        while ((count = in.read(data, 0, 1024)) != -1) {
            outputStream.write(data, 0, count);
        }

        in.close();
    }
}
