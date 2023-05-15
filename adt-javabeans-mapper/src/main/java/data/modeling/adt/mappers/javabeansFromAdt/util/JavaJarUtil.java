package data.modeling.adt.mappers.javabeansFromAdt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaJarUtil {

    /**
     * compile and jar given a target folder of java files
     *
     * @param path the target folder of java files
     * @param jarName the jar name
     * @return the location of the jar
     * @throws IOException - not able to open the file
     * @throws InterruptedException - if could not compile the artifact
     */
    public String toJar(Path path, String jarName)
            throws IOException, InterruptedException {
        return toJar(path + "*", path, jarName);
    }

    public String toJar(
            String jarPath, Path path, String jarName)
            throws IOException, InterruptedException {
        String jarLocation = path + jarName + ".jar";

        // compile
        List<String> compileArgs =
                new ArrayList<>(Arrays.asList("javac", "-encoding", "UTF-8", "-cp", jarPath));
        Set<String> files =
                findFiles(path, file -> file.toString().endsWith("java")).stream()
                        .map(Path::toString)
                        .collect(Collectors.toSet());
        compileArgs.addAll(files);
        assert (0 == executeCommand(compileArgs));

        // package
        List<String> jarArgs = Arrays.asList("jar", "cvf", jarLocation, "-C", path.toString(), ".");
        assert (0 == executeCommand(jarArgs));
        return jarLocation;
    }

    private int executeCommand(List<String> compileArgs) throws IOException, InterruptedException {
        Process process = new ProcessBuilder().command(compileArgs).redirectErrorStream(true).start();
        // note: without consuming the stdout and stderr
        //  the process will block and hang.
        Stream<String> outinput =
                new BufferedReader(new InputStreamReader(process.getInputStream())).lines();
        outinput.toList().forEach(System.out::println);

        Stream<String> errinput =
                new BufferedReader(new InputStreamReader(process.getErrorStream())).lines();
        errinput.toList().forEach(System.out::println);

        process.waitFor(4, TimeUnit.MINUTES);
        int exitCode = process.exitValue();
        if (exitCode > 0) {
            errinput = new BufferedReader(new InputStreamReader(process.getErrorStream())).lines();
            errinput.toList().forEach(System.out::println);
        }
        process.destroy();
        return exitCode;
    }

    /**
     * The parameters are the target file name to look for and the directory to start in.
     *
     * @param targetPath = target file name, dirName = directory to start in
     */
    private Set<Path> findFiles(Path targetPath, Predicate<Path> predicate) throws IOException {
        try (Stream<Path> walkStream = Files.walk(targetPath)) {
            return walkStream
                    .filter(p -> p.toFile().isFile())
                    .filter(predicate)
                    .collect(Collectors.toSet());
        }
    }
}
