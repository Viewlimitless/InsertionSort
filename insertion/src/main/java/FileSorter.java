
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileSorter extends Thread {
    private final Algorithm CURRENT_ALGORITHM;
    private File inFile;
    private File outFile;
    private BufferedReader readerInFile;
    private BufferedReader readerOutFile;
    private File tempFile;
    private final String END_OF_FILE = "---this string will be removed---";

    public FileSorter(File inFile, File outFile, Algorithm CURRENT_ALGORITHM) {
        this.CURRENT_ALGORITHM = CURRENT_ALGORITHM;
        this.inFile = inFile;
        this.outFile = outFile;
        if (!inFile.exists() || inFile.isHidden()) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void run() {
        init();
        String nextInLine = "";
        String nextOutLine = "";
        int linePosition;
        saveStringToPosition(END_OF_FILE + "\n", 0);
        while (nextInLine != null) {
            nextInLine = getNextLineFile(readerInFile);
            initReaderOutFile();
            linePosition = 0;
            while ((nextOutLine = getNextLineFile(readerOutFile)) != null) {
                linePosition++;
                if (compareTwo(nextInLine, nextOutLine)) {
                    saveStringToPosition(nextInLine, linePosition);
                    break;
                }
            }
            if (nextOutLine == null) {
                saveStringToPosition(nextInLine, linePosition);
            }
        }
        close();
    }

    private void saveStringToPosition(String nextInLine, int linePosition) {
        if (nextInLine == null) return;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(outFile.toPath())));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(tempFile.toPath())));
            String line = "";
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
                if (count == linePosition) {
                    writer.write(nextInLine + "\n");
                }
                if (!line.equals(END_OF_FILE))
                    writer.write(line + "\n");
            }
            if (count == 0) {
                writer.write(nextInLine + "\n");
            }
            reader.close();
            writer.close();
            if (readerOutFile != null)
                readerOutFile.close();

            outFile.delete();

            Files.move(tempFile.toPath(), outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            FileSorterInterrupt();
        }
    }

    private void init() {
        try {
            outFile.createNewFile();
            tempFile = File.createTempFile("tempFile", ".tmp", outFile.getParentFile());
            readerInFile = new BufferedReader(new InputStreamReader(Files.newInputStream(inFile.toPath())));
        } catch (IOException e) {
            e.printStackTrace();
            FileSorterInterrupt();
        }

    }

    public void FileSorterInterrupt() {
        this.setDaemon(true);
        while (this.isAlive())
            this.interrupt();
    }

    private void initReaderOutFile() {
        try {
            if (readerOutFile != null) readerOutFile.close();
            readerOutFile = new BufferedReader(new InputStreamReader(Files.newInputStream(outFile.toPath())));
        } catch (IOException e) {
            e.printStackTrace();
            FileSorterInterrupt();
        }
    }

    private boolean compareTwo(String a, String b) {
        if (a != null && b != null && !a.isEmpty() && !b.isEmpty() && !a.equals(END_OF_FILE) && !b.equals(END_OF_FILE))
            switch (CURRENT_ALGORITHM) {
                case INTEGER_GROWTH:
                    return Integer.parseInt(b) > Integer.parseInt(a);
                case INTEGER_DOWNTURN:
                    return Integer.parseInt(a) > Integer.parseInt(b);
                case STRING_GROWTH:
                    return compareTwoStrings(a, b);
                case STRING_DOWNTURN:
                    return compareTwoStrings(b, a);
            }
        return false;
    }

    private boolean compareTwoStrings(String aa, String bb) {
        byte[] a = aa.getBytes();
        byte[] b = bb.getBytes();
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] < b[i]) {
                return true;
            } else if (a[i] > b[i]) {
                return false;
            }
        }
        return false;
    }

    private String getNextLineFile(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            FileSorterInterrupt();
        }
        return null;
    }

    private void close() {
        try {
            readerInFile.close();
            readerOutFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getInFile() {
        return inFile;
    }

    public File getOutFile() {
        return outFile;
    }
}
