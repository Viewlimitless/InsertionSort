
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sort {
    static final String COMMAND1 = "C:\\testSort\\ --out-prefix=sorted_ --content- type=i --sort-mode=a";
    static final String COMMAND2 = "C:\\testSort\\ --out-prefix=sorted_ --content- type=i --sort-mode=b";
    static final String COMMAND3 = "C:\\testSort\\ --out-prefix=sorted_ --content- type=s --sort-mode=a";

    public static void main(String[] args) {
        List<FileSorter> list = null;
        try {
            if (args.length == 5){
            list = getFileSorter(args);
            } else throwException();
            for (final FileSorter fileSorter : list) {
                System.out.println(String.format("[%s]->[%s]", fileSorter.getInFile(), fileSorter.getOutFile()));
                fileSorter.start();

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<FileSorter> getFileSorter(String... commands) throws IOException {
        List<FileSorter> list = new ArrayList<>();
        Algorithm algorithm = null;
        switch (commands[2] + " " + commands[3] + " " + commands[4]) {
            case "--content- type=i --sort-mode=a":
                algorithm = Algorithm.INTEGER_GROWTH;
                break;
            case "--content- type=i --sort-mode=b":
                algorithm = Algorithm.INTEGER_DOWNTURN;
                break;
            case "--content- type=s --sort-mode=a":
                algorithm = Algorithm.STRING_GROWTH;
                break;
            case "--content- type=s --sort-mode=b":
                algorithm = Algorithm.STRING_DOWNTURN;
                break;
        }
        if (algorithm == null) {
            throwException();
        }
        File directory = new File(commands[0]);
        if (!directory.exists())
            throw new FileNotFoundException(String.format("Directory [%s] is not exists.", commands[0]));
        if (directory.isHidden())
            throw new FileNotFoundException(String.format("Directory [%s] is hidden.", commands[0]));

        File[] inFiles = directory.listFiles();

        for (File inFile : inFiles) {
            File outFile = getOutFileName(commands[1].replaceFirst("--out-prefix=", ""), inFile);
            if (outFile.exists()) {
                System.out.println(String.format("[%s] can not be sorted, because [%s] is already exist.", inFile.getName(), outFile.getName()));
            } else
                list.add(new FileSorter(inFile, outFile, algorithm));
        }


        return list;
    }

    private static void throwException() {
        throw new IllegalArgumentException(String.format("\n" +
                "Current command unknown.\n" +
                "Command examples:\n" +
                "%s\n%s\n%s\n", COMMAND1, COMMAND2, COMMAND3));
    }

    private static File getOutFileName(String addPrefix, File inFile) {
        String suffix = inFile.getName().split("\\.").length > 1 ? "." + inFile.getName().split("\\.")[1] : "";
        String outName = addPrefix + inFile.getName().split("\\.")[0] + suffix;
        return new File(inFile.getParent(), outName);
    }


}


