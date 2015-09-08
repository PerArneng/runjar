package com.runjar.io;

import com.runjar.model.ManifestInfo;

import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ManifestInfoReader {

    public ManifestInfo read(File file) {

        try {

            JarFile jarFile = new JarFile(file);

            Manifest manifest = jarFile.getManifest();
            if (manifest == null) {
                return null;
            }

            Attributes mainAttributes = manifest.getMainAttributes();

            return new ManifestInfo(mainAttributes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
