
import java.io.*;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ccwc {

    protected int fileLength = -1;
    protected int linesNum = -1;
    protected int wordsNum = -1;
    protected int charNum = -1;
    protected String fileName = "";
    protected boolean hasFile;
    protected BufferedReader reader;

    public ccwc(String file) throws IOException {
        this.fileName += file;
        this.hasFile = !this.fileName.isEmpty();
        if (hasFile) {
            this.reader = new BufferedReader(new FileReader(this.fileName));
        } else {
            this.reader = new BufferedReader(new InputStreamReader(System.in));
        }
    }

    @Override
    public String toString() {
        StringBuilder printedResult = new StringBuilder();
        if (linesNum >= 0) {
            printedResult.append(" ").append(this.linesNum);
        }
        if (wordsNum >= 0) {
            printedResult.append(" ").append(this.wordsNum);
        }
        if (fileLength >= 0) {
            printedResult.append(" ").append(this.fileLength);
        }
        if (charNum >= 0) {
            printedResult.append(" ").append(this.charNum);
        }

        String Name = String.valueOf(Path.of(this.fileName).getFileName());
        printedResult.append(" ").append(Name);
        return printedResult.toString();
    }


    private String readStdin () throws IOException {
        StringBuilder inputText = new StringBuilder();
        String line;
        while ((line = this.reader.readLine()) != null) {
            inputText.append(line).append("\n");
        }
        reader.close();
        if (hasFile) {
            this.reader = new BufferedReader(new FileReader(this.fileName));
        } else {
            this.reader = new BufferedReader(new InputStreamReader(System.in));
        }
        return inputText.toString();
    }

    public void getBytesNum() throws IOException {
        if (hasFile) {
            this.fileLength = (int) new File(this.fileName).length();
        } else {
            this.fileLength = readStdin().getBytes().length;
        }
    }

    public void getLinesNum() throws IOException {
        this.linesNum = (int) readStdin().lines().count();
    }


    public void getWordsNum() throws IOException {
        this.wordsNum = (int) readStdin().lines().
                flatMap(s -> Stream.of(s.split("\\s"))).
                filter(s -> !s.isEmpty()).
                count();
    }

    public void getCharNum() throws IOException {
        this.charNum = 0;
        while (this.reader.read() != -1) {
            this.charNum++;
        }
    }

    public void allMetrics() throws IOException {
        this.fileLength = 0;
        this.linesNum = 0;
        this.wordsNum = 0;
        String line;
        while ((line = this.reader.readLine()) != null) {
            if (!hasFile) {
                this.fileLength += line.getBytes().length;
            }
            linesNum++;
            wordsNum += Stream.of(line.split("\\s")).filter(s -> !s.isEmpty()).count();
        }
        if(hasFile) {
            this.fileLength = (int) new File(this.fileName).length();
        }
    }


}
