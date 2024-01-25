import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.io.IOException;

public class Main {
    @Parameter(names = {"-c"}, description = "File length in bytes")
    private boolean fileLengthOpt;

    @Parameter (names = {"-l"}, description = "Number of lines in the file/input")
    private boolean linesNumOpt;

    @Parameter (names = {"-w"}, description = "Number of words in the file/input")
    private boolean wordsNumOpt;

    @Parameter (names = {"-m"}, description = "Number of characters in the file/input")
    private boolean charNumOpt;

    @Parameter(description = "Input file name")
    private String fileName = "";
    private boolean hasOption = false;

    private void hasOptions() {
        boolean[] Options = {this.fileLengthOpt, this.linesNumOpt, this.wordsNumOpt, this.charNumOpt};
        for(boolean Opt : Options) {
            if (Opt) {
                this.hasOption = true;
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    public void run() throws IOException {

        this.hasOptions();
        ccwc result = new ccwc(this.fileName);

        if (fileLengthOpt) {
            result.getBytesNum();
        }
        if (linesNumOpt) {
            result.getLinesNum();
        }

        if (wordsNumOpt) {
            result.getWordsNum();
        }

        if (charNumOpt) {
            result.getCharNum();
        }

        if (!hasOption) {
            if(!result.reader.ready()) {
                System.out.println("Error: No options provided, and no input was given. Please provide at least one option or input.");
                result.reader.close();
                System.exit(1);
            }
            result.allMetrics();
        }
        result.reader.close();
        System.out.println(result);
    }



}
